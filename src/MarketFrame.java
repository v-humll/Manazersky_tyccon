import javax.swing.*;
import java.awt.*;

public class MarketFrame extends JFrame {
    public MarketFrame() {
        setTitle("IT Startup Tycoon - Market");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel marketPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        marketPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //hire emplozees
        JPanel hirePanel = new JPanel();
        hirePanel.setBorder(BorderFactory.createTitledBorder("Hire Employees"));
        hirePanel.add(new JButton("Hire Developer"));
        hirePanel.add(new JButton("Hire Designer"));
        hirePanel.add(new JButton("Hire Tester"));
        marketPanel.add(hirePanel);

        //buy office
        JPanel officePanel = new JPanel();
        officePanel.setBorder(BorderFactory.createTitledBorder("Office Expansion"));
        officePanel.add(new JButton("Buy Bigger Office"));
        marketPanel.add(officePanel);

        //buy upgrades
        JPanel upgradePanel = new JPanel();
        upgradePanel.setBorder(BorderFactory.createTitledBorder("Upgrades"));
        upgradePanel.add(new JButton("Buy Global Upgrade"));
        marketPanel.add(upgradePanel);

        add(marketPanel, BorderLayout.CENTER);

        //close button
        JPanel bottomPanel = new JPanel();
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        bottomPanel.add(closeButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
