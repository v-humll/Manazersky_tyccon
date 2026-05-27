import javax.swing.*;
import java.awt.*;

public class MarketFrame extends JFrame {
    private Company company;
    private MainFrame mainFrame;

    public MarketFrame(Company company, MainFrame mainFrame) {
        this.company = company;
        this.mainFrame = mainFrame;

        setTitle("IT Startup Tycoon - Market");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel marketPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        marketPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Hire employees panel
        JPanel hirePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        hirePanel.setBorder(BorderFactory.createTitledBorder("Hire Employees"));
        
        JButton hireDevButton = new JButton("Hire Developer ($300)");
        JButton hireDesButton = new JButton("Hire Designer ($200)");
        JButton hireTestButton = new JButton("Hire Tester ($150)");

        hireDevButton.addActionListener(e -> {
            if (company.getEmployees().size() >= company.getCapacity()) {
                JOptionPane.showMessageDialog(this, "Your office is full! Expand your office first.", "Office Full", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                company.removeMoney(300);
                Employee dev = new Developer("Dev " + (company.getEmployees().size() + 1), 100);
                company.hireEmployee(dev);
                mainFrame.updateCompanyInfo(company);
                JOptionPane.showMessageDialog(this, "Developer hired successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (InsufficientFundsException ex) {
                JOptionPane.showMessageDialog(this, "Not enough money to hire a Developer! (Cost: $300)", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
            }
        });

        hireDesButton.addActionListener(e -> {
            if (company.getEmployees().size() >= company.getCapacity()) {
                JOptionPane.showMessageDialog(this, "Your office is full! Expand your office first.", "Office Full", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                company.removeMoney(200);
                Employee des = new Designer("Des " + (company.getEmployees().size() + 1), 80);
                company.hireEmployee(des);
                mainFrame.updateCompanyInfo(company);
                JOptionPane.showMessageDialog(this, "Designer hired successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (InsufficientFundsException ex) {
                JOptionPane.showMessageDialog(this, "Not enough money to hire a Designer! (Cost: $200)", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
            }
        });

        hireTestButton.addActionListener(e -> {
            if (company.getEmployees().size() >= company.getCapacity()) {
                JOptionPane.showMessageDialog(this, "Your office is full! Expand your office first.", "Office Full", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                company.removeMoney(150);
                Employee test = new Tester("Tester " + (company.getEmployees().size() + 1), 60);
                company.hireEmployee(test);
                mainFrame.updateCompanyInfo(company);
                JOptionPane.showMessageDialog(this, "Tester hired successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (InsufficientFundsException ex) {
                JOptionPane.showMessageDialog(this, "Not enough money to hire a Tester! (Cost: $150)", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
            }
        });

        hirePanel.add(hireDevButton);
        hirePanel.add(hireDesButton);
        hirePanel.add(hireTestButton);
        marketPanel.add(hirePanel);

        // Buy office panel
        JPanel officePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        officePanel.setBorder(BorderFactory.createTitledBorder("Office Expansion"));
        
        int currentCap = company.getCapacity();
        int nextOfficeCost = 500 * (currentCap / 5);
        JButton buyOfficeButton = new JButton("Buy Bigger Office ($" + nextOfficeCost + ")");
        
        buyOfficeButton.addActionListener(e -> {
            int cap = company.getCapacity();
            int cost = 500 * (cap / 5);
            try {
                company.removeMoney(cost);
                company.setCapacity(cap + 5);
                mainFrame.updateCompanyInfo(company);
                JOptionPane.showMessageDialog(this, "Office expanded successfully! New capacity: " + (cap + 5), "Success", JOptionPane.INFORMATION_MESSAGE);
                buyOfficeButton.setText("Buy Bigger Office ($" + (500 * ((cap + 5) / 5)) + ")");
            } catch (InsufficientFundsException ex) {
                JOptionPane.showMessageDialog(this, "Not enough money to expand office! (Cost: $" + cost + ")", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
            }
        });

        officePanel.add(buyOfficeButton);
        marketPanel.add(officePanel);

        // Upgrades panel
        JPanel upgradePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        upgradePanel.setBorder(BorderFactory.createTitledBorder("Upgrades"));
        
        JComboBox<Upgrade> upgradeCombo = new JComboBox<>();
        if (company.getUpgrades() != null) {
            for (Upgrade u : company.getUpgrades()) {
                upgradeCombo.addItem(u);
            }
        }
        
        JButton buyUpgradeButton = new JButton("Buy Selected Upgrade");
        buyUpgradeButton.addActionListener(e -> {
            Upgrade selected = (Upgrade) upgradeCombo.getSelectedItem();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select an upgrade to purchase.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (selected.isPurchased()) {
                JOptionPane.showMessageDialog(this, "Upgrade already purchased!", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                company.removeMoney(selected.getCost());
                selected.setPurchased(true);
                mainFrame.updateCompanyInfo(company);
                upgradeCombo.setSelectedItem(selected);
                upgradeCombo.repaint();
                JOptionPane.showMessageDialog(this, selected.getName() + " purchased successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (InsufficientFundsException ex) {
                JOptionPane.showMessageDialog(this, "Not enough money for upgrade! (Cost: $" + selected.getCost() + ")", "Insufficient Funds", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        upgradePanel.add(upgradeCombo);
        upgradePanel.add(buyUpgradeButton);
        marketPanel.add(upgradePanel);

        add(marketPanel, BorderLayout.CENTER);

        // Close button panel
        JPanel bottomPanel = new JPanel();
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        bottomPanel.add(closeButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}

