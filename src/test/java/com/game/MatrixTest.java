package com.game;

import junit.framework.TestCase;

import static org.assertj.core.api.Assertions.assertThat;

public class MatrixTest extends TestCase {

    public void testGetNewBooleanGrid() {
        boolean[][] testBooleanGrid = {
                {false, true, false, false},
                {false, true, false, false},
                {false, true, false, false},
                {false, false, false, false},
        };
        boolean[][] expectedBoolean = {
                {false, false, false, false},
                {true, true, true, false},
                {false, false, false, false},
                {false, false, false, false},
        };
        Matrix matrix = new Matrix(testBooleanGrid);

        assertThat(matrix.getNewBooleanGrid()).isEqualTo(expectedBoolean);
    }
}