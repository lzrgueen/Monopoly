package BoardGame;

public class LuxuryTaxPiece extends Piece {

    public LuxuryTaxPiece(String name){
        super(name);
    }

    @Override
    public void doSomething(Player player, Board board){

        int tax  = (int)(player.getMoney() * 0.05);
        player.decreaseMoney(tax);
        System.out.println(player.getName() + " has " +  player.getMoney() + " money.Therefore, he will be punished by "+ tax +" dollars.");
    }
}
