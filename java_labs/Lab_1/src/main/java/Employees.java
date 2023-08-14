import java.util.*;

public class Employees
{
    private String sorting;
    private Set<Employee> employees;
    private Map<Employee, Integer> map;

    public Employees(String sorting) {
        this.sorting = sorting;

        if (sorting == "normal_sorting")
        {
            employees = new TreeSet<>();
            map=new TreeMap<>();
        }
        else if(sorting == "alternative_sorting")
        {
            employees = new TreeSet<>(new EmployeeComparator());
            map=new TreeMap<>(new EmployeeComparator());
        }
        else
        {
            employees = new HashSet<>();
            map=new HashMap<>();
        }
    }

    public void addWorker(Employee e)
    {
        employees.add(e);
    }
    public String getSorting() {
        return sorting;
    }
    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

    public Map<Employee, Integer> descenantsStatistic()
    {
        for (Employee employee:employees)
        {
            int n=employee.countNumberOfSubordinates(0);
            map.put(employee, n);
        }
        return map;
    }
}
