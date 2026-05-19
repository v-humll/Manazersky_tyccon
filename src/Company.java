import java.util.ArrayList;
import java.util.List;

public class Company {
    private int money;
    private int week;
    private int capacity;
    private List<Employee> employees;

    public Company() {
        this.money = 1000; // Start with some initial budget
        this.week = 1;
        this.capacity = 5;
        this.employees = new ArrayList<>();
        // Start with one default employee generating passive income
        this.employees.add(new Developer("Alice (Junior)", 80));
    }

    public void addMoney(int amount) {
        this.money += amount;
    }

    public void removeMoney(int amount) throws InsufficientFundsException {
        if (this.money < amount) {
            throw new InsufficientFundsException();
        }
        this.money -= amount;
    }

    public int getMoney() {
        return money;
    }

    public int getWeek() {
        return week;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void hireEmployee(Employee emp) throws InsufficientFundsException {
        if (employees.size() >= capacity) {
            return;
        }
        employees.add(emp);
    }

    public void paySalaries() throws BankruptcyException {
        int totalSalaries = 0;
        for (Employee emp : employees) {
            totalSalaries += emp.getSalary();
        }
        this.money -= totalSalaries;
        if (this.money < 0) {
            throw new BankruptcyException();
        }
    }
}

