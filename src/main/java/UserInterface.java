import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    Controller controller = new Controller();
    private Scanner scanner = new Scanner(System.in);

    public void start(){
        printWelcome();
    }

    private void printWelcome() {

        int menuInput = 0;
        boolean inputError;

        while (menuInput != 9){
            // Printing out menu
            System.out.println("""
                    Velkommen til Delfinen Svømmeklub!
                    
                    1. Registrer nyt medlem
                    2. Søg efter medlem
                    3. Rediger medlem
                    4. Udmeld medlem
                    5. Vis liste af oprettede medlemmer
                    6. Vis liste af oprettede hold
                    7. 
                    8.
                    9. Afslut program
                    """);

            do {
                try{
                    menuInput = scanner.nextInt();
                    scanner.nextLine();
                    
                    handleUserInput(menuInput);
                    inputError = false;
                } catch (InputMismatchException e){
                    System.out.println("Input er ugyldigt, prøv venligst igen!");
                    
                    inputError = true;
                    scanner.nextLine();
                }
            }while(inputError);
        }
    }

    private void handleUserInput(int menuInput) {
        switch(menuInput){
            case 1 -> registerMember();
        }
    }

    private void registerMember() {
    }
}
