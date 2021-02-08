import java.util.ArrayList;

public class BackEndMethods {
    private int totalCapacity = 1700;
    private HashTableMap<String, Player> completeRoster;
    private HashTableMap<String, Player> myNflRoster;
    private String key;
    private String source;
    private ArrayList<Player> playerData;
    private LoadPlayers loader;
    private int size;
    private Player player;
    private String[] teams;
    private ArrayList<String> keys;

    /**
     * Default Constructor
     */
    public BackEndMethods() {
        completeRoster = new HashTableMap(totalCapacity);
        myNflRoster = new HashTableMap();
        keys = new ArrayList<String>();
        teams = new String[]{"Arizona Cardinals", "Atlanta Falcons", "Baltimore Ravens", "Buffalo Bills", "Carolina Panthers", "Chicago Bears", "Cincinnati Bengals", "Cleveland Browns", "Dallas Cowboys", "Denver Broncos", "Detroit Lions", "Green Bay Packers", "Houston Texans", "Indianapolis Colts", "Jacksonville Jaguars", "Kansas City Chiefs", "Las Vegas Raiders", "Los Angeles Chargers", "Los Angeles Rams", "Miami Dolphins", "Minnesota Vikings", "New England Patriots", "New Orleans Saints", "New York Giants", "New York Jets", "Philadelphia Eagles", "Pittsburgh Steelers", "San Francisco 49ers", "Seattle Seahawks", "Tampa Bay Buccaneers", "Tennessee Titans", "Washington"};
        createCompleteRoster();
    }

    /**
     * Loads Master hashtable of all NFL players
     *
     * @return true if successful
     */
    public boolean createCompleteRoster() {
        loader = new LoadPlayers("CompleteRoster_TeamAndJersey.txt");
        playerData = loader.getPlayers();
        source = loader.getSource();

        for (int i = 0; i < playerData.size(); i++) {
            player = playerData.get(i);
            int jerseyNum = playerData.get(i).getJerseyNum();
            key = createKey(player.getTeamName(), Integer.toString(jerseyNum));

            completeRoster.put(key, player);
        }

        return true;
    }

    /**
     * Adds player to personal hashtable
     *
     * @param jNum
     * @param teamName
     * @return true if successful
     */
    public boolean addPlayer(int jNum, String teamName) {
        String jerseyNum = Integer.toString(jNum);
        key = createKey(teamName, jerseyNum);

        if (!myNflRoster.containsKey(key)) {
            myNflRoster.put(key, this.findPlayerInMaster(jNum, teamName));
            keys.add(key);
            this.size++;
            return true;
        }

        return false;
    }

    /**
     * finds player in master hashtable
     *
     * @param jNum
     * @param teamName
     * @return player object
     */
    public Player findPlayerInMaster(int jNum, String teamName) {

        String jerseyNum = Integer.toString(jNum);
        key = createKey(teamName, jerseyNum);
        if (!completeRoster.containsKey(key)) {
            return null;
        }

        player = completeRoster.get(key);

        return player;
    }

    /**
     * finds player in personal hashtable
     *
     * @param jNum
     * @param teamName
     * @return player object
     */
    public Player findPlayerInMyNflRoster(int jNum, String teamName) {
        String jerseyNum = Integer.toString(jNum);
        key = createKey(teamName, jerseyNum);

        if (!completeRoster.containsKey(key)) {
            return null;
        }

        player = completeRoster.get(key);
        return player;
    }

    /**
     * size getter method
     *
     * @return size
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns whether or not a player exists in personal hashtable
     *
     * @param teamName
     * @param jerseyNum
     * @return true if player exists
     */
    public boolean ContainsPlayerInMyNflRoster(String teamName, String jerseyNum) {
        key = createKey(teamName, jerseyNum);
        return this.myNflRoster.containsKey(key);
    }

    /**
     * Removes player from personal hashtable
     *
     * @param jNum
     * @param teamName
     * @return player object that was removed
     */
    public String remove(int jNum, String teamName) {
        String jerseyNum = Integer.toString(jNum);
        key = createKey(teamName, jerseyNum);

        if (!this.ContainsPlayerInMyNflRoster(teamName, jerseyNum)) return null;

        player = this.findPlayerInMyNflRoster(jNum, teamName);

        this.size--;
        this.myNflRoster.remove(key);

        //remove key from keys list
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equals(key)) {
                keys.remove(key);
                return player.toString();
            }
        }

        return player.toString();
    }

    /**
     * @return private HashtableMap<String, Player>
     * this method is only used for testing
     */
    public HashTableMap<String, Player> getCompleteRoster() {
        return this.completeRoster;
    }

    /**
     * @return private HashtableMap<String, Player>
     * this method is only used for testing
     */
    public HashTableMap<String, Player> getMyNflRoster() {
        return this.myNflRoster;
    }

    /**
     * @return private String array teams
     * this method is only used for testing
     */
    public String[] getTeams() {
        return teams;
    }

    /**
     * Lists all players in personal hashtable
     *
     * @return string of all players
     */
    public String listMyPlayers() {
        String allPlayers = "";

        for (int i = 0; i < keys.size(); i++) {
            if (this.myNflRoster != null && keys.get(i) != null && this.myNflRoster.get(keys.get(i)) != null)

                allPlayers += this.myNflRoster.get(keys.get(i)).toString() + "\n" + "\n";
        }

        return allPlayers;
    }

    /**
     * source getter method
     *
     * @return source
     */
    public String getSource() {
        return this.source;
    }

    /**
     * helper method to create key
     *
     * @param teamName
     * @param jerseyNum
     * @return concatenation of teamName and jerseyNum
     */
    public String createKey(String teamName, String jerseyNum) {
        return teamName + jerseyNum;
    }


}
