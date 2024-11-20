package com.example.pumpkin;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicTacToeModelTest {

    private TicTacToeModel model;

    @Before
    public void setUp() {
        model = new TicTacToeModel();
    }

    @Test
    public void testMakeMove_ValidMove() {
        assertTrue(model.makeMove(0, 0)); //Det första draget måste vara giltigt
        assertEquals('X', model.getBoard()[0][0]); //X måste ha satts in.
    }

    @Test
    public void testMakeMove_InvalidMove() {
        model.makeMove(0, 0); // Det första draget är gjort
        assertFalse(model.makeMove(0, 0)); //Kan inte göra ett drag på samma plats
    }

    @Test
    public void testMakeMove_SwitchPlayer() {
        model.makeMove(0, 0); // X-drag
        assertEquals('O', model.getCurrentPlayer()); // Nästa spelare måste vara ”O”
        model.makeMove(1, 1); // O-drag
        assertEquals('X', model.getCurrentPlayer()); // Nästa spelare måste vara ”X”
    }

    @Test
    public void testCheckWinner_RowWin() {
        model.makeMove(0, 0); // X
        model.makeMove(1, 0); // O
        model.makeMove(0, 1); // X
        model.makeMove(1, 1); // O
        model.makeMove(0, 2); //X vinner
        assertEquals('X', model.checkWinner());
    }

    @Test
    public void testCheckWinner_ColumnWin() {
        model.makeMove(0, 0); // X
        model.makeMove(0, 1); // O
        model.makeMove(1, 0); // X
        model.makeMove(1, 1); // O
        model.makeMove(2, 0); // X vinner.
        assertEquals('X', model.checkWinner());
    }

    @Test
    public void testCheckWinner_DiagonalWin() {
        model.makeMove(0, 0); // X
        model.makeMove(0, 1); // O
        model.makeMove(1, 1); // X
        model.makeMove(0, 2); // O
        model.makeMove(2, 2); // X vinner.
        assertEquals('X', model.checkWinner());
    }

    @Test
    public void testCheckWinner_NoWinner() {
        model.makeMove(0, 0); // X
        model.makeMove(0, 1); // O
        model.makeMove(0, 2); // X
        assertEquals(' ', model.checkWinner()); //Inga vinnare
    }

    @Test
    public void testCheckWinner_BoardFullWithNoWinner() {
        model.makeMove(0, 0); // X
        model.makeMove(0, 1); // O
        model.makeMove(0, 2); // X
        model.makeMove(1, 0); // O
        model.makeMove(1, 1); // X
        model.makeMove(1, 2); // O
        model.makeMove(2, 0); // X
        model.makeMove(2, 1); // O
        model.makeMove(2, 2); // X

        assertEquals(' ', model.checkWinner()); // Inga vinnare
        assertTrue(model.isBoardFull()); // Spelbrädet fullt
    }
}
