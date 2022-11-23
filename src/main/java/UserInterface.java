import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    Controller controller = new Controller();
    private Scanner scanner = new Scanner(System.in);

    public void start(){
        controller.registerMember("Anders Teller", 20, true); // TEST
        controller.registerMember("Nicolai Andersson", 21, true); // TEST
        controller.registerMember("Victor Hanert", 22, true); // TEST
        controller.registerMember("Omar Kayed", 22, false); // TEST
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
                    2. Vis liste af oprettede medlemmer
                    3. Søg efter medlem
                    4. Rediger medlem
                    5. Udmeld medlem
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
            case 2 -> printMembers();
        }
    }


    private void registerMember() {

        System.out.println("Registrer et medlem");

        System.out.println("Indtast medlemmets navn: ");
        String name = scanner.nextLine();



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

        boolean isActive = true;
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


        controller.registerMember(name, age, isActive);
    }

    private void printMembers() {
        if (!controller.getMembers().isEmpty()){
            System.out.println("------------------------------------------------------");
            for (Member member : controller.getMembers()){
                System.out.println("Navn: " + member.getName());
                System.out.println("Alder: " + member.getAge() + " år");
                System.out.println("Medlemsskab: " + (member.isActive()?"Aktivt":"Passivt"));
                System.out.println("------------------------------------------------------");
            }

            System.out.println("Der er i øjeblikket registreret " + controller.getMembers().size() + " medlem" + (controller.getMembers().size() == 1 ? "":"mer") + " i systemet");
        }else{
            System.out.println("Der er ingen registreret medlemmer i systemet.");
        }
    }
}
