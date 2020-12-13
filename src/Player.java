public class Player {
    private long balance;
    private Modes gameChoice;
    private int numberChoice;
    private long bettingAmount;

    //constructor only for testing
    Player(long number){
        this.balance = number;
    }
    public long getBalance(){
        return this.balance;
    }

    public Modes getGameChoice() {
        return gameChoice;
    }

    public void setGameChoice(Modes gameChoice) {
        this.gameChoice = gameChoice;
    }

    public int getNumberChoice() {
        return numberChoice;
    }

    public void setNumberChoice(int numberChoice) {
        this.numberChoice = numberChoice;
    }

    public long getBettingAmount() {
        return bettingAmount;
    }

    public void setBettingAmount(long bettingAmount) {
        this.bettingAmount = bettingAmount;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
