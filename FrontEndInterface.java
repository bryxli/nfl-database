import java.util.NoSuchElementException;
import java.util.Scanner;

public class FrontEndInterface {

    /**
     * Scanner asks for string from the user until a valid team name is entered.
     *
     * @param input
     * @return String team which contains a valid team name.
     */
    public static String teamInput(Scanner input) {
        String team = "";
        boolean validTeam = false;
        String[] teamList = new String[]{"Arizona Cardinals", "Atlanta Falcons", "Baltimore Ravens",
                "Buffalo Bills", "Carolina Panthers", "Chicago Bears", "Cinncinati Bengals",
                "Clevland Browns", "Dallas Cowboys", "Denver Broncos", "Detroit Lions", "Green Bay Packers",
                "Houston Texans", "Indianapolis Colts", "Jacksonville Jaguars", "Kansas City Chiefs",
                "Las Vegas Raiders", "Los Angeles Chargers", "Los Angeles Rams", "Miami Dolphins",
                "Minnesota Vikings", "New England Patriots", "New Orleans Saints", "New York Giants",
                "New York Jets", "Philadelphia Eagles", "Pittsburgh Steelers", "San Francisco 49ers",
                "Seattle Seahawks", "Tampa Bay Buccaneers", "Tennessee Titans", "Washington"};
        do {
            System.out.println("Please enter a team (case sensitive): ");
            team = input.nextLine();
            for (int i = 0; i < 32; i++) {
                if (team.equals(teamList[i])) {
                    validTeam = true;
                    i = 32;
                }
            }
            if (validTeam == false) {
                System.out.print("Invalid input. ");
            }
        } while (validTeam == false);
        return team;
    }

    /**
     * Scanner asks for a number until a valid jersey number between 1-99 inclusive.
     *
     * @param input
     * @return int num which contains a valid jersey number.
     */
    public static int numInput(Scanner input) {
        boolean validNum = false;
        String numInput = "";
        int num = -1;

        System.out.println("Please enter a positive number less than 100: ");
        do {
            try {
                numInput = input.nextLine();
                num = Integer.parseInt(numInput);
                if (num < 0 || num > 99) {
                    System.out.println("Please enter a positive number less than 100: ");
                } else {
                    validNum = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please try again and enter a positive number less than 100: ");
            }
        } while (validNum == false);
        return num;
    }


    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        String team = "";
        Player foundPlayer = null;
        String playerProfile;
        int num = -1;

        String[] teamList = new String[]{"Arizona Cardinals", "Atlanta Falcons", "Baltimore Ravens",
                "Buffalo Bills", "Carolina Panthers", "Chicago Bears", "Cinncinati Bengals",
                "Clevland Browns", "Dallas Cowboys", "Denver Broncos", "Detroit Lions", "Green Bay Packers",
                "Houston Texans", "Indianapolis Colts", "Jacksonville Jaguars", "Kansas City Chiefs",
                "Las Vegas Raiders", "Los Angeles Chargers", "Los Angeles Rams", "Miami Dolphins",
                "Minnesota Vikings", "New England Patriots", "New Orleans Saints", "New York Giants",
                "New York Jets", "Philadelphia Eagles", "Pittsburgh Steelers", "San Francisco 49ers",
                "Seattle Seahawks", "Tampa Bay Buccaneers", "Tennessee Titans", "Washington"};

        String helpMenu =
                "Here is a list of available commands! \n" + "Type \"A\" to add a player to your list! \n"
                        + "Type \"G\" to get information of a player from our database! \n"
                        + "Type \"R\" to remove a player from your list! \n"
                        + "Type \"L\" to list all teams in the NFL! \n"
                        + "Type \"D\" to display the players on your list! \n"
                        + "Type \"Q\" to quit!";
        String command;


        BackEndMethods database = new BackEndMethods();

        System.out.print("Welcome to the MyNFL app! ");
        //infinite loop until user enters the character 'Q'. Typing in the different
        //characters will go into the different if statements and run the processes with in
        do {
            System.out.println("Please enter a command (type \"h\" for a list of commands): ");
            command = input.nextLine().toUpperCase();
            if (command.equals("H")) {
                System.out.println(helpMenu);
            } else if (command.equals("A")) {
                team = teamInput(input);
                num = numInput(input);
                database.createCompleteRoster();
                boolean success = database.addPlayer(num, team);
                if (success) {
                    System.out.println("Player was added to your list successfully.");
                } else {
                    System.out.println("Player does not exist.");
                }
            } else if (command.equals("G")) {
                try {
                    team = teamInput(input);
                    num = numInput(input);
                    foundPlayer = database.findPlayerInMaster(num, team);
                    if (foundPlayer == null) {
                        System.out.println("No such player exists");
                    } else {
                        System.out.println(foundPlayer);
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("No such player exists.");
                }
            } else if (command.equals("R")) {
                team = teamInput(input);
                num = numInput(input);
                playerProfile = database.remove(num, team);
                if (playerProfile == null) {
                    System.out.println("The player already does not exist on " +
                            "your personal list.");
                } else {
                    System.out.println("The player has been removed from your " +
                            "personal list.");
                }
            } else if (command.equals("D")) {
                System.out.println(database.listMyPlayers());
            } else if (command.equals("L")) {
                for (int i = 0; i < 32; i++) {
                    System.out.println(teamList[i]);
                }
            }
        } while (!command.equals("Q"));
        System.out.println("Thank you for using MyNFL.");
    }
}
