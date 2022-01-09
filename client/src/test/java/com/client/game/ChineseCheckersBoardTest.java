package com.client.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChineseCheckersBoardTest {
    ChineseCheckersBoardBuilder instanceOfBuilder;
    ChineseCheckersBoard instanceOfCheckersBoard;
    int size = 5;
    int numberOfPlayers = 2;
    int height = size + 3 * (size - 1);
    int width = 2 * (size + 2 * (size - 1)) - 1;

    @BeforeEach
    public void init() throws Exception {
        instanceOfBuilder = new ChineseCheckersBoardBuilder();
        instanceOfCheckersBoard = new ChineseCheckersBoard();
        instanceOfBuilder.setNumberOfPlayers(numberOfPlayers);
        instanceOfBuilder.setSize(size);
        instanceOfBuilder.build();
    }

    @Test
    void testBuilding() {
        assertEquals(25, instanceOfBuilder.width);
        assertEquals(17, instanceOfBuilder.height);
        assertEquals(5, instanceOfBuilder.size);
        assertEquals(2, instanceOfBuilder.numberOfPlayers);
    }

    @Test
    void testGetBoard() {
        instanceOfCheckersBoard.board = new int[instanceOfBuilder.height][instanceOfBuilder.width];
        instanceOfCheckersBoard.setBoard(instanceOfCheckersBoard.board, size);
        assertEquals(instanceOfCheckersBoard.board, instanceOfCheckersBoard.getBoard());
    }

    @Test
    void testSetVaildMoves() throws Exception {
        try {
            instanceOfCheckersBoard.board = new int[instanceOfBuilder.height][instanceOfBuilder.width];
            assertEquals(new ChineseCheckersBoardBuilder().setNumberOfPlayers(numberOfPlayers).setSize(size).build(),
                    instanceOfCheckersBoard.setValidMoves(instanceOfBuilder.height, instanceOfBuilder.width));
        } catch (Exception ignored) {
        }
    }

    @Test
    void testSetVaildMovesForNoPlayers() {
        width = 0;
        height = 0;
        instanceOfCheckersBoard.board = new int[height][width];
        try {
            assertEquals(new ChineseCheckersBoardBuilder().setNumberOfPlayers(0).setSize(size).build(),
                    instanceOfCheckersBoard.setValidMoves(height, width));
        } catch (Exception ignored) {
        }
    }

    @Test
    void testMoveOneFiled() {
        instanceOfCheckersBoard.board = new int[instanceOfBuilder.height][instanceOfBuilder.width];
        try {
            assertArrayEquals(new int[] { 7, 11 }, instanceOfCheckersBoard.moveOneField(8, 10, 5));
            assertArrayEquals(new int[] { 7, 9 }, instanceOfCheckersBoard.moveOneField(8, 10, 4));
            assertArrayEquals(new int[] { 8, 8 }, instanceOfCheckersBoard.moveOneField(8, 10, 3));
            assertArrayEquals(new int[] { 9, 9 }, instanceOfCheckersBoard.moveOneField(8, 10, 2));
            assertArrayEquals(new int[] { 9, 11 }, instanceOfCheckersBoard.moveOneField(8, 10, 1));
            assertArrayEquals(new int[] { 8, 12 }, instanceOfCheckersBoard.moveOneField(8, 10, 0));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void checkIfGameEndedTest() {
        assertEquals(1, instanceOfCheckersBoard.checkIfGameEnded());
    }

    @Test
    void chineseCheckersBoardAdapterTest() {
        instanceOfCheckersBoard.board = new int[instanceOfBuilder.height][instanceOfBuilder.width];
        ChineseCheckersBoardAdapter checkersBoardAdapter = new ChineseCheckersBoardAdapter(instanceOfCheckersBoard);
        assertEquals(17, checkersBoardAdapter.getFields().length);

    }

    @Test
    void MouseMoveHandlerTest() {
        instanceOfCheckersBoard.board = new int[instanceOfBuilder.height][instanceOfBuilder.width];
        ChineseCheckersBoardAdapter checkersBoardAdapter = new ChineseCheckersBoardAdapter(instanceOfCheckersBoard);
        MouseMoveHandler mmh = new MouseMoveHandler(checkersBoardAdapter);
        assertEquals(true, mmh instanceof MouseMoveHandler);
    }

}
