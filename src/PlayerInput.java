import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

public class PlayerInput{
    static boolean typeError, validInput;
    static Scanner inputReader = new Scanner(System.in);

    PlayerInput(){}

    public Modes getModeSelection() {

        String infoString = "Enter the mode you want to play in" +
                "\nthe available options are;" +
                "\n1: Color based" +
                "\n2: Column based" +
                "\n3: Normal";
        Predicate<Long> function = (i) -> ((i >= 1) && (i <= 3));
        return Modes.values()[(int)getUserInputs(infoString, function) - 1];
    }

    public long getBetAmount(Player player){
        String infoString = "How much do you money do you want to bet?\n" +
                "Your current balance is " + player.getBalance();
        Predicate<Long> function = (Long i) -> ((i >= 0) && (i <= player.getBalance()));
        return getUserInputs(infoString, function);
    }


    public boolean repeatQuestion(){
        String infoString = "Do you want to play again?\n" +
                "1 = yes, 0 = no";
        Predicate<Long> function = (Long i) -> (i == 0 || i == 1);
        return  1 == getUserInputs(infoString, function);

    }

    public static long getUserInputs(String inputInfo, Predicate<Long> function ){
        System.out.println(inputInfo);
        long userInput = 0;
        do{
            typeError = false;
            if(inputReader.hasNext()){
                try{
                    userInput = inputReader.nextLong();
                    if(function.test(userInput)){
                        validInput = true;
                        return userInput;
                    } else {
                        validInput = false;
                        System.out.println("You entered an invalid number");
                    }
                } catch (InputMismatchException e){
                    System.out.println(inputReader.next() + " is not a valid input, TRY AGIAN!!");
                    typeError = true;
                }
            }
        }while(!validInput || typeError);
        return 0;
    }
}
