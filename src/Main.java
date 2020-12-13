import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        System.out.println("Creating the player");
        RouletteGame game = new RouletteGame(new Player(getPlayerStartingBalance()));
        game.start();

    }

    static long getPlayerStartingBalance(){
        String infoString = "Enter the initial balance of player:";
        Predicate<Long> function = (Long i) -> (i > 10_000);
        return PlayerInput.getUserInputs(infoString, function);
    }
}
