package BoardGame;

import java.util.Random;

public class ChancePiece extends Piece {


    private int[] chances={-100,-100,200,250,-200,-100,100,100,100,-250,50,50,-50,-50};

    public ChancePiece(String name){
        super(name);
    }

    /**
     * The player will randomly increase or decrease some money.
     * @param player the player on this piece
     * @param board the board of the piece
     */
    @Override
    public void doSomething(Player player, Board board){
        Random random = new Random();
        int chancesIndex = random.nextInt(chances.length);
        int amount = chances[chancesIndex];
        System.out.println(player.getName() + " goes to the CHANCE!");
        if(amount>0){
            System.out.println("You luckily gained "+amount + " dollars!");
        }
        else{
            System.out.println("Unfortunately! You lose "+Math.abs(amount) + " dollars!");
        }
        player.addMoney(amount);
    }
}
