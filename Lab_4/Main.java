import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4");

        MageRepo mageRepo = new MageRepo(emf);
        TowerRepo towerRepo = new TowerRepo(emf);

        int n = 0;
        String name = "";

        while(true)
        {
            String command = scanner.nextLine();
            switch (command)
            {
                case "addMage":
                    System.out.println("Mage name: ");
                    name = scanner.nextLine();
                    System.out.println("Mage level: ");
                    String l = scanner.nextLine();
                    int level = Integer.parseInt(l);
                    System.out.println("Name of the tower: ");
                    String tower = scanner.nextLine();
                    Tower tower1 = towerRepo.find(tower);

                    Mage newMage = new Mage(name, level);
                    newMage.setTower(tower1);
                    mageRepo.add(newMage);
                    System.out.println("Mage added");
                    break;
                case "addTower":
                    System.out.println("Tower name: ");
                    name = scanner.nextLine();
                    System.out.println("Tower height: ");
                    int height = scanner.nextInt();

                    Tower newTower = new Tower(name, height);
                    towerRepo.add(newTower);
                    System.out.println("Tower added");
                    break;
                case "deleteMage":
                    System.out.println("Mage name:");
                    name = scanner.nextLine();
                    mageRepo.delete(mageRepo.find(name));
                    System.out.println("Mage " + name + " deleted.");
                    break;
                case "deleteTower":
                    System.out.println("Tower name: ");
                    name = scanner.nextLine();
                    towerRepo.delete(towerRepo.find(name));
                    System.out.println("Tower "+ name + " deleted.");
                    break;
                case "printMages":
                    List<Mage> mages = mageRepo.findAll();
                    System.out.println(mages);
                    break;
                case "printTowers":
                    List<Tower> towers = towerRepo.findAll();
                    System.out.println(towers);
                    break;
                case "query":
                    System.out.println("Queries: 1. Pobranie wszystkich magów z poziomem większym niż \n 2. pobranie wszystkie wież niższych niż \n 3. pobranie wszystkich magów z poziomem wyższym niż z danej wieży");
                    int query = scanner.nextInt();
                    if (query == 1)
                    {
                        n = scanner.nextInt();
                        System.out.println(mageRepo.findMagesWithHigherLevel(n));
                    }
                    if (query == 2)
                    {
                        n = scanner.nextInt();
                        System.out.println(towerRepo.findTowersWithLowerHeight(n));
                    }
//                    if (query == 3)
//                    {
//                        name = scanner.nextLine();
//                        Tower t = towerRepo.find(name);
//                        n = scanner.nextInt();
//
//                        System.out.println(mageRepo.findMagesWithHigherLevelAmongSameTower(n, t));
//                    }
                    break;
                case "end":
                    emf.close();
                    break;
            }
        }
    }
}

