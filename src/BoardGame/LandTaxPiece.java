package BoardGame;

public class LandTaxPiece extends Piece{

    public LandTaxPiece(String name){
        super(name);
    }

    @Override
    public void doSomething(Player player, Board board){
        int land = player.getLandCount();
        int building = player.getBuilding();

        int tax  = land * 20 + 25 * building;
        player.decreaseMoney(tax);
        System.out.println(player.getName() + " has " + land + " lands and "+ building + " buildings. Therefore, he will be punished by "+ tax +" dollars.");
    }
}
