public abstract class Employee implements IPayable {
    protected String name;
    protected int baseSalary;
    protected int experience;
    protected int level;

    public Employee(String name, int baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.experience = 0;
        this.level = 1;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getSalary() {
        return baseSalary * level;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public void addExperience(int amount) {
        this.experience += amount;
        // Simple level-up system: 100 XP per level, max level 3 (Junior, Mid, Senior)
        int newLevel = 1 + (this.experience / 100);
        if (newLevel > 3) {
            newLevel = 3;
        }
        this.level = newLevel;
    }

    public abstract String getRoleName();
    public abstract int generatePassiveIncome();
}

