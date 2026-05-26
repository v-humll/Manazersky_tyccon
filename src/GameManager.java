import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameManager {
    private Company company;
    private MainFrame mainFrame;
    private Timer gameTimer;
    private RandomEventManager randomEventManager;

    public GameManager(Company company, MainFrame mainFrame) {
        this.company = company;
        this.mainFrame = mainFrame;
        this.randomEventManager = new RandomEventManager();
        
        // Check every 1sec -> add passive income, add XP, and advance week every 10 ticks
        gameTimer = new Timer(1000, new ActionListener() {
            private int tickCount = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Generate passive income and add experience to each employee
                int totalPassiveIncome = 0;
                for (Employee emp : company.getEmployees()) {
                    int income = emp.generatePassiveIncome();
                    
                    // Apply global upgrades multiplier
                    double multiplier = 1.0;
                    if (company.getUpgrades() != null) {
                        for (Upgrade u : company.getUpgrades()) {
                            if (u.isPurchased()) {
                                multiplier += (u.getIncomeMultiplier() - 1.0);
                            }
                        }
                    }
                    totalPassiveIncome += (int)(income * multiplier);
                    
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
                    
                    // Trigger weekly random event
                    try {
                        randomEventManager.checkAndTriggerEvent(company, mainFrame);
                    } catch (Exception ex) {
                        System.err.println("Random event failed: " + ex.getMessage());
                    }
                    
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

                // 4. Check victory condition (e.g. reach $5000 budget to enter stock market)
                if (company.getMoney() >= 5000) {
                    gameTimer.stop();
                    JOptionPane.showMessageDialog(
                        mainFrame,
                        "Congratulations! Your company has reached $" + company.getMoney() + " and successfully entered the Stock Market!\nYou won the game!",
                        "Victory - IPO Achieved!",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    System.exit(0);
                }

                // 5. Check if company went bankrupt due to random event
                if (company.getMoney() < 0) {
                    gameTimer.stop();
                    JOptionPane.showMessageDialog(
                        mainFrame, 
                        "Your company went bankrupt! You ran out of money (Balance: $" + company.getMoney() + ").\nGame Over!",
                        "Bankruptcy - Game Over",
                        JOptionPane.ERROR_MESSAGE
                    );
                    System.exit(0);
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


