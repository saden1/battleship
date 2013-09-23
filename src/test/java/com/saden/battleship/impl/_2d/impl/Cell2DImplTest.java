/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl._2d.impl;

import com.saden.battleship.impl._2d.Cell2D;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

/**
 *
 * @author saden
 */
public class Cell2DImplTest {

    Cell2D sut;
    Integer x;
    Integer y;

    @BeforeMethod
    public void initMocks() {
        x = 1;
        y = 1;

        sut = new Cell2DImpl(x, y);
    }

    @Test
    public void givenGivenNullXGetXShouldReturnNull() {
        sut = new Cell2DImpl(null, null);

        assertThat(sut.getX()).isNull();
    }

    @Test
    public void givenGivenValidXGetXShouldReturnValidX() {
        sut = new Cell2DImpl(x, null);

        assertThat(sut.getX()).isSameAs(x);
    }

    @Test
    public void givenGivenNullYGetYShouldReturnNull() {
        sut = new Cell2DImpl(null, null);

        assertThat(sut.getY()).isNull();
    }

    @Test
    public void givenGivenValidYGetYShouldReturnValidY() {
        sut = new Cell2DImpl(null, y);

        assertThat(sut.getY()).isSameAs(y);
    }

    @DataProvider
    Object[][] invalidCell() {
        return new Object[][]{
            { null, 1 },
            { 1, null }
        };
    }

    @Test(dataProvider = "invalidCell")
    public void givenInvalidCellStateEqualsShouldReturnFalse(Integer x, Integer y) {
        Cell2D cellA = new Cell2DImpl(x, y);
        Cell2D cellB = new Cell2DImpl(2, 1);


        Boolean result = cellA.equals(cellB);

        assertThat(result).isFalse();
    }

    @Test
    public void givenTwoUnequalCellsEqualsShouldReturnFalse() {
        Cell2D cellA = new Cell2DImpl(1, 1);
        Cell2D cellB = new Cell2DImpl(2, 1);

        assertThat(cellA).isNotEqualTo(cellB);
    }

    @Test
    public void givenTwoEqualCellsEqualsShouldReturnTrue() {
        Cell2D cellA = new Cell2DImpl(1, 1);
        Cell2D cellB = new Cell2DImpl(1, 1);

        assertThat(cellA).isEqualTo(cellB);
    }

    @Test
    public void givenNullEqualsShouldReturnFalse() {
        Cell2D cellA = new Cell2DImpl(1, 1);
        Cell2D cellB = null;
        Boolean result = cellA.equals(cellB);

        assertThat(result).isFalse();
    }

    @Test
    public void givenDifferentClassEqualsShouldReturnFalse() {
        Cell2D cellA = new Cell2DImpl(1, 1);
        Integer cellB = 1;

        Boolean result = cellA.equals(cellB);

        assertThat(result).isFalse();
    }

    @Test(dataProvider = "invalidCell")
    public void givenInvalidCellStateHashCodeShouldBeDiffrent(Integer x, Integer y) {
        Cell2D cellA = new Cell2DImpl(x, y);
        Cell2D cellB = new Cell2DImpl(2, 1);

        assertThat(cellA.hashCode()).isNotEqualTo(cellB.hashCode());
    }

    @Test
    public void givenTwoUnequalCellsHashCodeShouldBeDiffrent() {
        Cell2D cellA = new Cell2DImpl(1, 1);
        Cell2D cellB = new Cell2DImpl(2, 1);

        assertThat(cellA.hashCode()).isNotEqualTo(cellB.hashCode());
    }

    @Test
    public void givenTwoEqualCellsHashCodeShouldBeTheSame() {
        Cell2D cellA = new Cell2DImpl(1, 1);
        Cell2D cellB = new Cell2DImpl(1, 1);

        assertThat(cellA.hashCode()).isEqualTo(cellB.hashCode());
    }
}
