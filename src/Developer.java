public class Developer extends Employee {
    public Developer(String name, int baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public String getRoleName() {
        return "Developer";
    }

    @Override
    public int generatePassiveIncome() {
        return 15 * level;
    }
}

