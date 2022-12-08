package ui;

import member.Member;
import application.*;
import team.*;
import member.Result;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    private Controller controller = new Controller();
    private Scanner scanner = new Scanner(System.in);
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\033[0;31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public enum Profession {
        CHAIRMAN,
        TRAINER,
        CASHIER
    }

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
                
                9. Afslut program                
                
                """);

        do {
            try {
                Profession profession = null;
                menuInput = scanner.nextInt();
                scanner.nextLine();

                switch (menuInput) {
                    case 1 -> profession = UserInterface.Profession.CHAIRMAN;
                    case 2 -> profession = UserInterface.Profession.TRAINER;
                    case 3 -> profession = UserInterface.Profession.CASHIER;

                    case 9 -> System.exit(1);
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
                        
                        7. Gem data
                        8. Gå tilbage til start
                        9. Afslut program
                        """);

                    do {
                        try {
                            menuInput = scanner.nextInt();
                            scanner.nextLine();

                            switch (menuInput) {
                                case 1 -> registerMember();
                                case 2 -> viewMembers();
                                case 3 -> searchForMember();
                                case 4 -> editMember();
                                case 5 -> deleteMember();

                                case 7 -> saveData();
                                case 8 -> printWelcome();
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
                            1. Se liste af oprettede hold
                            2. Tilføj medlem til hold
                            3. Tilføj resultat til medlem
                            4. Se top 5 resultater
                            
                            7. Gem data
                            8. Gå tilbage til start
                            9. Afslut program
                            """);

                    do {
                        try {
                            menuInput = scanner.nextInt();
                            scanner.nextLine();

                            switch (menuInput) {
                                case 1 -> viewTeams();
                                case 2 -> addMemberToTeam();
                                case 3 -> addResult();
                                case 4 -> viewTop5Results();

                                case 7 -> saveData();
                                case 8 -> printWelcome();
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
                case CASHIER -> {
                    System.out.println("""
                            1. Se medlemmers kontigent
                            2. Se samlet kontigent
                            3. Se medlemmer i restance
                            
                            8. Gå tilbage til start
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
                                case 8 -> printWelcome();
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


        LocalDate birthday = getDate("Indtast medlemmets fødselsdato: (dd-mm-yyyy) ");

        LocalDate registerDate = getDate("Indtast medlemmets indmeldingsdato: (dd-mm-yyyy) ");


        int phoneNumber = getPhoneNumber();

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

    // Printing out all members in the system
    private void viewMembers() {
        if (!controller.getMembers().isEmpty()) {
            System.out.printf("-----------------------------------------------------------------------------------------------%n");
            System.out.printf(ANSI_BLUE + "| %-18s | %-10s | %8s | %13s | %12s | %15s | %n", "NAVN", "FØDSELSDAG", "TLF","MEDLEMSTYPE", "MEDLEMSSKAB", "INDMELDINGSDATO" + ANSI_RESET);
            System.out.printf("-----------------------------------------------------------------------------------------------%n");
            for (Member member : controller.getMembers()) {
                member.calculateMemberType();
                System.out.printf("| %-18s | %-10s | %8s | %13s | %12s | %15s | %n", member.getName(),member.getBirthday().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                        member.getPhoneNumber(),(member.calculateMemberType() == Member.MemberType.JUNIOR_SWIMMER ? "Juniorsvømmer" : "")
                                + (member.calculateMemberType() == Member.MemberType.SENIOR_SWIMMER ? "Seniorsvømmer" : "")
                                +(member.calculateMemberType() == Member.MemberType.PENSION_SWIMMER ? "Pensionist":""),(member.isActive() ? "Aktivt" : "Passivt"),
                        member.getRegisterDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                System.out.printf("-----------------------------------------------------------------------------------------------%n");

            }

            System.out.println("Der er i øjeblikket registreret " + controller.getMembers().size() + " medlem" + (controller.getMembers().size() == 1 ? "" : "mer") + " i systemet");
        } else {
            System.out.println("Der er ingen registreret medlemmer i systemet.");
        }
    }

    // Method for searching and printing a specific member
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
                    searchResult.get(choice -1).calculateMemberType();
                    System.out.printf("-----------------------------------------------------------------------------------------------%n");
                    System.out.printf(ANSI_BLUE + "| %-18s | %-10s | %8s | %13s | %12s | %15s | %n", "NAVN", "FØDSELSDAG", "TLF","MEDLEMSTYPE", "MEDLEMSSKAB", "INDMELDINGSDATO" + ANSI_RESET);
                    System.out.printf("-----------------------------------------------------------------------------------------------%n");
                    System.out.printf("| %-18s | %-10s | %8s | %13s | %12s | %15s | %n", searchResult.get(choice - 1).getName(),searchResult.get(choice - 1).getBirthday().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                            searchResult.get(choice - 1).getPhoneNumber(),(searchResult.get(choice -1).calculateMemberType() ==Member.MemberType.JUNIOR_SWIMMER ? "Juniorsvømmer" : "")
                                    + (searchResult.get(choice -1).calculateMemberType() ==Member.MemberType.SENIOR_SWIMMER ? "Seniorsvømmer" : "")
                                    +(searchResult.get(choice -1).calculateMemberType() ==Member.MemberType.PENSION_SWIMMER ? "Pensionist" : ""),(searchResult.get(choice -1).isActive() ? "Aktivt" : "Passivt"),
                                    searchResult.get(choice -1).getRegisterDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    System.out.printf("-----------------------------------------------------------------------------------------------%n");
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("Input ikke gyldigt, prøv venligst igen!");
                    inputError = true;
                }
            } while (inputError);
        } else {
            System.out.println("Ingen relevante medlemmer med følgende søgeord!");
        }
    }

    // Loading saved data from csv files
    private void loadData() {
        controller.loadData();
        System.out.println("Data er nu opdateret...");
    }

    // Saving changed data to csv files
    private void saveData() {
        if (controller.isChanges()) {
            controller.saveData();
            System.out.println("Data er gemt");
        } else {
            System.out.println("Data er ikke gemt, da ingen ændringer er lavet.");
        }
    }

    // Searching and editing a specific member
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

            System.out.println("Navn: " + editMember.getName());
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                editMember.setName(newName);
            }

            System.out.println("Fødselsdato: " + editMember.getBirthday().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            LocalDate newBirthDay = getDate("Ny fødselsdato: (dd-MM-yyy");
            editMember.setBirthday(newBirthDay);


            System.out.println("Indmeldelsesdato: " + editMember.getRegisterDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            LocalDate newRegisterDate = getDate("Ny indmeldingsdato: (dd-MM-yyyy");
            editMember.setRegisterDate(newRegisterDate);

            System.out.println("Telefon-nr: " + (editMember.getPhoneNumber()));
            int phoneNumber = getPhoneNumber();
            editMember.setPhoneNumber(phoneNumber);


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

    // Searching and deleting specific member
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

    // Printing out all members who owes the swimclub
    private void viewMembersInArrear(){
        ArrayList<Member> membersInArrear = controller.getMembersInArrear();

        if (membersInArrear.isEmpty()){
            System.out.println("Der er i øjeblikket ingen medlemmer i restance");
        }else{
            // For loop that loops through every member in arrear and printing out how much they owe
            System.out.println("Der er i øjeblikket registreret " + membersInArrear.size() + " medlem" + (membersInArrear.size() == 1 ? "" : "mer") + " i restance");
            System.out.printf("--------------------------------------------------------%n");
            System.out.printf(ANSI_BLUE + "| %-18s | %-18s | %14s | %n", "NAVN", "KONTINGENTBETALING", "SKYLDER" + ANSI_RESET);
            System.out.printf("--------------------------------------------------------%n");

            for (Member member : controller.getMembersInArrear()){
                System.out.printf("| %-18s | %-18s | %14s | %n",member.getName() ,member.getContributionPrice() + " kr.", ANSI_RED + member.getOwes() + " kr." + ANSI_RESET);
                System.out.printf("--------------------------------------------------------%n");
            }
        }
    }

    // Printing out all the teams in the system
    private void viewTeams(){
        ArrayList<Team> teams = controller.getTeams();

        if (teams.isEmpty()){
            System.out.println("Der er ingen hold registreret i systemet.");
        }else{
            System.out.println("Der er i øjeblikket registreret " + teams.size() + " hold i systemet.");


            System.out.println("------------------------------------");
            for (Team team : teams){
                System.out.println("Hold: " + team.getName());
                System.out.println("Disciplin: " + team.getDisciplin());
                System.out.print("Medlemmer: ");
                if (team.getTeamMembers().isEmpty()){
                    System.out.println("Ingen medlemmer tilføjet til " + team.getName());
                }else{
                    for (Member member : team.getTeamMembers()){
                        System.out.print(member.getName() + (team.getTeamMembers().size()<=1?"":", "));
                    }
                    System.out.println();
                }
                System.out.println("------------------------------------");
            }
        }
    }

    // Searching and adding member to a team
    private void addMemberToTeam(){
        System.out.println("Indtast søgeord for medlemmet du vil tilføje til et hold: ");
        String searchTerm = scanner.nextLine();

        ArrayList<Member> searchResults = controller.searchForMember(searchTerm);

        int index = 1;
        if (searchResults.isEmpty()) {
            System.out.println("Ingen fundet");
        }else {
            for (Member searchResult : searchResults) {
                System.out.println(index++ + ": " + searchResult.getName());
            }

            System.out.println("Vælg den svømmer du vil tilføje: ");

            int memberChoice = 1;
            memberChoice = Integer.parseInt(scanner.nextLine());
            Member memberChosen = searchResults.get(memberChoice - 1);

            if (memberChosen.calculateAge() < 18) {
                Team teamChosen = getJuniorTeam();
                System.out.println("Tilføjer til: " + teamChosen.getName());
                controller.addMemberToTeam(teamChosen, memberChosen);
                System.out.println(memberChosen.getName() + " er tilføjet til " + teamChosen.getName());
            }

            else {
                Team teamChosen = getSeniorTeam();
                System.out.println("Tilføjer til: " + teamChosen.getName());
                controller.addMemberToTeam(teamChosen, memberChosen);
                System.out.println(memberChosen.getName() + " er tilføjet til " + teamChosen.getName());
            }


        }
    }

    private Team getJuniorTeam() {
        int teamIndex = 1;
        for (Team team : controller.getTeams()){
            if (team.getName().contains("u-18")) {
                System.out.println(teamIndex++ + ": " + team.getName());
            }
        }
        System.out.println("Vælg det juniorhold du vil tilføje medlemmet til: ");

        int teamChoice = 1;
        teamChoice = Integer.parseInt(scanner.nextLine());
        return controller.getTeams().get(teamChoice - 1);
    }

    private Team getSeniorTeam() {
        int teamIndex = 5;
        for (Team team : controller.getTeams()){
            if (team.getName().contains("Senior")) {
                System.out.println(teamIndex++ + ": " + team.getName());
            }
        }
        System.out.println("Vælg det seniorhold du vil tilføje medlemmet til: ");

        int teamChoice = 1;
        teamChoice = Integer.parseInt(scanner.nextLine());
        return controller.getTeams().get(teamChoice - 1);
    }

    // Adds results of member to the specific team
    private void addResult(){
        Team teamChosen = getTeam("Vælg det hold du vil tilføje resultater til: ");


        Member memberChosen = getMember(teamChosen);


        System.out.println("""
                1. Træningsresultat
                2. Konkurrenceresultat""");

        int resultType = Integer.parseInt(scanner.nextLine());

        switch(resultType){
            case 1-> {
                LocalDate resultDate = getDate("Vælg dato for resultatet (dd-MM-yyyy): ");

                double time = getTime();

                controller.addTrainingResult(memberChosen, resultDate, time, teamChosen);
                System.out.println("Tid er tilføjet til " + memberChosen.getName());
            }
            case 2->{
                LocalDate resultDate = getDate("Vælg dato for resultatet (dd-MM-yyyy): ");

                double time = getTime();

                System.out.println("Angiv hvilket stævne " + memberChosen.getName() + " deltog til");
                String convention = scanner.nextLine();

                int placement = 0;
                boolean placementError;

                do {
                    try{
                        System.out.println("Angiv " + memberChosen.getName() + "s placering til stævnet");
                        placement = Integer.parseInt(scanner.nextLine());
                        placementError = false;
                    }catch(NumberFormatException e){
                        System.out.println("Ugyldigt input, prøv venligst igen!");
                        placementError = true;
                    }
                }while(placementError);

                controller.addCompetitionResult(memberChosen, resultDate, time, convention, placement, teamChosen);

                System.out.println("Tid er registreret for " + memberChosen.getName());

            }
            default -> System.out.println("Ugyldigt input, prøv igen!");
        }
    }

    // Getting the top 5 times from members at a specific team
    private void viewTop5Results(){
        Team team = getTeam("Vælg det hold du vil se top 5 resultater for: ");
        ArrayList<Result> sortedTrainingResults = controller.sortTrainingResults(team);
        ArrayList<Result> sortedCompetitionResults = controller.sortCompetitionResults(team);

        if (!sortedTrainingResults.isEmpty()){
            System.out.println("Bedste 5 træningsresultater for " + team.getName());
            for(int i = 0; i< 5; i++){
                Result result = sortedTrainingResults.get(i);
                System.out.println(result.getMember().getName() + " " + result.getTime() + " " + result.getDate());
            }
        }else{
            System.out.println("Der er ingen træningsresultater på " + team.getName());
        }

        System.out.println();

        if (!sortedCompetitionResults.isEmpty()){
            System.out.println("Bedste 5 konkurrenceresultater for " + team.getName());
            for(int i = 0; i< 5; i++){
                Result result = sortedCompetitionResults.get(i);
                System.out.println(result.getMember().getName() + " " + result.getTime() + " " + result.getConvention() + " " + result.getPlacement() + " " + result.getDate());
            }
        }else{
            System.out.println("Der er ingen konkurrenceresultater på " + team.getName());
        }
    }

    //Extracted method for getting members
    private Member getMember(Team teamChosen) {
        int memberIndex = 1;
        if (!teamChosen.getTeamMembers().isEmpty()){
            for (Member member : teamChosen.getTeamMembers()){
                System.out.println(memberIndex++ + ": " + member.getName());
            }
        }else{
            System.out.println("Ingen medlemmer på holdet");
        }

        System.out.println("Vælg medlemmet du vil tilføje et resultat til: ");
        int memberChoice = Integer.parseInt(scanner.nextLine());
        Member memberChosen = teamChosen.getTeamMembers().get(memberChoice-1);
        return memberChosen;
    }

    // Extracted method for getting team
    private Team getTeam(String message) {
        int teamIndex = 1;
        for (Team team : controller.getTeams()){
            System.out.println(teamIndex++ + ": " + team.getName());
        }

        System.out.println(message);

        int teamChoice = 1;
        teamChoice = Integer.parseInt(scanner.nextLine());
        Team teamChosen = controller.getTeams().get(teamChoice - 1);
        return teamChosen;
    }

    // Extracted method for getting time for results
    private double getTime() {
        double time = 0;
        boolean timeError;

        do {
            try{
                System.out.println("Skriv medlemmets målte tid (mm.ss): ");
                time = Double.parseDouble(scanner.nextLine());
                timeError = false;
            }catch (NumberFormatException e){
                System.out.println("Ugyldigt tidsinput, prøv venligst igen!");
                timeError = true;
            }
        }while(timeError);
        return time;
    }

    // Extracted method for getting date for results
    private LocalDate getDate(String message) {
        LocalDate date = LocalDate.now();
        boolean dateError;

        do{
            try {
                System.out.println(message);
                date = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                dateError = false;
            }catch (NumberFormatException | DateTimeParseException e){
                System.out.println("Ugyldig dato, prøv venligst igen!");
                dateError = true;
            }

        }while(dateError);
        return date;
    }

    private int getPhoneNumber() {
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
        return phoneNumber;
    }
}
