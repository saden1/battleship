/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl._2d.impl;

import com.saden.battleship.impl._2d.Cell2D;
import com.saden.battleship.impl.core.CoreObjectFactory;
import com.saden.battleship.impl.core.Vehicle;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.testng.annotations.*;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author saden
 */
public class Battleship2DItemFactoryImplTest {

    Battleship2DItemFactoryImpl sut;
    CoreObjectFactory objectFactory;

    @BeforeMethod
    public void initMocks() {
        objectFactory = mock(CoreObjectFactory.class);

        sut = new Battleship2DItemFactoryImpl(objectFactory);
    }

    @DataProvider
    Object[][] invalidCreateShipParams() {
        return new Object[][]{
            { null, mock(Set.class) },
            { "", mock(Set.class) },
            { "ship", null }
        };
    }

    @Test(dataProvider = "invalidCreateShipParams",
            expectedExceptions = { NullPointerException.class, IllegalArgumentException.class })
    public void givenInvalidInputCreateVehicleShouldThrowException(String name,
            Set<Cell2D> cells) {
        sut.createVehicle(name, cells);
    }

    @Test
    public void givenValidParamsCreateShipShouldReturnVehicle() {
        Integer x = 1;
        Integer y = 1;
        Integer maxLife = 1;
        String name = "ship";

        Set<Cell2D> cells = spy(new HashSet<Cell2D>(maxLife));
        Cell2D cell = new Cell2DImpl(x, y);
        cells.add(cell);

        Map<Object, Object> damageMap = mock(Map.class);
        given(objectFactory.createHashMap(maxLife)).willReturn(damageMap);

        Vehicle result = sut.createVehicle(name, cells);

        assertThat(result).isNotNull();
        verify(cells).isEmpty();
        verify(cells).size();
        verify(objectFactory).createHashMap(maxLife);
        verify(cells).iterator();
        verify(damageMap).put(cell, Boolean.FALSE);
    }

    @Test
    public void givenLocationCreateCellShouldReturnCell() {
        Cell2D result = sut.createCell(null, null);

        assertThat(result).isNotNull();
    }
}
