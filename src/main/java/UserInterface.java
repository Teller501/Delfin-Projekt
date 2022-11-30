import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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


        // Printing out menu
        System.out.println("""
                Velkommen til Delfinen Svømmeklub!
                
                Vælg venligst hvem du er:
                
                
                1. Formand
                2. Træner
                3. Kasserer                    
                
                """);

        do {
            try {
                Profession profession = null;
                menuInput = scanner.nextInt();
                scanner.nextLine();

                switch (menuInput) {
                    case 1 -> profession = Profession.CHAIRMAN;
                    case 2 -> profession = Profession.TRAINER;
                    case 3 -> profession = Profession.CASHIER;
                }
                inputError = false;
                showMenu(profession);
            } catch (InputMismatchException e) {
                System.out.println("Input er ugyldigt, prøv venligst igen!");

                inputError = true;
                scanner.nextLine();
            }
        } while (inputError);


    }

    private void showMenu(Profession profession){
        int menuInput = 0;
        boolean inputError;

        while (menuInput != 9){
            switch (profession){
                case CHAIRMAN -> {
                    System.out.println("""
                        1. Registrer medlem
                        2. Vis liste af medlemmer
                        3. Søg efter medlem
                        4. Rediger medlem
                        5. Udmeld medlem
                        
                        8. Gem data
                        9. Afslut program
                        """);

                    do {
                        try {
                            menuInput = scanner.nextInt();
                            scanner.nextLine();

                            switch (menuInput) {
                                case 1 -> registerMember();
                                case 2 -> printMembers();
                                case 3 -> searchForMember();
                                case 4 -> editMember();
                                case 5 -> deleteMember();

                                case 8 -> saveData();
                                case 9 -> System.exit(1);
                            }
                            inputError = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Input er ugyldigt, prøv venligst igen!");

                            inputError = true;
                            scanner.nextLine();
                        }
                    } while (inputError);
                }
                case TRAINER -> {
                    System.out.println("""
                            1. Opret hold
                            2. Se liste af oprettede hold
                            3. 
                            """);

                }
                case CASHIER -> {
                    System.out.println("""
                            1. Se medlemmers kontigent
                            2. Se samlet kontigent
                            3. Se medlemmer i restance
                            
                            9. Afslut program
                            """);
                    do {
                        try {
                            menuInput = scanner.nextInt();
                            scanner.nextLine();

                            switch (menuInput) {
                                case 1 -> viewContribution();
                                case 2 -> {
                                    System.out.println("Der er i øjeblikket registreret " + controller.getMembers().size() + " medlem" + (controller.getMembers().size() == 1 ? "" : "mer") + " i systemet");

                                    System.out.println("Den samlede kontigentbetaling er: " + controller.getTotalContribution() + "kr.");
                                    System.out.println();
                                }
                                case 3 -> viewMembersInArrear();

                                case 9 -> System.exit(1);
                            }
                            inputError = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Input er ugyldigt, prøv venligst igen!");

                            inputError = true;
                            scanner.nextLine();
                        }
                    } while (inputError);
                }
            }
        }
    }

    private void registerMember() {

        System.out.println("Registrer et medlem");

        System.out.println("Indtast medlemmets navn: ");
        String name = scanner.nextLine();


        LocalDate birthday = LocalDate.now();
        boolean birthdayInputError;

        do {
            try {
                System.out.println("Indtast medlemmets fødselsdato: (dd-mm-yyyy)");
                birthday = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                birthdayInputError = false;
            } catch (NumberFormatException | DateTimeParseException e) {
                System.out.println("Ugyldig fødselsdato, prøv venligst igen!");
                birthdayInputError = true;
            }
        } while (birthdayInputError);

        LocalDate registerDate = LocalDate.now();
        boolean registerDateInputError;

        do {
            try {
                System.out.println("Indtast medlemmets indmeldingsdato: (dd-mm-yyyy)");
                registerDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                registerDateInputError = false;
            } catch (NumberFormatException | DateTimeParseException e) {
                System.out.println("Ugyldig fødselsdato, prøv venligst igen!");
                registerDateInputError = true;
            }
        } while (registerDateInputError);


        // When typing a phonenumber, it checks if the number is 8 digits (danish number)
        String phoneNumberInput = "";
        int phoneNumber = 0;
        boolean phoneNumberInputError;

        do{
            try{
                System.out.println("Indtast medlemmets telefon-nr: ");
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
            System.out.println("Skal brugeren have et aktivt medlemskab? (j / n) ");
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


        controller.registerMember(name, birthday, registerDate, isActive, phoneNumber);
    }

    private void printMembers() {
        if (!controller.getMembers().isEmpty()) {
            System.out.println("------------------------------------------------------");
            for (Member member : controller.getMembers()) {
                member.calculateMemberType();

                System.out.println("Navn: " + member.getName());
                System.out.println("Fødselsdag: " + member.getBirthday().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                System.out.println("Telefon nr: " + member.getPhoneNumber());
                System.out.println("Medlemstype: " + (member.calculateMemberType() == MemberType.JUNIOR_SWIMMER ? "Juniorsvømmer" : "")
                        + (member.calculateMemberType() == MemberType.SENIOR_SWIMMER ? "Seniorsvømmer" : "")
                        +(member.calculateMemberType() == MemberType.PENSION_SWIMMER ? "Pensionist":""));
                System.out.println("Medlemskab: " + (member.isActive() ? "Aktivt" : "Passivt"));
                System.out.println("Indmeldingsdato: " + member.getRegisterDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
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
                    System.out.println("Fødselsdag: " + searchResult.get(choice - 1).getBirthday() + "år");
                    System.out.println("Telefon nr: " + searchResult.get(choice - 1).getPhoneNumber());
                    System.out.println("Medlemskab: " + (searchResult.get(choice - 1).isActive() ? "Aktivt" : "Passivt"));
                    System.out.println("------------------------------------------------------");
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Input ikke gyldigt, prøv venligst igen!");
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

            System.out.println("Fødselsdato: " + editMember.getBirthday().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            do {
                try {
                    String newBirthDay = scanner.nextLine();
                    if (!newBirthDay.isEmpty()) {
                        editMember.setBirthday(LocalDate.parse(newBirthDay,DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    }
                    inputError = false;
                } catch (NumberFormatException | DateTimeParseException e) {
                    System.out.println("Indtast venligst en gyldig dato! - Benyt denne format: dd-MM-yyyy");
                    inputError = true;
                }
            } while (inputError);


            System.out.println("Indmeldelsesdato: " + editMember.getRegisterDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            do {
                try{
                    String newRegisterDate = scanner.nextLine();
                    if (!newRegisterDate.isEmpty()) {
                        editMember.setBirthday(LocalDate.parse(newRegisterDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    }
                    inputError = false;
                }catch (NumberFormatException | DateTimeParseException e){
                    System.out.println("Indtast venligst en gyldig dato! - Benyt denne format: dd-MM-yyyy");
                    inputError = true;
                }
            }while(inputError);

            System.out.println("Telefon-nr: " + (editMember.getPhoneNumber()));
            do {
                try {
                    String newPhoneNumber = scanner.nextLine().trim();
                    if (!newPhoneNumber.isEmpty()) {
                        if (newPhoneNumber.length() == 8) {
                            editMember.setPhoneNumber(Integer.parseInt(newPhoneNumber));
                            inputError = false;
                        } else {
                            System.out.println("Du skal indtaste 8 cifre! ");
                            inputError = true;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Indtast venligst et gyldigt telefon-nr! - Skal være 8 tal.");
                    inputError = true;
                }
            } while (inputError);


            System.out.println("Medlemskabsstatus: " + (editMember.isActive() ? "Aktivt" : "Passivt"));
            char activeStatus;
            do {
                System.out.println("Skal brugeren have et aktivt medlemskab? (j / n) ");
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

    private void viewContribution(){
        // For loop that gets contribution for each member and printing out:
        System.out.println("------------------------------------");
        for (Member member : controller.getMembers()){
            System.out.println("Medlem: " + member.getName());
            System.out.println("Kontingentbetaling: " + member.getContributionPrice() +"kr.");
            System.out.println("------------------------------------");
        }
    }

    private void viewMembersInArrear(){
        ArrayList<Member> membersInArrear = controller.getMembersInArrear();

        if (membersInArrear.isEmpty()){
            System.out.println("Der er i øjeblikket ingen medlemmer i restance");
        }else{
            // For loop that loops through every member in arrear and printing out how much they owe
            System.out.println("Der er i øjeblikket registreret " + membersInArrear.size() + " medlem" + (membersInArrear.size() == 1 ? "" : "mer") + " i restance");

            System.out.println("------------------------------------");
            for (Member member : controller.getMembersInArrear()){
                System.out.println("Medlem: " + member.getName());
                System.out.println("Kontingentbetaling: " + member.getContributionPrice() +"kr.");
                System.out.println("Skylder: "); // TODO udregn med hvor meget medlemmet skylder
                System.out.println("------------------------------------");
            }
        }
    }


    //Kan laves som extra detalje hvis vi har tid til over:
    private int getValidNumber(int min, int max) {
        // har en try catch og while, indtil der er indtastet noget gyldigt mellem min og max
        try {
        }
        catch (IndexOutOfBoundsException | NumberFormatException e) {
        }
        return 0; // returnerer det gyldige indtastede
    }
}
