/**
 * Represents an individual player in the NFL.
 */
public class Player {
    //Data fields
    private final String name;
    private final String teamName;
    private final String pos;
    private final int jerseyNum;
    private final int age;
    private final String height;
    private final int weight;
    private final String yearsOfExperience;
    private final String college;

    /**
     * Constructor for Player object, all fields are required
     *
     * @param name              the name of the Player
     * @param teamName          the name of the team
     * @param pos               the position of the Player
     * @param jerseyNum         the jersey number of the Player
     * @param age               the age of the Player
     * @param height            the height of the Player
     * @param weight            the weight of the Player
     * @param yearsOfExperience number of years of experience
     * @param college           the college the Player attended
     */
    public Player(String name, String teamName, String pos, int jerseyNum, int age, String height, int weight,
                  String yearsOfExperience, String college) {
        this.name = name;
        this.teamName = teamName;
        this.pos = pos;
        this.jerseyNum = jerseyNum;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.yearsOfExperience = yearsOfExperience;
        this.college = college;
    }

    /**
     * Obtains the team name for hashing
     *
     * @return the teamName field
     */
    public String getTeamName() {
        return this.teamName;
    }

    /**
     * Obtains the jersey number for hasing
     *
     * @return the jerseyNum field
     */
    public int getJerseyNum() {
        return this.jerseyNum;
    }

    /**
     * Overrides the toString method to print information about the Player
     *
     * @return all the fields of the Player
     */
    @Override
    public String toString() {
        return "Name: " + name + "\nTeam Name: " + teamName + "\nPosition: " + pos + "\nJersey Number: " + jerseyNum +
                "\nAge: " + age + "\nHeight: " + height + "\nWeight: " + weight + "\nYears of Experience: " +
                yearsOfExperience + "\nCollege Attended: " + college;
    }
}
