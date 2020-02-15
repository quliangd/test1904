package qu.com.lambda;

import java.util.Objects;

public class Employee {
    private String name;
    private int age;
    private double price;
    private Status status;

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, int age, double price, Status status) {
        this.name = name;
        this.age = age;
        this.price = price;
        this.status = status;
    }

    public Employee(String name, int age, double price) {
        this.name = name;
        this.age = age;
        this.price = price;
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", price=" + price +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getAge() == employee.getAge() &&
                Double.compare(employee.getPrice(), getPrice()) == 0 &&
                Objects.equals(getName(), employee.getName()) &&
                status == employee.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getAge(), getPrice(), status);
    }


    public enum Status {
        FREE,
        BUSY,
        VOCATION;
    }
}
