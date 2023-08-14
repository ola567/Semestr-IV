import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

//repository for queries
public class MageRepo extends JPARepo<Mage, String>
{
    public MageRepo(EntityManagerFactory emf)
    {
        super(emf, Mage.class);
    }

    public List<Mage> findMagesWithHigherLevel(int level)
    {
        EntityManager em = getEmf().createEntityManager();
        List<Mage> mages = (List<Mage>) em.createQuery("select c from Mage c where c.level > :level", Mage.class)
                .setParameter("level", level)
                .getResultList();
        em.close();
        return mages;
    }

//    public List<Mage> findMagesWithHigherLevelAmongSameTower(int level, Tower tower)
//    {
//        EntityManager em = getEmf().createEntityManager();
//        List<Mage> mages = (List<Mage>) em.createQuery("select m from Mage where m.tower.equals(:tower) and m.level > :level", Mage.class)
//                .setParameter("tower", tower)
//                .setParameter("level", level)
//                .getResultList();
//        em.close();
//        return mages;
//    }
}
