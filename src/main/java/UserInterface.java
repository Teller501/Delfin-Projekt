import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    Controller controller = new Controller();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        loadData();
        printWelcome();
    }

    private void printWelcome() {

        int menuInput = 0;
        boolean inputError;

        while (menuInput != 9) {
            // Printing out menu
            System.out.println("""
                    Velkommen til Delfinen Svømmeklub!
                                        
                    1. Registrer nyt medlem
                    2. Vis liste af oprettede medlemmer
                    3. Søg efter medlem
                    4. Rediger medlem
                    5. Udmeld medlem
                    6. Hold menu (opret hold, se liste af hold)
                    8. Gem data
                    9. Afslut program
                    """);

            do {
                try {
                    menuInput = scanner.nextInt();
                    scanner.nextLine();

                    handleUserInput(menuInput);
                    inputError = false;
                } catch (InputMismatchException e) {
                    System.out.println("Input er ugyldigt, prøv venligst igen!");

                    inputError = true;
                    scanner.nextLine();
                }
            } while (inputError);
        }
    }

    private void handleUserInput(int menuInput) {
        switch (menuInput) {
            case 1 -> registerMember();
            case 2 -> printMembers();
            case 3 -> searchForMember();
            case 4 -> editMember();
            case 5 -> deleteMember();
            case 8 -> saveData();
        }
    }

    private void registerMember() {

        System.out.println("Registrer et medlem");

        System.out.println("Indtast medlemmets navn: ");
        String name = scanner.nextLine();


        int age = 0;
        boolean ageInputError;

        do {
            try {
                System.out.println("Indtast medlemmets alder: ");
                age = Integer.parseInt(scanner.nextLine());
                ageInputError = false;
            } catch (NumberFormatException e) {
                System.out.println("Input er ugyldigt, prøv venligst igen!");
                ageInputError = true;
            }
        } while (ageInputError);


        // When typing a phonenumber, it checks if the number is 8 digits (danish number)
        String phoneNumberInput = "";
        int phoneNumber = 0;
        boolean phoneNumberInputError;

        do{
            try{
                System.out.println("Indtast medlemmets telefonnr: ");
                phoneNumberInput = scanner.nextLine();

                if (phoneNumberInput.length() == 8){
                    phoneNumber = Integer.parseInt(phoneNumberInput);
                    phoneNumberInputError = false;
                }else {
                    System.out.println("Du skal indtaste 8 cifre! ");
                    phoneNumberInputError = true;
                }

            } catch (NumberFormatException e){
                System.out.println("Input er ugyldigt, prøv venligst igen!");
                phoneNumberInputError = true;
            }
        }while(phoneNumberInputError);

        // Checking if the right word is typed when asked about membership status
        boolean isActive = true;
        char activeStatus;

        do {
            System.out.println("Skal brugeren have et aktivt medlemsskab? (j / n) ");
            activeStatus = scanner.next().charAt(0);
            System.out.println();

            if (activeStatus == 'j') {
                isActive = true;
            } else if (activeStatus == 'n') {
                isActive = false;
            } else {
                System.out.println("Input er ugyldigt, prøv venligst igen!");
            }
        } while (activeStatus != 'j' && activeStatus != 'n');


        controller.registerMember(name, age, isActive, phoneNumber);
    }

    private void printMembers() {
        if (!controller.getMembers().isEmpty()) {
            System.out.println("------------------------------------------------------");
            for (Member member : controller.getMembers()) {
                System.out.println("Navn: " + member.getName());
                System.out.println("Alder: " + member.getAge() + " år");
                System.out.println("Telefon nr: " + member.getPhoneNumber());
                System.out.println("Medlemsskab: " + (member.isActive() ? "Aktivt" : "Passivt"));
                System.out.println("------------------------------------------------------");
            }

            System.out.println("Der er i øjeblikket registreret " + controller.getMembers().size() + " medlem" + (controller.getMembers().size() == 1 ? "" : "mer") + " i systemet");
        } else {
            System.out.println("Der er ingen registreret medlemmer i systemet.");
        }
    }

    private void searchForMember() {
        System.out.println("Indtast søgeord: ");
        String searchTerm = scanner.nextLine();

        ArrayList<Member> searchResult = controller.searchForMember(searchTerm);

        int index = 1;
        if (!searchResult.isEmpty()) {
            for (Member member : searchResult) {
                System.out.println(index++ + ": " + member.getName());
            }

            System.out.println("Vælg venligst medlemmet du vil se nærmere på: ");
            int choice = 1;
            boolean inputError = false;

            do {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    inputError = false;

                    System.out.println("------------------------------------------------------");
                    System.out.println("Navn: " + searchResult.get(choice - 1).getName());
                    System.out.println("Alder: " + searchResult.get(choice - 1).getAge() + "år");
                    System.out.println("Telefon nr: " + searchResult.get(choice - 1).getPhoneNumber());
                    System.out.println("Medlemsskab: " + (searchResult.get(choice - 1).isActive() ? "Aktivt" : "Passivt"));
                    System.out.println("------------------------------------------------------");
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Input ikkt gyldigt, prøv venligst igen!");
                    inputError = true;
                }
            } while (inputError);
        } else {
            System.out.println("Ingen relevante medlemmer med følgende søgeord!");
        }
    }

    private void loadData() {
        controller.loadData();
        System.out.println("Data er nu opdateret...");
    }

    private void saveData() {
        if (controller.isChanges()) {
            controller.saveData();
            System.out.println("Data er gemt");
        } else {
            System.out.println("Data er ikke gemt, da ingen ændringer er lavet.");
        }
    }

    private void editMember() {

        System.out.println("Indtast søgeord: ");
        String searchTerm = scanner.nextLine();

        ArrayList<Member> searchResults = controller.searchForMember(searchTerm);

        if (searchResults.isEmpty()) {
            System.out.println("Ingen fundet");
        } else {
            int index = 1;
            for (Member searchResult : searchResults) {
                System.out.println(index++ + ": " + searchResult.getName());
            }

            System.out.println("Vælg den svømmer som du gerne vil redigere: ");

            int memberChoice = 1;
            boolean inputError = false;
            memberChoice = Integer.parseInt(scanner.nextLine());
            Member editMember = searchResults.get(memberChoice - 1);
            System.out.println("Redigere: " + editMember.getName());

            System.out.println("------------------------------------");

            System.out.println("Indtast data der skal ændres og klik ENTER. Skal data ikke ændres, klik blot ENTER.");

            System.out.println("Fornavn: " + editMember.getName());
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                editMember.setName(newName);
            }

            System.out.println("Alder: " + editMember.getAge());
            do {
                try{
                    String newAge = scanner.nextLine();
                    if (!newAge.isEmpty()) {
                        editMember.setAge(Integer.parseInt(newAge));
                    }
                    inputError = false;
                }catch (NumberFormatException e){
                    System.out.println("Indtast venligst et tal!");
                    inputError = true;
                }
            }while(inputError);

            System.out.println("Medlemsskabsstatus: " + (editMember.isActive() ? "Aktivt" : "Passivt"));
            char activeStatus;

            do {
                System.out.println("Skal brugeren have et aktivt medlemsskab? (j / n) ");
                activeStatus = scanner.next().charAt(0);
                System.out.println();

                if (activeStatus == 'j') {
                    editMember.setActive(true);
                } else if (activeStatus == 'n') {
                    editMember.setActive(false);
                } else {
                    System.out.println("Input er ugyldigt, prøv venligst igen!");
                }
            } while (activeStatus != 'j' && activeStatus != 'n');


        }
    }

    public void deleteMember() {
        System.out.println("------------------------------------");
        System.out.println("Indtast søgeord: ");
        String searchTerm = scanner.nextLine();
        ArrayList<Member> searchResults = controller.searchForMember(searchTerm);

        if (searchResults.isEmpty()) {
            System.out.println("Ingen fundet");
        } else {
            int index = 1;
            for (Member searchResult : searchResults) {
                System.out.println(index++ + ": " + searchResult.getName());
            }

            System.out.println("Vælg medlemmet du vil slette: ");
            int memberChoice = 1;
            boolean inputError = false;
            do {
                try {
                    memberChoice = Integer.parseInt(scanner.nextLine());
                    Member deleteMember = searchResults.get(memberChoice - 1);

                    controller.deleteMember(deleteMember);
                    System.out.println(deleteMember.getName() + " er slettet fra databasen");
                    inputError = false;
                } catch (NumberFormatException e) {
                    System.out.println("Ugyldigt input, indtast venligst tallet på medlemmet du ønsker slettet.");
                    inputError = true;
                }
            } while (inputError);

        }
    }
}
