import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameManager {
    private Company company;
    private MainFrame mainFrame;
    private Timer gameTimer;

    public GameManager(Company company, MainFrame mainFrame) {
        this.company = company;
        this.mainFrame = mainFrame;
        
        //check every 1sec -> add passive income
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                company.addMoney(10);
                mainFrame.updateCompanyInfo(company);
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
