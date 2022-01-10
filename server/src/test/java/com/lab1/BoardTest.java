package com.lab1;

import com.board.ChineseCheckersBoard;
import com.board.ChineseCheckersBoardBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    public void builderTest() throws Exception {
        ChineseCheckersBoardBuilder cb = new ChineseCheckersBoardBuilder();
        cb.setNumberOfPlayers(0).setSize(5);
        ChineseCheckersBoard c = cb.build();
        Assertions.assertNotNull(c.getBoard());
        cb.setNumberOfPlayers(2);
        c = cb.build();
        Assertions.assertNotNull(c.getBoard());
        cb.setNumberOfPlayers(3);
        c = cb.build();
        Assertions.assertNotNull(c.getBoard());
        cb.setNumberOfPlayers(4);
        c = cb.build();
        Assertions.assertNotNull(c.getBoard());
        cb.setNumberOfPlayers(6);
        c = cb.build();
        Assertions.assertNotNull(c.getBoard());
    }

    @Test
    public void boardTest() throws Exception {
        ChineseCheckersBoard b = new ChineseCheckersBoardBuilder().setNumberOfPlayers(6).setSize(5).build();
        ChineseCheckersBoard l = b.setValidMoves(2,12);
        Assertions.assertEquals(1, l.getBoard()[4][10]);
        Assertions.assertEquals(0, l.getBoard()[4][12]);
        l = b.setValidMoves(5,21);
        Assertions.assertEquals(1, l.getBoard()[5][17]);
        Assertions.assertEquals(0, l.getBoard()[6][18]);
        l = b.setValidMoves(11,21);
        Assertions.assertEquals(1, l.getBoard()[11][17]);
        Assertions.assertEquals(0, l.getBoard()[10][18]);
        l = b.setValidMoves(14,12);
        Assertions.assertEquals(1, l.getBoard()[12][10]);
        Assertions.assertEquals(0, l.getBoard()[12][12]);
        l = b.setValidMoves(11,3);
        Assertions.assertEquals(1, l.getBoard()[11][7]);
        Assertions.assertEquals(0, l.getBoard()[10][6]);
        l = b.setValidMoves(5,3);
        Assertions.assertEquals(1, l.getBoard()[5][7]);
        Assertions.assertEquals(0, l.getBoard()[6][8]);
    }
}
