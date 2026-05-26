public class HappyClientEvent extends Event {
    public HappyClientEvent() {
        super("Happy Client", "An extremely satisfied client sent a bonus tip of $500!");
    }

    @Override
    public void apply(Company company) throws Exception {
        company.addMoney(500);
    }
}

