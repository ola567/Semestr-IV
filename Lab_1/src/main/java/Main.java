import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        String sorting = args[0];
        Employees employees = new Employees(sorting);
        Map<Employee, Integer> map;

        Employee e1=new Employee("Mariusz", "kierownik", "biuro", 1, 8000);
        Employee e2=new Employee("Maciej", "pracownik fizyczny", "dział produkcji", 3, 2000);
        Employee e3=new Employee("Dominik", "pracownik fizyczny", "dział produkcji", 3, 2000);
        Employee e4=new Employee("Oktawiusz", "pracownik fizyczny", "dział produkcji", 4, 2000);
        Employee e5=new Employee("Grzegorz", "koordynator produkcji", "dział produkcji", 4, 4000);
        Employee e6=new Employee("Jacek", "dostawca", "dział transportu", 3, 3500);
        Employee e7=new Employee("Jaromir", "logistyk", "dział transportu", 3, 4000);
        Employee e8=new Employee("Irena", "osoba zajmujaca się pormocją", "dział promocji i marketingu", 2, 5000);
        Employee e9=new Employee("Małgorzata", "sprzątaczka", " ", 4, 5000);
        Employee e10=new Employee("Juliusz", "prezes", "biuro", 1, 10000);

        e10.addSubordinate(e1);
        e1.addSubordinate(e7);
        e7.addSubordinate(e2);
        e7.addSubordinate(e3);
        e7.addSubordinate(e4);

        employees.addWorker(e1);
        employees.addWorker(e2);
        employees.addWorker(e3);
        employees.addWorker(e4);
        employees.addWorker(e5);
        employees.addWorker(e6);
        employees.addWorker(e7);
        employees.addWorker(e8);
        employees.addWorker(e9);
        employees.addWorker(e10);

        e10.print("");
        System.out.println("\n");
        map = employees.descenantsStatistic();
        for (Map.Entry<Employee, Integer> pair : map.entrySet())
        {
            System.out.println("Worker: " + pair.getKey().toString() + " Subordinates: " + pair.getValue());
        }
    }
}
