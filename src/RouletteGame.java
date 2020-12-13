import java.util.Random;

public class RouletteGame{
    private Player player;
    private final Random numberGenerator = new Random();
    private int rouletteNumber;
    private PlayerInput pi = new PlayerInput();

    String stringInfoGame = "Enter the number:\n";
    String ruleOne = "Number: from  0 - 36";
    String ruleTwo = "0: Red\n1: Black";
    String ruleThree = "0: Bet on first Column\n" +
            "1: Bet on Second Column\n" +
            "2: Bet on Third Column";

    RouletteGame(Player player){
        this.player = player;
    }

    public void start(){
        boolean notEnoughBalance = false;
        do{
            boolean won = false;
            rouletteNumber = getRandomNumber();
            player.setGameChoice(pi.getModeSelection());
            player.setBettingAmount(pi.getBetAmount(this.player));
            switch (player.getGameChoice()){
                case NORMAL:
                    player.setNumberChoice((int)PlayerInput
                            .getUserInputs(stringInfoGame + ruleOne,
                                    (Long i) -> (i >= 0 && i <= 36)));
                    won = gameOne();
                    break;
                case COLOR:
                    player.setNumberChoice((int)PlayerInput
                            .getUserInputs(stringInfoGame + ruleTwo,
                                    (Long i) -> (i == 0 || i == 1)));
                    won = gameTwo();
                    break;
                case COLUMN:
                    player.setNumberChoice((int)PlayerInput
                            .getUserInputs(stringInfoGame + ruleThree,
                                    (Long i) -> (i >= 0 && i <= 36)));
                    won = gameThree();
                    break;
            }


            System.out.println("Let the game begin");
            System.out.println("Rolling the ball.");
            createAnticipation(10000);
            if(won){
                System.out.println("*************************************************");
                System.out.println("Hurray! Congratulations you won the game");
                player.setBalance(player.getBalance() + 2 * player.getBettingAmount());
                showCurrentBalance();
                System.out.println("*************************************************");
                createAnticipation(5000);
            }else{
                System.out.println("\uD83D\uDE22\uD83D\uDE22\uD83D\uDE22\uD83D\uDE22");
                System.out.println("Sorry you lost the game");
                player.setBalance(player.getBalance() - player.getBettingAmount());
                showCurrentBalance();
                System.out.println("\uD83D\uDE22\uD83D\uDE22\uD83D\uDE22\uD83D\uDE22");
                createAnticipation(5000);
                if(player.getBalance() < 0){
                    System.out.println("You owe us " + Math.abs(player.getBalance()));
                    System.out.println("You can't play anymore");
                    notEnoughBalance = true;
                }
            }

            if(this.player.getBalance() == 0){
                System.out.println("You are bankrupt now!!");
                notEnoughBalance = true;
            }


        }while(!notEnoughBalance && pi.repeatQuestion());
    }



    public boolean gameOne(){
        return rouletteNumber == player.getNumberChoice();
    }

    public boolean gameTwo(){
        return rouletteNumber%2 == player.getNumberChoice()%2;
    }

    public boolean gameThree(){
        return rouletteNumber%3 == player.getNumberChoice();
    }

    private int getRandomNumber(){
        return numberGenerator.nextInt(37);
    }

    private void showCurrentBalance(){
        System.out.println("Your current balance is " + this.player.getBalance());
    }


    public void createAnticipation(int i){
        try
        {
            Thread.sleep(i);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

}
