package BoardGame;

public abstract class Piece {
    String name;

    public Piece(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void doSomething(Player player, Board board);
}
