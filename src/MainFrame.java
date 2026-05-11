import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JLabel financeLabel;
    private JLabel weekLabel;
    private JLabel capacityLabel;
    private DefaultListModel<String> employeeListModel;

    public MainFrame() {
        setTitle("IT Tycoon - Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Top panel - Company stats
        JPanel statsPanel = new JPanel(new GridLayout(1, 3));
        financeLabel = new JLabel("Finance: $0", SwingConstants.CENTER);
        weekLabel = new JLabel("Week: 1", SwingConstants.CENTER);
        capacityLabel = new JLabel("Capacity: 1/5", SwingConstants.CENTER);
        
        statsPanel.add(financeLabel);
        statsPanel.add(weekLabel);
        statsPanel.add(capacityLabel);
        add(statsPanel, BorderLayout.NORTH);

        // Center panel - Employees
        JPanel employeesPanel = new JPanel(new BorderLayout());
        employeesPanel.setBorder(BorderFactory.createTitledBorder("Employees"));
        employeeListModel = new DefaultListModel<>();
        employeeListModel.addElement("Employee 1 (Developer) - Passive Income");
        JList<String> employeeList = new JList<>(employeeListModel);
        employeesPanel.add(new JScrollPane(employeeList), BorderLayout.CENTER);
        add(employeesPanel, BorderLayout.CENTER);

        // Bottom panel - Navigation buttons
        JPanel navPanel = new JPanel();
        JButton marketButton = new JButton("Open Market");
        JButton projectsButton = new JButton("Open Projects");

        marketButton.addActionListener(e -> new MarketFrame().setVisible(true));
        projectsButton.addActionListener(e -> new ProjectFrame().setVisible(true));

        navPanel.add(marketButton);
        navPanel.add(projectsButton);
        add(navPanel, BorderLayout.SOUTH);
    }

    public void updateCompanyInfo(Company company) {
        financeLabel.setText("Finance: $" + company.getMoney());
        weekLabel.setText("Week: " + company.getWeek());
        // For now hardcoded 1 employee
        capacityLabel.setText("Capacity: 1/" + company.getCapacity());
    }
}
