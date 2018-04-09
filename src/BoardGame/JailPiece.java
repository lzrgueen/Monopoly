package BoardGame;

public class JailPiece extends Piece {

    public JailPiece(String name){
        super(name);
    }

    @Override
    public void doSomething(Player player,Board board){
        System.out.println("Welcome to the jail! You are fined 250 dollars and will be resting for one turn!");
        player.decreaseMoney(250);
        player.setCanMove(false);
    }
}
