package desafio;

import java.util.ArrayList;
import java.util.List;

class Department {

    private final int id;
    private final String name;
    private final List<Employee> employees;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employee.setDepartment(this);
        this.employees.add(employee);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;

        Department that = (Department) o;

        return this.id == that.id &&
               this.name.equals(that.name) &&
               this.employees.equals(that.employees);
    }

    @Override
    public final int hashCode() {
        int hash = id;
        final int prime = 31;

        hash *= prime + name.hashCode();
        hash *= prime + employees.hashCode();

        if (hash < 0) hash = -hash;

        return hash;
    }

    // Department.java
    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();

        // 1) Nome do departamento + dois pontos + quebra de linha
        sb.append(name).append(":\n");

        // 2) Para cada funcionário, chamar e.toString() com indentação
        for (Employee e : employees) {
            sb.append("    ")
                    .append(e.toString())
                    .append("\n"); // quebra de linha ao final de cada funcionário
        }

        return sb.toString();
    }

}