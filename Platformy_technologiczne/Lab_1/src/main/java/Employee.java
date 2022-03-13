import java.util.*;

public class Employee implements Comparable<Employee>
{
    private String name;
    private String jobTitle;
    private String department;
    private int clearanceLevel;
    private int salary;
    private Set<Employee> subordinates;

    public Employee(String name, String jobTitle, String department, int clearanceLevel, int salary)
    {
        this.name = name;
        this.jobTitle = jobTitle;
        this.department = department;
        this.clearanceLevel = clearanceLevel;
        this.salary = salary;
        this.subordinates = new TreeSet<Employee>();
    }

    public void addSubordinate(Employee e)
    {
        subordinates.add(e);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
               // ", jobTitle='" + jobTitle + '\'' +
               // ", department='" + department + '\'' +
                ", clearanceLevel=" + clearanceLevel +
                ", salary=" + salary +
               // ", subordinates=" + subordinates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return clearanceLevel == employee.clearanceLevel && salary == employee.salary && name.equals(employee.name) && jobTitle.equals(employee.jobTitle) && department.equals(employee.department) && subordinates.equals(employee.subordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, jobTitle, department, clearanceLevel, salary, subordinates);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getClearanceLevel() {
        return clearanceLevel;
    }

    public void setClearanceLevel(int clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Set<Employee> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(Set<Employee> subordinates) {
        this.subordinates = subordinates;
    }

    @Override
    public int compareTo(Employee other)
    {
        int ret = name.compareTo(other.name);
        if(ret !=0 )
            return ret;

        ret = jobTitle.compareTo(other.jobTitle);
        if (ret !=0 )
            return ret;

        ret = clearanceLevel - other.clearanceLevel;
        if (ret !=0 )
            return ret;

        ret =salary-other.salary;
        return ret;
    }

    public void print(String pref)
    {
        pref += "-";
        String temp = pref;
        temp+=this.toString();
        System.out.println(temp);
        for(Employee employee : subordinates)
            employee.print(pref);
    }

    public int countNumberOfSubordinates(int count)
    {
        for(Employee employee: subordinates)
        {
            count=employee.countNumberOfSubordinates(count);
            count+=1;
        }
        return count;
    }
}
