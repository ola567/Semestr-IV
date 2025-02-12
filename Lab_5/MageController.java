import java.util.Optional;

public class MageController
{
    private MageRepository repository;

    public MageController(MageRepository repository)
    {
        this.repository = repository;
    }

    public String find(String name)
    {
        Optional<Mage> mage = repository.find(name);
        if (mage.isEmpty())
        {
            return "not found";
        }
        else
            return mage.get().toString();
    }

    public String delete(String name)
    {
        Optional<Mage> mage = repository.find(name);
        if (mage.isEmpty())
        {
            return "not found";
        }
        else
        {
            repository.delete(name);
            return "done";
        }
    }

    public String save(MageDto mageDto)
    {
        Optional<Mage> mage = repository.find(mageDto.getName());
        if(mage.isEmpty())
        {
            repository.save(new Mage(mageDto.getName(), mageDto.getLevel()));
            return "done";
        }
        else
        {
            return  "bad request";
        }
    }
}
