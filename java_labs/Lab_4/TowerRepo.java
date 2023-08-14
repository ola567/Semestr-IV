import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

//repositories for queries
public class TowerRepo extends JPARepo<Tower, String>
{
    public TowerRepo(EntityManagerFactory emf)
    {
        super(emf, Tower.class);
    }

    public List<Tower> findTowersWithLowerHeight(int height)
    {
        EntityManager em = getEmf().createEntityManager();
        List<Tower> towers = (List<Tower>) em.createQuery("select c from Tower c where c.height < :height", Tower.class)
                .setParameter("level", height)
                .getResultList();
        em.close();
        return towers;
    }
}
