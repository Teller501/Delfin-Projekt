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

        System.out.println("Registrer et medlem");

        System.out.println("Indtast medlemmets fornavn: ");
        String firstName = scanner.nextLine();
        firstName = firstName.contains(" ") ? firstName.split(" ")[0] : firstName; // Checking if firstName contains more than one word

        System.out.println("Indtast medlemmets efternavn: ");
        String lastName = scanner.nextLine();


        int age = 0;
        boolean ageInputError;

        do{
            try{
                System.out.println("Indtast medlemmets alder: ");
                age = Integer.parseInt(scanner.nextLine());
                ageInputError = false;
            } catch (NumberFormatException e){
                System.out.println("Input er ugyldigt, prøv venligst igen!");
                ageInputError = true;
            }
        }while(ageInputError);

        boolean isActive;
        char activeStatus;

        do {
            System.out.println("Skal brugeren have et aktivt medlemsskab? (j / n) ");
            activeStatus = scanner.next().charAt(0);
            System.out.println();

            if (activeStatus == 'j'){
                isActive = true;
            } else if (activeStatus == 'n'){
                isActive = false;
            } else {
                System.out.println("Input er ugyldigt, prøv venligst igen!");
            }
        } while(activeStatus != 'j' && activeStatus != 'n');

    }
}
