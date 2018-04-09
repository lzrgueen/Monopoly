package BoardGame;

public class AirportPiece extends Piece{


    /**
     * The subclass of Game Piece that represents the airport
     */

    private int price;
    private int ownerId = -1;
    private int index;

    public AirportPiece(String name,int index){
        super(name);
        price = 250;
        this.index = index;
    }

    /**
     * If nobody owns the airport before, the player will buy it.
     * Otherwise, if other player has stepped on the airport, he will pay the owner rent = steps * 25
     * @param player player constructor
     * @param board
     */

    @Override
    public void doSomething(Player player, Board board){
        if(ownerId>=0 && ownerId!= player.getId()){
            int numOfAirports = board.getPlayer(ownerId).getAirportsCount();
            int tax = player.getStepsInThisTurn() * 25 * numOfAirports;
            System.out.println(player.getName()+" paid "+tax +" dollars to "+board.getPlayer(ownerId).getName());
            player.decreaseMoney(tax);
            board.getPlayer(getOwnerId()).addMoney(tax);

        }
        else{
            if(ownerId==-1){
                System.out.println("The price of "+this.getName()+" is "+this.price);
                if(player.getMoney()> this.price) {
                    System.out.println(player.getName() + " buys " + this.getName());
                    setOwnerId(player.getId());
                    player.addAirport(index);
                    player.decreaseMoney(this.price);
                }
            }

        }
    }

    /**
     * Setter and getter functons
     *
     */
    public void setOwnerId(int id){
        this.ownerId = id;
    }

    public int getOwnerId(){
        return this.ownerId;
    }
}
