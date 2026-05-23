import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Company company = new Company();
            MainFrame mainFrame = new MainFrame(company);

            mainFrame.updateCompanyInfo(company);
            mainFrame.setVisible(true);

            GameManager gameManager = new GameManager(company, mainFrame);
            gameManager.start();
        });
    }
}