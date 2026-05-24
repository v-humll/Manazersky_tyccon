import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProjectFrame extends JFrame {
    private Company company;
    private MainFrame mainFrame;
    private JList<Project> projectList;
    private DefaultListModel<Project> projectListModel;

    public ProjectFrame(Company company, MainFrame mainFrame) {
        this.company = company;
        this.mainFrame = mainFrame;

        setTitle("IT Startup Tycoon - Projects");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel projectsPanel = new JPanel(new BorderLayout());
        projectsPanel.setBorder(BorderFactory.createTitledBorder("Available Projects"));
        
        projectListModel = new DefaultListModel<>();
        // Populate standard projects
        projectListModel.addElement(new Project("Project Alpha", 300, "Developer", 1, 5));
        projectListModel.addElement(new Project("Project Beta", 800, "Designer", 2, 10));
        projectListModel.addElement(new Project("Project Gamma", 1500, "Tester", 3, 15));
        projectListModel.addElement(new Project("Project Delta", 500, "Designer", 1, 7));
        projectListModel.addElement(new Project("Project Epsilon", 1000, "Developer", 2, 12));

        projectList = new JList<>(projectListModel);
        projectsPanel.add(new JScrollPane(projectList), BorderLayout.CENTER);
        add(projectsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton acceptButton = new JButton("Accept Project");
        JButton closeButton = new JButton("Close");

        acceptButton.addActionListener(e -> {
            Project selectedProject = projectList.getSelectedValue();
            if (selectedProject == null) {
                JOptionPane.showMessageDialog(this, "Please select a project to accept.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (company.getActiveProject() != null) {
                JOptionPane.showMessageDialog(this, "You already have an active project! Finish it first.", "Active Project Running", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validate qualifications
            boolean qualified = false;
            for (Employee emp : company.getEmployees()) {
                if (emp.getRoleName().equalsIgnoreCase(selectedProject.getRequiredRole()) && emp.getLevel() >= selectedProject.getRequiredLevel()) {
                    qualified = true;
                    break;
                }
            }

            if (!qualified) {
                String lvlStr = selectedProject.getRequiredLevel() == 1 ? "Junior" : (selectedProject.getRequiredLevel() == 2 ? "Mid" : "Senior");
                JOptionPane.showMessageDialog(this, "You do not have a qualified employee for this project!\nRequires: " + lvlStr + " " + selectedProject.getRequiredRole() + " (or higher).", "Requirement Not Met", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Accept project
            company.setActiveProject(new Project(selectedProject.getName(), selectedProject.getReward(), selectedProject.getRequiredRole(), selectedProject.getRequiredLevel(), selectedProject.getDuration()));
            mainFrame.updateCompanyInfo(company);
            JOptionPane.showMessageDialog(this, "Project accepted! Your team is working on it.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        closeButton.addActionListener(e -> dispose());

        bottomPanel.add(acceptButton);
        bottomPanel.add(closeButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}

