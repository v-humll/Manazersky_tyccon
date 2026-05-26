public class ServerBurnoutEvent extends Event {
    public ServerBurnoutEvent() {
        super("Server Burnout", "Your primary server overheated and burned out. It costs $400 to replace it!");
    }

    @Override
    public void apply(Company company) throws Exception {
        company.addMoney(-400);
    }
}

