import BoardGame.Board;

import BoardGame.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void initializeTest() throws Exception{
        Player p1 = new Player(0,"P1");
        assertEquals(p1.getName(),"P1");
        assertEquals(p1.getMoney(),3000);
        assertEquals(p1.getIsBankrupt(),false);
        assertEquals(p1.getId(),0);
        assertEquals(p1.getCanMove(),true);
        assertEquals(p1.getAirportsCount(),0);
        assertEquals(p1.getLandCount(),0);
        assertEquals(p1.getBuilding(),0);
    }

    @Test
    public void OnBoardTest() throws Exception{
        Board board = new Board(4);
        assertEquals(board.getNumOfPlayer(),4);
        assertEquals(board.getPlayer(0).getName(),"P0");
        assertEquals(board.getPlayer(1).getName(),"P1");
        assertEquals(board.getPlayer(2).getName(),"P2");
        assertEquals(board.getPlayer(3).getName(),"P3");
        assertEquals(board.getPlayer(0).getMoney(),3000);
        assertEquals(board.getPlayer(0).getPosition(),0);
        assertEquals(board.getPlayer(1).getPosition(),0);
        assertEquals(board.getPlayer(2).getPosition(),0);
        assertEquals(board.getPlayer(3).getPosition(),0);
    }

    @Test
    public void tossDieTest() throws Exception{
        Player p1 = new Player(0,"P1");
        boolean flag = true;
        for(int i=0;i<100;i++){
            int face = p1.tossDie();
            flag = face>=1 && face<=6;
            if(!flag)
                break;
        }
        assertTrue(flag);
    }
}
