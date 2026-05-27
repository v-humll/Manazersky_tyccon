public class Upgrade implements IUpgradable {
    private String name;
    private int cost;
    private double incomeMultiplier;
    private boolean purchased;

    public Upgrade(String name, int cost, double incomeMultiplier) {
        this.name = name;
        this.cost = cost;
        this.incomeMultiplier = incomeMultiplier;
        this.purchased = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public double getIncomeMultiplier() {
        return incomeMultiplier;
    }

    @Override
    public boolean isPurchased() {
        return purchased;
    }

    @Override
    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    @Override
    public String toString() {
        String status = purchased ? " [Purchased]" : " ($" + cost + ")";
        return name + status + " - +" + (int)((incomeMultiplier - 1.0) * 100) + "% Passive Income";
    }
}

