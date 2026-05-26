import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class RandomEventManager {
    private List<Event> events;
    private Random random;

    public RandomEventManager() {
        this.events = new ArrayList<>();
        this.events.add(new HappyClientEvent());
        this.events.add(new ServerBurnoutEvent());
        this.random = new Random();
    }

    public void checkAndTriggerEvent(Company company, JFrame parentFrame) {
        // 20% chance to trigger a random event every week
        if (random.nextInt(100) < 20) {
            Event event = events.get(random.nextInt(events.size()));
            try {
                event.apply(company);
                JOptionPane.showMessageDialog(
                    parentFrame,
                    event.getDescription(),
                    "Random Event: " + event.getName(),
                    JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                System.err.println("Failed to apply event: " + ex.getMessage());
            }
        }
    }
}

