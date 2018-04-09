package BoardGame;

public class LandPiece extends Piece{

    private int index;
    private int price;
    private int ownerId= -1;
    private int building = 0;

    /**
     * Constructor of the LandPiece class
     * @param name name of the piece
     * @param price priec of the land
     * @param index index of the land
     */

    public LandPiece(String name, int price,int index){
        super(name);
        this.price = price;
        this.index = index;
    }

    /**
     * Set its ownerId
     * @param ownerId the new id of the owner.
     */
    public void setOwnerId(int ownerId){
        this.ownerId = ownerId;
    }

    /**
     * Getter functions of its price
     * @return the price of the land
     */
    public int getPrice(){
        return price;
    }

    /**
     * Build house when the owner steps on the land, and the maximum number of building on the land is 5.
     */
    public void buildHouse(){
        if(building<5){
            building+=1;
        }

    }

    /**
     * Getter function for the ownerId.
     * @return ownerId
     */
    public int getOwnerId(){
        return this.ownerId;
    }

    /**
     * If the owner steps on the land, he will automatically build a building as long as he has money and the building count is less than 5.
     * If other player steps on it, he will be punished by some rent.
     * If the land does not own to anybody before, the player will buy this land as long as he has money.
     * @param player the player on this land
     * @param board the board of the piece
     */
    @Override
    public void doSomething(Player player, Board board){
        if(ownerId>=0){
            if(ownerId == player.getId()){
                if(player.getMoney()>=100 && building<5){
                    System.out.println(player.getName() + " builds a new house at "+this.getName());
                    buildHouse();
                    player.addBuilding();
                    player.decreaseMoney(100);
                }

            }
            else{
                int basic_tax = (price/10 - 4) ;
                int multiple = building==0? 1: (building-1) * 2;
                int tax = basic_tax * multiple;
                System.out.println(player.getName()+" paid "+tax +" dollars to "+board.getPlayer(ownerId).getName());
                player.decreaseMoney(tax);
                board.getPlayer(ownerId).addMoney(tax);
            }
        }
        else{
                    System.out.println("The price of "+this.getName()+" is "+this.price);
                    if(player.getMoney()> this.price) {
                        System.out.println(player.getName() + " buys " + this.getName());
                        setOwnerId(player.getId());
                        player.addLand(this.index);
                        player.decreaseMoney(this.price);
                    }
        }
    }

}
