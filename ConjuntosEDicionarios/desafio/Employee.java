package desafio;

import static java.lang.Double.compare;
import static java.lang.String.format;

class Employee {

    private final int id;
    private final String name;
    private final double salary;
    private Department department;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        Employee that = (Employee) o;

        if (this.id != that.id) return false;
        if (!this.name.equals(that.name)) return false;
        if (compare(this.salary, that.salary) != 0) return false;
        if (this.department == null && that.department != null) return false;
        if (this.department != null && that.department == null) return false;

        if (this.department != null) return this.department.getId() == that.department.getId();

        return true;
    }

    @Override
    public final int hashCode() {
        int hash = id;
        final int prime = 31;

        hash *= prime + name.hashCode();
        hash *= prime + Double.hashCode(salary);

        if (department != null) hash *= prime + department.getId();

        if (hash < 0) hash = -hash;

        return hash;
    }

    @Override
    public final String toString() {
        return format("%d: %s, $ %.2f", id, name, salary);
    }
}

