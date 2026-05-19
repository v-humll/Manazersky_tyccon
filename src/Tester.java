public class Tester extends Employee {
    public Tester(String name, int baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public String getRoleName() {
        return "Tester";
    }

    @Override
    public int generatePassiveIncome() {
        return 8 * level;
    }
}

