import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Filehandler {

    public void saveData(ArrayList<Member> members) throws FileNotFoundException {
        PrintStream output = new PrintStream(new File("data/members.csv"));

        for (Member member : members) {
            output.print(member.getName());
            output.print(";");
            output.print(member.getBirthday());
            output.print(";");
            output.print(member.getRegisterDate());
            output.print(";");
            output.print(member.getPhoneNumber());
            output.print(";");
            output.print(member.isActive());
            output.print(";");
            output.println();
        }

        //Slutte med close:
        output.close();
        output.flush();
    }

    public void loadData(ArrayList<Member> members) throws FileNotFoundException {
        // Clear list of names before load:
        members.clear();

        Scanner reader = new Scanner(new File("data/members.csv"));

        while (reader.hasNextLine()) {
            String line = reader.nextLine();

            Member dataObjekt = parseCSVLine(line);
            members.add(dataObjekt);

            System.out.println(line);
        }
    }

    private Member parseCSVLine(String line) {
        try {
            String[] parts = line.split(";");

            Member dataObjekt = new Member();
            dataObjekt.setName(parts[0]);
            dataObjekt.setBirthday(LocalDate.parse(parts[1]));
            dataObjekt.setRegisterDate(LocalDate.parse(parts[2]));
            dataObjekt.setPhoneNumber(Integer.parseInt(parts[3]));
            dataObjekt.setActive(Boolean.parseBoolean(parts[4]));
            return dataObjekt;
        } catch (NumberFormatException e) {
            System.out.println("Kan ikke loade data, fejl i input");
            return null;
        }

    }
}
