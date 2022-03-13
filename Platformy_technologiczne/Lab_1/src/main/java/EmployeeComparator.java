import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee>
{
    @Override
    public int compare(Employee o1, Employee o2)
    {
        int res = o1.getClearanceLevel() - o2.getClearanceLevel();
        if(res !=0 )
            return res;

        res = o1.getName().compareTo(o2.getName());
        if(res != 0)
            return res;

        res= o1.getJobTitle().compareTo(o2.getJobTitle());
        if(res!= 0)
            return res;

        res=o1.getDepartment().compareTo(o2.getDepartment());
        if(res!= 0)
            return res;

        res=o1.getSalary()-o2.getSalary();
        return res;
    }
}
