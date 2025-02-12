import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class MageRepository
{
    private Collection<Mage> collections;

    public MageRepository()
    {
        this.collections = new ArrayList<>();
    }

    public Optional<Mage> find(String name)
    {
        Optional<Mage> mag = Optional.empty();
        for (Mage mage: collections)
        {
            if (mage.getName().equals(name))
            {
                mag = Optional.of(mage);
            }
        }
        if(mag.isEmpty())
        {
            return Optional.empty();
        }
        else
        {
            return mag;
        }
    }
    public void delete(String name)
    {
        Optional<Mage> mage = this.find(name);
        if(mage.isEmpty())
        {
            throw new IllegalArgumentException("not found");
        }
        else
        {
            collections.remove(mage);
        }
    }
    public void save(Mage mage)
    {
        Optional<Mage> mag = this.find(mage.getName());
        if(mag.isEmpty())
        {
            collections.add(mage);
        }
        else
        {
            throw new IllegalArgumentException("Mage already exists");
        }
    }
}
