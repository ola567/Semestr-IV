import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Tower
{
    public Tower(String name, int height)
    {
        this.name = name;
        this.height = height;
    }

    @Id
    @Getter
    private String name;

    @Getter
    @Setter
    private int height;

    @Getter
    @Setter
    @OneToMany(mappedBy = "tower", fetch = FetchType.EAGER)
    private List<Mage> mages;

    public void addMage(Mage mage)
    {
        mages.add(mage);
    }

    @PreRemove
    private void preRemove()
    {
        for (Mage mage: mages)
        {
            mage.setTower(null);
        }
    }

    @Override
    public String toString() {
        return "Tower{" +
                "name='" + name + '\'' +
                ", height=" + height +
                '}';
    }

    public String getName() {
        return name;
    }
}
