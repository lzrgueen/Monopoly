package engine.game;

import BoardGame.*;

import java.util.Scanner;

public class Game {
    Board board;

    public Game(int numOfPlayers){
        board = new Board(numOfPlayers);
    }

    public static void main(String [] args){
        System.out.println("Monopoly game\n");
        System.out.println("Please select number of players (2-4) \n");
        Scanner sc = new Scanner(System.in);
        int numPlayers = 0;
        while(numPlayers>4 || numPlayers<2){
            numPlayers = sc.nextInt();
            if(numPlayers>4 || numPlayers <2)
                System.out.println("Invalid Number! Please retype the number of players!");
        }
        sc.close();
        Game game = new Game(numPlayers);
        game.startNewGame();
    }


    public void startNewGame(){
        System.out.println("Game Start!");
        while(!board.gameEnds()){
            Player currentPlayer = board.getCurrentPlayer();
            System.out.println(currentPlayer.getName() + "'s Turn");
            if(!currentPlayer.getIsBankrupt()){
                int dice = currentPlayer.tossDie();
                if(board.getCurrentPlayer().getCanMove())
                    System.out.println(currentPlayer.getName() + " throws "+dice);
                else{
                    System.out.println(board.getCurrentPlayer().getName()+ " cannot move at this turn!");
                }
                int newPosition = currentPlayer.getPosition() + dice;
                Piece newPiece = board.movePlayer(currentPlayer,dice);
                System.out.println(currentPlayer.getName()+"'s money is "+currentPlayer.getMoney());
                if( newPiece instanceof AirportPiece){
                    int ownerId = ((AirportPiece)newPiece).getOwnerId();
                    if(ownerId!= -1 && ownerId!=currentPlayer.getId()){
                        System.out.println(board.getPlayer(ownerId).getName()+"'s money is "+board.getPlayer(ownerId).getMoney());
                    }
                }
                if( newPiece instanceof LandPiece){
                    int ownerId = ((LandPiece)newPiece).getOwnerId();
                    if(ownerId!= -1 && ownerId!=currentPlayer.getId()){
                        System.out.println(board.getPlayer(ownerId).getName()+"'s money is "+board.getPlayer(ownerId).getMoney());
                    }
                }
                if(currentPlayer.getIsBankrupt()){
                    System.out.println(currentPlayer.getName() +" has no money! He is now bankrupt!");
                }
                else{
                    System.out.println(currentPlayer.getName() + " now stays at "+currentPlayer.getPosition() );
                }
            }

            board.nextTurn();
        }
        System.out.println("Game ends! The winner is "+ board.getWinner().getName());



    }
}
