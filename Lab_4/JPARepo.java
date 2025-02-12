import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public abstract class JPARepo<Entity, Key>
{
    private final EntityManagerFactory emf;
    private final Class<Entity> clazz;

    public JPARepo(EntityManagerFactory emf, Class<Entity> clazz)
    {
        this.emf = emf;
        this.clazz = clazz;
    }

    public void add(Entity entity)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(entity);
        transaction.commit();
        em.close();
    }

    public void delete(Entity entity)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(em.merge(entity));
        transaction.commit();
        em.close();
    }

    public List<Entity> findAll()
    {
        EntityManager em = emf.createEntityManager();
        List<Entity> list = em.createQuery("select e from "+ clazz.getSimpleName() + " e", clazz).getResultList();
        em.close();
        return list;
    }

    public Entity find(Key id)
    {
        EntityManager em = emf.createEntityManager();
        Entity entity = em.find(clazz, id);
        em.close();
        return entity;
    }
    protected EntityManagerFactory getEmf()
    {
        return emf;
    }
}
