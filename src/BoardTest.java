import BoardGame.Board;

import BoardGame.LandPiece;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testMovePlayer() throws Exception{
        Board board = new Board(2);
        board.getPlayer(0).setPosition(38);
        assertEquals(board.getCurrentPlayer().getId(),0);
        board.movePlayer(board.getPlayer(0),7);
        assertEquals(board.getPlayer(0).getMoney(),3250);
        assertEquals(board.getPlayer(0).getAirportsCount(),1);
        board.nextTurn();
        assertEquals(board.getCurrentPlayer().getId(),1);
        board.movePlayer(board.getPlayer(1),10);
        assertFalse(board.getPlayer(1).getCanMove());
        assertEquals(board.getPlayerMoney(1),2750);
        board.nextTurn();
        assertEquals(board.getCurrentPlayer().getId(),0);
        board.getCurrentPlayer().decreaseMoney(3200);
        board.movePlayer(board.getCurrentPlayer(),25);
        assertEquals(board.getCurrentPlayer().getPosition(),10);
        assertEquals(board.getCurrentPlayer().getIsBankrupt(),true);
        int oldPos = board.getPlayerPosition(0);
        board.movePlayer(board.getPlayer(0),10);
        int currentPos = board.getPlayerPosition(0);
        assertEquals(oldPos,currentPos);
        assertEquals(board.getPlayer(0).getAirportsCount(),0);
        assertTrue(board.gameEnds());
        assertEquals(board.getWinner().getName(),"P1");
        assertEquals(board.getWinnerId(),1);

    }

    @Test
    public void testMovePlayer2() throws Exception{
        Board board = new Board(3);
        board.getPlayer(0).setPosition(38);
        assertEquals(board.getCurrentPlayer().getId(),0);
        board.movePlayer(board.getPlayer(0),7);
        assertEquals(board.getPlayer(0).getMoney(),3250);
        assertEquals(board.getPlayer(0).getAirportsCount(),1);
        board.nextTurn();
        board.movePlayer(board.getPlayer(1),20);
        assertEquals(board.getPlayer(1).getMoney(),3000);
        board.nextTurn();
        board.movePlayer(board.getPlayer(2),5);
        assertEquals(board.getPlayer(2).getMoney(),2875);
        assertEquals(board.getPlayer(0).getMoney(),3375);
    }


    @Test
    public void testMoveChance() throws Exception{
        Board board = new Board(4);
        List<Integer> amount = new ArrayList<>();
        amount.add(50);
        amount.add(250);
        amount.add(100);
        amount.add(200);
        board.getPlayer(0).setPosition(7);
        boolean flag = false;
        for(int i=0;i<20;i++){
            int money = board.getPlayer(0).getMoney();
            if(i%2==0)
                board.movePlayer(board.getPlayer(0),10);
            else
                board.movePlayer(board.getPlayer(0),-10);
            int new_money = board.getPlayer(0).getMoney();
            flag = amount.contains(Math.abs(new_money-money));
            if(flag==false)
                break;
        }



        assertTrue(flag);
    }



    @Test
    public void testMoveGo() throws Exception{
        Board board = new Board(4);
        board.movePlayer(board.getPlayer(0),40);
        assertEquals(board.getPlayer(0).getMoney(),4000);
    }

    @Test
    public void testLandPrice() throws Exception{
        Board board = new Board(2);
        boolean flag = true;
        for(int i=0;i<board.getNumOfPieces();i++){
            int price = board.getLandPrice(i);
            if(board.getPiece(i) instanceof LandPiece){
                flag = price>=150 && price<= 400 && price%10 ==0;
            }
            else{
                flag = price== -1;
            }
            if(!flag)
                break;
        }
        assertTrue(flag);
    }


    @Test
    public void testLuxuryTax() throws Exception{
        Board board = new Board(2);
        board.movePlayer(0,15);
        assertEquals(board.getPlayerMoney(0),2850);
    }

    @Test
    public void testLandTax() throws Exception{
        Board board = new Board(2);
        board.movePlayer(0,2);
        assertEquals(board.getLandOwnerId(2),0);
        board.movePlayer(0,7);
        board.movePlayer(0,40);
        int oldMoney = board.getPlayerMoney(0);
        board.movePlayer(0,26);
        int newMoney = board.getPlayerMoney(0);
        assertEquals(oldMoney-newMoney,65);

    }

    @Test
    public void testBuyLand() throws Exception{
        Board board = new Board(2);
        board.movePlayer(0,2);
        assertEquals(board.getLandOwnerId(2),0);
        int oldMoney = board.getPlayerMoney(0);
        board.nextTurn();
        int oldMoney1 = board.getPlayerMoney(1);
        board.moveCurrentPlayer(2);
        int price = board.getLandPrice(2);
        int rent = ( price - 40)/ 10;
        int newMoney = board.getPlayerMoney(0);
        int newMoney1 = board.getPlayerMoney(1);
        assertEquals(rent,newMoney-oldMoney);
        assertEquals(rent,oldMoney1-newMoney1);

    }

    @Test
    public void testBuyAirport() throws Exception{

    }

    @Test
    public void testGetLandOwnerId() throws Exception{
        Board board = new Board(2);
        board.movePlayer(0,2);
        assertEquals(board.getLandOwnerId(2),0);
        assertEquals(board.getLandOwnerId(3),-1);
        assertEquals(board.getLandOwnerId(10),-2);
    }

}
