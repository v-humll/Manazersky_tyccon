public class Designer extends Employee {
    public Designer(String name, int baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public String getRoleName() {
        return "Designer";
    }

    @Override
    public int generatePassiveIncome() {
        return 10 * level;
    }
}

