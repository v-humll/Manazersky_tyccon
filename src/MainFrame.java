import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Company company;
    private JLabel financeLabel;
    private JLabel weekLabel;
    private JLabel capacityLabel;
    private JLabel projectLabel;
    private DefaultListModel<String> employeeListModel;

    public MainFrame(Company company) {
        this.company = company;

        setTitle("IT Tycoon - Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Top panel - Company stats (2 rows, 2 columns)
        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        financeLabel = new JLabel("Finance: $0", SwingConstants.CENTER);
        weekLabel = new JLabel("Week: 1", SwingConstants.CENTER);
        capacityLabel = new JLabel("Capacity: 1/5", SwingConstants.CENTER);
        projectLabel = new JLabel("Active Project: None", SwingConstants.CENTER);
        
        statsPanel.add(financeLabel);
        statsPanel.add(weekLabel);
        statsPanel.add(capacityLabel);
        statsPanel.add(projectLabel);
        add(statsPanel, BorderLayout.NORTH);

        // Center panel - Employees
        JPanel employeesPanel = new JPanel(new BorderLayout());
        employeesPanel.setBorder(BorderFactory.createTitledBorder("Employees"));
        employeeListModel = new DefaultListModel<>();
        JList<String> employeeList = new JList<>(employeeListModel);
        employeesPanel.add(new JScrollPane(employeeList), BorderLayout.CENTER);
        add(employeesPanel, BorderLayout.CENTER);

        // Bottom panel - Navigation buttons
        JPanel navPanel = new JPanel();
        JButton marketButton = new JButton("Open Market");
        JButton projectsButton = new JButton("Open Projects");

        marketButton.addActionListener(e -> new MarketFrame(company, this).setVisible(true));
        projectsButton.addActionListener(e -> new ProjectFrame(company, this).setVisible(true));

        navPanel.add(marketButton);
        navPanel.add(projectsButton);
        add(navPanel, BorderLayout.SOUTH);
    }

    public void updateCompanyInfo(Company company) {
        financeLabel.setText("Finance: $" + company.getMoney());
        weekLabel.setText("Week: " + company.getWeek());
        capacityLabel.setText("Capacity: " + company.getEmployees().size() + "/" + company.getCapacity());
        
        if (company.getActiveProject() != null) {
            Project p = company.getActiveProject();
            projectLabel.setText("Active Project: " + p.getName() + " (" + p.getProgress() + "/" + p.getDuration() + "s) - $" + p.getReward());
        } else {
            projectLabel.setText("Active Project: None");
        }

        employeeListModel.clear();
        for (Employee emp : company.getEmployees()) {
            String levelStr = "Junior";
            if (emp.getLevel() == 2) {
                levelStr = "Mid";
            } else if (emp.getLevel() == 3) {
                levelStr = "Senior";
            }
            employeeListModel.addElement(emp.getName() + " (" + emp.getRoleName() + " - " + levelStr + ") - Income: $" + emp.generatePassiveIncome() + "/s, Salary: $" + emp.getSalary() + "/week, XP: " + emp.getExperience());
        }
    }
}
