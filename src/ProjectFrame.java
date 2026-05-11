import javax.swing.*;
import java.awt.*;

public class ProjectFrame extends JFrame {
    public ProjectFrame() {
        setTitle("IT Startup Tycoon - Projects");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        JPanel projectsPanel = new JPanel(new BorderLayout());
        projectsPanel.setBorder(BorderFactory.createTitledBorder("Available Projects"));
        JList<String> projectList = new JList<>(new String[]{"Project Alpha", "Project Beta", "Project Gamma"});
        projectsPanel.add(new JScrollPane(projectList), BorderLayout.CENTER);
        add(projectsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton acceptButton = new JButton("Accept Project");
        JButton closeButton = new JButton("Close");

        closeButton.addActionListener(e -> dispose());


        bottomPanel.add(acceptButton);
        bottomPanel.add(closeButton);
        add(bottomPanel, BorderLayout.SOUTH);

    }
}
