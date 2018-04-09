package BoardGame;

public class GoPiece extends Piece {

    public GoPiece(String name){
        super(name);
    }

    @Override
    public void doSomething(Player player, Board board){
        System.out.println("Congratulations! As you have stepped on the Go Tile, you will be awarded additional $500 dollars!");
        player.addMoney(500);
    }

}
