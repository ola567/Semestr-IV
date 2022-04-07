import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class MageControllerTest
{
    @Test
    public void delete_notExistingObject_returnNotFound()
    {
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);
        mageRepository.save(new Mage("Ola", 12));
        mageRepository.save(new Mage("Kasia", 13));

        doReturn(Optional.empty()).when(mageRepository).find("Kamil");
        assertThat(mageController.delete("Kamil")).isEqualTo("not found");
    }

    @Test
    public void delete_existingObject_returnDone()
    {
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);
        mageRepository.save(new Mage("Ola", 12));
        mageRepository.save(new Mage("Kasia", 13));

        Mage m = new Mage("Ola", 12);
        doReturn(Optional.of(m)).when(mageRepository).find(m.getName());
        assertThat(mageController.delete(m.getName())).isEqualTo("done");
    }

    @Test
    public void find_notExistingObject_returnNotFound()
    {
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);

        doReturn(Optional.empty()).when(mageRepository).find("Kamil");
        assertThat(mageController.find("Kamil")).isEqualTo("not found");
    }

    @Test
    public void find_existingObject_returnName()
    {
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);
        mageRepository.save(new Mage("Ola", 12));

        Mage mage = new Mage("Ola", 12);
        doReturn(Optional.of(mage)).when(mageRepository).find("Ola");
        assertThat(mageController.find(mage.getName())).isEqualTo(mage.getName());
    }

    @Test
    public void save_newObject_returnDone()
    {
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);

        doReturn(Optional.empty()).when(mageRepository).find("Ola");
        assertThat(mageController.save(new MageDto("Ola", 12))).isEqualTo("done");
    }

    @Test
    public void save_existingObject_returnBadRequest()
    {
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);
        mageRepository.save(new Mage("Ola", 12));

        Mage m = new Mage("Ola", 12);
        doReturn(Optional.of(m)).when(mageRepository).find(m.getName());
        assertThat(mageController.save(new MageDto(m.getName(), m.getLevel()))).isEqualTo("bad request");
    }
}
