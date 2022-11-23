import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Filehandler {

    public void saveData(ArrayList<Member> members) throws FileNotFoundException {
        PrintStream output = new PrintStream(new File("data/members.csv"));

        for (Member member : members) {
            output.print(member.getName());
            output.print(";");
            output.print(member.getAge());
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

        while(reader.hasNextLine()) {
            String line = reader.nextLine();

            Member dataObjekt = parseCSVLine(line);
            members.add(dataObjekt);

            System.out.println(line);
        }
    }

    private Member parseCSVLine(String line) {
        try{
            String[] parts = line.split(";");

            Member dataObjekt = new Member();
            dataObjekt.setName(parts[0]);
            dataObjekt.setAge(Integer.parseInt(parts[1]));
            dataObjekt.setActive(Boolean.parseBoolean(parts[2]));
            return dataObjekt;
        }
        catch(NumberFormatException e){
            System.out.println("Kan ikke loade data, fejl i input");
            return null;
        }

    }
}
