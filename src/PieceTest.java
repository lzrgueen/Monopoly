import BoardGame.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Test
    public void testPieceCount() throws Exception{
        Board board = new Board(2);
        assertEquals(board.getNumOfPieces(),40);
        assertTrue(board.getPiece(0) instanceof GoPiece);
        assertTrue(board.getPiece(10) instanceof JailPiece);
        assertTrue(board.getPiece(20) instanceof ParkPiece);
        assertTrue(board.getPiece(30) instanceof GoToJailPiece);
        assertTrue(board.getPiece(5) instanceof AirportPiece);
        assertTrue(board.getPiece(25) instanceof AirportPiece);
        assertTrue(board.getPiece(15) instanceof LuxuryTaxPiece);
        assertTrue(board.getPiece(35) instanceof LandTaxPiece);
        assertTrue(board.getPiece(7) instanceof ChancePiece);
        assertTrue(board.getPiece(17) instanceof ChancePiece);
        assertTrue(board.getPiece(27) instanceof ChancePiece);
        assertTrue(board.getPiece(37) instanceof ChancePiece);
        assertTrue(board.getPiece(14) instanceof LandPiece);
        assertTrue(board.getPiece(2) instanceof LandPiece);
    }


}
