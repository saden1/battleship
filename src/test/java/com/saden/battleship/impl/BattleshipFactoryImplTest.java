/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl;

import com.saden.battleship.Battleship;
import com.saden.battleship.BattleshipItemFactory;
import com.saden.battleship.impl._2d.Battleship2DObjectFactory;
import com.saden.battleship.impl.core.Board;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import org.testng.annotations.*;

/**
 *
 * @author saden
 */
public class BattleshipFactoryImplTest {

    BattleshipFactoryImpl sut;
    Battleship2DObjectFactory objectFactory;

    @BeforeMethod
    public void initMocks() {
        objectFactory = mock(Battleship2DObjectFactory.class);
        sut = new BattleshipFactoryImpl(objectFactory);
    }

    @DataProvider
    Object[][] invalidDimensions() {
        return new Object[][]{
            { null, 4 },
            { 4, null }
        };
    }

    @Test(dataProvider = "invalidDimensions", expectedExceptions = NullPointerException.class)
    public void givenInvalidParamsCreate2DGameShouldThrowException(Integer x, Integer y) {
        sut.create2DGame(x, y);
    }

    @Test
    public void givenValidDimensionsCreate2DGameShouldReturnBattleship() {
        Integer x = 1;
        Integer y = 1;
        Board board = mock(Board.class);
        BattleshipItemFactory itemFactory = mock(BattleshipItemFactory.class);

        given(objectFactory.createBoard(x, y)).willReturn(board);
        given(objectFactory.getItemFactory()).willReturn(itemFactory);

        Battleship result = sut.create2DGame(x, y);
        assertThat(result).isNotNull();

        verify(objectFactory).createBoard(x, y);
        verify(objectFactory).getItemFactory();
    }
}
