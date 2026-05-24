public class Project {
    private String name;
    private int reward;
    private String requiredRole;
    private int requiredLevel;
    private int duration;
    private int progress;

    public Project(String name, int reward, String requiredRole, int requiredLevel, int duration) {
        this.name = name;
        this.reward = reward;
        this.requiredRole = requiredRole;
        this.requiredLevel = requiredLevel;
        this.duration = duration;
        this.progress = 0;
    }

    public String getName() {
        return name;
    }

    public int getReward() {
        return reward;
    }

    public String getRequiredRole() {
        return requiredRole;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public int getDuration() {
        return duration;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void incrementProgress() {
        if (progress < duration) {
            progress++;
        }
    }

    public boolean isCompleted() {
        return progress >= duration;
    }

    @Override
    public String toString() {
        String lvlStr = requiredLevel == 1 ? "Junior" : (requiredLevel == 2 ? "Mid" : "Senior");
        return name + " (Req: " + lvlStr + " " + requiredRole + ") - Reward: $" + reward + " - Duration: " + duration + "s";
    }
}

