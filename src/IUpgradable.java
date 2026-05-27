public interface IUpgradable {
    String getName();
    int getCost();
    double getIncomeMultiplier();
    boolean isPurchased();
    void setPurchased(boolean purchased);
}

