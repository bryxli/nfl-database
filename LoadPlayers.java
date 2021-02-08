import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Converts data from a text file into an ArrayList of Player objects and stores the source of information.
 */
public class LoadPlayers {
    //Data fields
    private ArrayList<Player> players;
    private String source;

    /**
     * Constructor that takes in the text file and creates the ArrayList.
     * See example text file (CompleteRoster_TeamAndJersey.txt) for format.
     *
     * @param location the location of the text file
     */
    public LoadPlayers(String location) {
        try {
            players = new ArrayList<>();
            File file = new File(location);
            Scanner scanner = new Scanner(file);
            source = scanner.nextLine().trim();
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] information = data.split("\t");
                information[6] = information[6].split(" ")[0];
                if (information[3].equals(""))
                    information[3] = "0";
                if (information.length == 9)
                    players.add(new Player(information[0], information[1], information[2],
                            Integer.parseInt(information[3]), Integer.parseInt(information[4]),
                            information[5], Integer.parseInt(information[6]), information[7], information[8]));
            }
            scanner.close();
        } catch (FileNotFoundException | NoSuchElementException e) {
            players = null;
            System.out.println("File not found or not following format.");
        }
    }

    /**
     * Obtains the ArrayList of Player objects.
     *
     * @return the players field.
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Obtains the source that the data was retrieved from.
     *
     * @return the source field.
     */
    public String getSource() {
        return this.source;
    }
}
