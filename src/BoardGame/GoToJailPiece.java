package BoardGame;

public class GoToJailPiece extends Piece{

    public GoToJailPiece(String name){
        super(name);
    }

    @Override
    public void doSomething(Player player,Board board){
        System.out.println(player.getName()+" will jump to the jail!");
        board.movePlayer(player,-board.getNumOfPieces()/2);
    }

}
