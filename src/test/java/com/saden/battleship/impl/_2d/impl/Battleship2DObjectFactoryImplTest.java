/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl._2d.impl;

import com.google.common.collect.Table;
import com.saden.battleship.BattleshipItemFactory;
import com.saden.battleship.impl._2d.Cell2D;
import com.saden.battleship.impl.core.CoreObjectFactory;
import com.saden.battleship.impl.core.Board;
import com.saden.battleship.impl.core.Vehicle;
import java.util.HashSet;
import java.util.Set;
import org.testng.annotations.*;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author saden
 */
public class Battleship2DObjectFactoryImplTest {

    Battleship2DObjectFactoryImpl sut;
    BattleshipItemFactory<Cell2D> itemFactory;
    CoreObjectFactory objectFactory;

    @BeforeMethod
    public void initMocks() {
        itemFactory = mock(BattleshipItemFactory.class);
        objectFactory = mock(CoreObjectFactory.class);

        sut = new Battleship2DObjectFactoryImpl(itemFactory, objectFactory);
    }

    @Test
    public void givenValidParamsCreateBoardShouldCreateBoard() {
        Integer x = 1;
        Integer y = 1;

        Vehicle vehicle = mock(Vehicle.class);
        Table table = mock(Table.class);
        Set vehicles = new HashSet<>();
        vehicles.add(vehicle);

        given(objectFactory.createHashTable(x, y)).willReturn(table);
        given(objectFactory.createHashSet(x * y)).willReturn(vehicles);

        Board result = sut.createBoard(x, y);

        assertThat(result).isNotNull();
        assertThat(result.getVehicles())
                .containsExactly(vehicle);
        verify(objectFactory).createHashTable(x, y);
        verify(objectFactory).createHashSet(x * y);
    }

    @Test
    public void givenValidParamsGetItemFactoryShouldCreateBattleshipItemFactory() {
        BattleshipItemFactory result = sut.getItemFactory();

        assertThat(result).isNotNull();
    }
}
