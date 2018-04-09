package BoardGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {


    /**
     * Basic parameters of the player class
     */
    private int position;
    private int id;
    private String name;
    private boolean isBankrupt;
    private int money;
    private boolean canMove;
    private int building;
    private List<Integer> land;
    private int stepsInThisTurn;
    private List<Integer> airports;

    /**
     * Constructor of the player
     * @param id the id of the player
     * @param name name of the player
     */
    public Player(int id, String name){
        this.id = id;
        setPosition(0);
        this.name = name;
        this.isBankrupt = false;
        this.money = 3000;
        setCanMove(true);
        setBuilding(0);
        this.land = new ArrayList<>();
        setStepsInThisTurn(0);
        airports = new ArrayList<>();
    }

    /**
     * A player tosses a fair die and returns the result of the face on the die.
     * @return the face on the die. Ranges from 1 to 6.
     */

    public int tossDie(){
        Random random = new Random();
        int step = random.nextInt(6) +1;
        return step;
    }


    /**
     * Setter and Getters functions for these variables.
     *
     *
     */

    public int getPosition(){
        return this.position;
    }

    public void setPosition(int pos){
        this.position = pos;
    }

    public String getName(){
        return this.name;
    }

    public int getMoney(){
        return this.money;
    }

    public int getId(){
        return this.id;
    }

    public void setBankrupt(){
        this.isBankrupt = this.money<0;
    }

    public boolean getIsBankrupt(){
        return  this.isBankrupt;
    }

    public void addMoney(int money){
        this.money += money;
    }

    public void decreaseMoney(int money){
        this.money -= money;
    }

    public boolean getCanMove(){
        return this.canMove;
    }

    public void setCanMove(boolean canMove){
        this.canMove = canMove;
    }

    public void addBuilding(){
        this.building++;
    }

    public void addLand(int id){
        this.land.add(id);
    }

    public int getBuilding(){
        return this.building;
    }

    public int getLandCount(){
        return getLand().size();
    }

    public int getStepsInThisTurn(){
        return this.stepsInThisTurn;
    }

    public void setStepsInThisTurn(int steps){
        this.stepsInThisTurn = steps;
    }

    public void addAirport(int id){
        this.airports.add(id);
    }

    public void setBuilding(int building){
        this.building = building;
    }

    public int getAirportsCount(){
        return getAirports().size();
    }

    public List<Integer> getLand() {
        return land;
    }

    public List<Integer> getAirports() {
        return airports;
    }


    /**
     * Set all of the player's land and airports into empty. This function will be called only if he is bankrupt!!
     */
    public void emptyProperties(){
        this.airports.clear();
        this.land.clear();
        setBuilding(0);
    }

}
