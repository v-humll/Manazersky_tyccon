public class Company {
    private int money;
    private int week;
    private int capacity;

    public Company() {
        this.money = 0;
        this.week = 1;
        this.capacity = 5;
    }

    public void addMoney(int amount) {
        this.money += amount;
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
}
