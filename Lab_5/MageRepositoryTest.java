import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.*;

public class MageRepositoryTest
{
    @Test
    public void save_existingObject_throwIllegalArgumentException()
    {
        MageRepository mageRepository = new MageRepository();
        mageRepository.save(new Mage("Ola", 12));
        mageRepository.save(new Mage("Kasia", 13));

        assertThatIllegalArgumentException().isThrownBy(()->{ mageRepository.save(new Mage("Ola", 12));});
    }
    @Test
    public void find_existingObject_returnFoundedObject()
    {
        MageRepository mageRepository = new MageRepository();
        mageRepository.save(new Mage("Ola", 12));
        mageRepository.save(new Mage("Kasia", 13));

        Optional<Mage> m = mageRepository.find("Ola");

        assertThat(m).isNotEmpty();
    }

    @Test
    public void find_notExistingObject_returnEmptyObject()
    {
        MageRepository mageRepository = new MageRepository();
        mageRepository.save(new Mage("Ola", 12));
        mageRepository.save(new Mage("Kasia", 13));

        Optional<Mage> m = mageRepository.find("Kacper");

        assertThat(m).isEmpty();
    }

    @Test
    public void delete_notExistingObject_throwIllegalArgumentException()
    {
        MageRepository mageRepository = new MageRepository();
        mageRepository.save(new Mage("Ola", 12));
        mageRepository.save(new Mage("Kasia", 13));

        assertThatIllegalArgumentException().isThrownBy(()->{ mageRepository.delete("Olka");});
    }
}
