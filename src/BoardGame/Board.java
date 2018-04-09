package BoardGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    /**
     * Basic constructor of the game board
     */

    int currentTurn;
    int numOfPlayer;
    Player[] players;
    Piece[] pieces;
    String [] names;

    private int GO_PIECE = 0;
    private int GO_TO_JAIL_PIECE = 30;
    private int JAIL_PIECE = 10;
    private int PARK_PIECE = 20;
    private int LUXURY_TAX_PIECE = 15;
    private int AIRPORT_PIECE_1 = 5;
    private int AIRPORT_PIECE_2 = 25;
    private int LAND_TAX = 35;

    private List<Integer> CHANCE_PIECES;

    /**
     * constructor of game board
     * @param numOfPlayer number of players on the board, restricted from 2 to 4
     */

    public Board(int numOfPlayer){
        CHANCE_PIECES = new ArrayList<>();
        CHANCE_PIECES.add(7);
        CHANCE_PIECES.add(17);
        CHANCE_PIECES.add(27);
        CHANCE_PIECES.add(37);
        this.numOfPlayer = numOfPlayer;
        players = new Player[numOfPlayer];
        currentTurn = 0;
        pieces = new Piece[40];
        Random random = new Random();

        for(int i=0;i<numOfPlayer;i++){
            players[i] = new Player(i,"P"+i);
        }

        for(int i=0;i<pieces.length;i++){
            if(i==GO_PIECE){
                pieces[i] = new GoPiece("Go");
                continue;
            }
            if(i==JAIL_PIECE){
                pieces[i] = new JailPiece("Jail");
                continue;
            }
            if(i==PARK_PIECE){
                pieces[i] = new ParkPiece("Park");
                continue;
            }
            if(CHANCE_PIECES.contains(i)){
                pieces[i] = new ChancePiece("Chance");
                continue;
            }
            if(i==LUXURY_TAX_PIECE){
                pieces[i] = new LuxuryTaxPiece("Luxury Tax");
                continue;
            }
            if(i==AIRPORT_PIECE_1 || i == AIRPORT_PIECE_2){
                int airportIndex = i==AIRPORT_PIECE_1? 1:2;
                pieces[i] = new AirportPiece("Airport"+airportIndex,i);
                continue;
            }
            if(i==LAND_TAX){
                pieces[i] = new LandTaxPiece("Land Tax");
                continue;
            }

            if(i==GO_TO_JAIL_PIECE){
                pieces[i] = new GoToJailPiece("Go to Jail");
                continue;
            }
            pieces[i] = new LandPiece("Land "+i, 150 + random.nextInt(26) * 10,i );

        }

    }

    /**
     * Move current player by advancing several steps.
     * @param step number of steps the current player would go.
     * @return the new piece that the current player goes to.
     */

    public Piece moveCurrentPlayer(int step){
        return movePlayer(getCurrentPlayer(),step);
    }

    public Piece movePlayer(int id,int step){
        return movePlayer(players[id],step);
    }

    public Piece movePlayer(Player player, int step){

        if(!player.getIsBankrupt() && player.getCanMove()){
            int position = player.getPosition();
            int newPosition = position + step;
            if(newPosition >= 40){
                System.out.println("Congratulations! You have earned 500 dollars!");
                player.addMoney(500);
                newPosition %= 40;
            }
            player.setPosition(newPosition);
            if(step>0){
                //handles the case of jumping from "Go to Jail" to the "Jail"
                player.setStepsInThisTurn(step);
            }
            pieces[newPosition].doSomething(player,this);
            player.setBankrupt();
            if(player.getIsBankrupt()){
                returnLands(player);
            }
            return pieces[newPosition];
        }
        else{
            if(!player.getIsBankrupt())
                player.setCanMove(true);
            return pieces[player.getPosition()];
        }
    }

    /**
     * Detects whether only one player survives
     * @return true if only one player is not bankrupt, false otherwise.
     */
    public boolean gameEnds(){
        int inGame = 0;
        for(Player player: players){
            inGame += player.getIsBankrupt()? 0:1;
        }
        return inGame==1;
    }

    public int getWinnerId(){
        return getWinner().getId();
    }

    /**
     * Returns the winner player of the
     * @return
     */
    public Player getWinner(){

        for(Player player:players){
            if(!player.getIsBankrupt()){
                return  player;
            }
        }
        return null;
    }

    /**
     * Returns the owner id of the piece with index i
     * -1 if the piece is not a LandPiece
     * @param i index of the piece on the board
     * @return
     */
    public int getLandOwnerId(int i){
        if(getPiece(i) instanceof LandPiece){
            return ((LandPiece)getPiece(i)).getOwnerId();
        }
        else
            return -2;
    }

    /**
     *
     * @return current player of the board
     */
    public Player getCurrentPlayer(){
        return players[currentTurn];
    }

    /**
     * Get the array of players on the board
     * @return list of players
     */

    public Player[] getPlayers() {
        return players;
    }

    /**
     * Get number of players
     * @return the number of players
     */
    public int getNumOfPlayer(){
        return getPlayers().length;
    }

    /**
     * Set current player to next one
     */
    public void nextTurn(){
        currentTurn = (currentTurn+1)%getNumOfPlayer();
    }


    /**
     * Get the player by id number
     * @param id the id of the player
     * @return the player with this id
     */
    public Player getPlayer(int id){
        return players[id];
    }

    /**
     * Get number of the pieces
     * @return number of pieces
     */
    public int getNumOfPieces(){
        return pieces.length;
    }

    /**
     * Returns the position of the player with some id
     * @param id the id of the player
     * @return position of the player with this id
     */
    public int getPlayerPosition(int id){
        return players[id].getPosition();
    }

    /**
     * Gets the price of the land, -1 if the piece is not a LandPiece.
     * @param pos position of the piece
     * @return price of the land.
     */
    public int getLandPrice(int pos){
        if(! ( pieces[pos] instanceof LandPiece) )
            return -1;
        return ((LandPiece)(pieces[pos])).getPrice();
    }

    /**
     * returns the money of the player with some id
     * @param id the id name of the player
     * @return money of the player with this id
     */
    public int getPlayerMoney(int id){
        return players[id].getMoney();
    }

    /**
     * returns the piece of the board with index i
     * @param i index of the piece
     * @return the piece with index i
     */
    public Piece getPiece(int i){
        return pieces[i];
    }

    /**
     * Set all the player's land and airports into empty.
     * @param player the player that will be deleted.
     */
    public void returnLands(Player player){
        for(int land:player.getLand()){
            ((LandPiece)pieces[land]).setOwnerId(-1);

        }
        for(int airport:player.getAirports()){
            ((AirportPiece)pieces[airport]).setOwnerId(-1);
        }
        player.emptyProperties();
    }
}
