import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameManager {
    private Company company;
    private MainFrame mainFrame;
    private Timer gameTimer;

    public GameManager(Company company, MainFrame mainFrame) {
        this.company = company;
        this.mainFrame = mainFrame;
        
        // Check every 1sec -> add passive income, add XP, and advance week every 10 ticks
        gameTimer = new Timer(1000, new ActionListener() {
            private int tickCount = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Generate passive income and add experience to each employee
                int totalPassiveIncome = 0;
                for (Employee emp : company.getEmployees()) {
                    int income = emp.generatePassiveIncome();
                    totalPassiveIncome += income;
                    
                    // Add experience every tick (10 XP per second)
                    emp.addExperience(10);
                }
                company.addMoney(totalPassiveIncome);

                // 2. Process active project progress
                Project activeProj = company.getActiveProject();
                if (activeProj != null) {
                    activeProj.incrementProgress();
                    if (activeProj.isCompleted()) {
                        company.addMoney(activeProj.getReward());
                        company.setActiveProject(null);
                        JOptionPane.showMessageDialog(
                            mainFrame,
                            "Project \"" + activeProj.getName() + "\" has been completed!\nYou earned $" + activeProj.getReward() + ".",
                            "Project Completed",
                            JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }

                // 3. Tick count for week advancement
                tickCount++;
                if (tickCount >= 10) {
                    tickCount = 0;
                    company.setWeek(company.getWeek() + 1);
                    
                    // Process weekly salaries and check for bankruptcy
                    try {
                        company.paySalaries();
                    } catch (BankruptcyException ex) {
                        gameTimer.stop();
                        JOptionPane.showMessageDialog(
                            mainFrame, 
                            "Your company went bankrupt! You did not have enough money to pay weekly salaries of $" + getWeeklySalaries() + ".\nGame Over!",
                            "Bankruptcy - Game Over",
                            JOptionPane.ERROR_MESSAGE
                        );
                        System.exit(0);
                    }
                }
                
                // Update the UI
                mainFrame.updateCompanyInfo(company);
            }

            private int getWeeklySalaries() {
                int total = 0;
                for (Employee emp : company.getEmployees()) {
                    total += emp.getSalary();
                }
                return total;
            }
        });
    }

    public void start() {
        gameTimer.start();
    }
    
    public void stop() {
        gameTimer.stop();
    }
}

