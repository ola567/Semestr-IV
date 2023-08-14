import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Mage
{
    public Mage(String name, int level)
    {
        this.name = name;
        this.level = level;
    }

    @Getter
    @Id
    private String name;

    @Getter
    @Setter
    private int level;

    @Getter
    @ManyToOne
    private Tower tower;

    public void setTower(Tower tower)
    {
        this.tower = tower;
        if (tower != null)
            tower.addMage(this);
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", tower=" + tower +
                '}';
    }
}
