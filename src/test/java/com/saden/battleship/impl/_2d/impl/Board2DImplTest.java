/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl._2d.impl;

import com.google.common.base.Optional;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.saden.battleship.impl._2d.Cell2D;
import com.saden.battleship.impl.core.Vehicle;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import org.testng.annotations.*;

/**
 *
 * @author saden
 */
public class Board2DImplTest {

    Board2DImpl sut;
    Set<Vehicle> vehicles;
    Table<Integer, Integer, Vehicle> table;

    @BeforeMethod
    public void initMocks() {
        vehicles = spy(new HashSet<Vehicle>());
        table = spy(HashBasedTable.<Integer, Integer, Vehicle>create(5, 5));

        sut = new Board2DImpl(table, vehicles);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void givenNullVehiclesGetVehiclesShouldThrowException() {
        //let's change the state of the board so that the list of vehicles is null
        sut = new Board2DImpl(null, null);

        sut.getVehicles();
    }

    @Test
    public void givenValidVehiclesStateGetVehiclesShouldReturnNewVehicles() {
        Set<Vehicle> result = sut.getVehicles();

        //verify that we are not returning the internal list of vehicles
        assertThat(result).isNotSameAs(vehicles);
    }

    @DataProvider
    Object[][] cellParams() {
        return new Object[][]{
            { new Cell2DImpl(1, 1) }
        };
    }

    @Test(dataProvider = "cellParams",
            expectedExceptions = NullPointerException.class)
    public void givenNullTableGetCelShouldThrowException(Cell2DImpl cell) {
        //let's change the state of the board so that the table is null
        sut = new Board2DImpl(null, null);

        sut.getVehichel(cell);
    }

    @Test(dataProvider = "cellParams")
    public void givenValidCellGetCellShouldReturnAbsentOptional(Cell2DImpl cell) {
        given(table.get(cell.getX(), cell.getY())).willReturn(null);

        Optional<Vehicle> result = sut.getVehichel(cell);

        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isFalse();
    }

    @Test(dataProvider = "cellParams")
    public void givenValidCellGetCellShouldReturnPresentOptional(Cell2DImpl cell) {
        Vehicle vehicle = mock(Vehicle.class);
        given(table.get(cell.getX(), cell.getY())).willReturn(vehicle);

        Optional<Vehicle> result = sut.getVehichel(cell);

        assertThat(result).isNotNull();
        assertThat(result.isPresent()).isTrue();
    }

    @DataProvider
    Object[][] invalidAddVehicleParams() {
        return new Object[][]{
            { null, new Cell2DImpl(1, 1) },
            { mock(Vehicle.class), null },
            { mock(Vehicle.class), new Cell2DImpl(null, 1) },
            { mock(Vehicle.class), new Cell2DImpl(1, null) }
        };
    }

    @Test(dataProvider = "invalidAddVehicleParams",
            expectedExceptions = { NullPointerException.class })
    public void givenInvalidParamsAddVehicleShouldThrowException(Vehicle vehicle, Cell2D cell) {
        sut.addVehicle(vehicle, cell);
    }

    @Test
    public void givenValidParamsAddVehicleShouldReturn() {
        Integer x = 1;
        Integer y = 1;
        Cell2D cell = spy(new Cell2DImpl(x, y));
        Vehicle vehicle = mock(Vehicle.class);

        sut.addVehicle(vehicle, cell);

        verify(cell).getX();
        verify(cell).getY();
        assertThat(table.size()).isEqualTo(1);
    }

    @DataProvider
    Object[][] invalidVehicleParam() {
        return new Object[][]{
            { null },
            { mock(Vehicle.class) }
        };
    }

    @Test(dataProvider = "invalidVehicleParam")
    public void givenNullHasVehicleShouldReturnFalse(Vehicle vehicle) {
        Boolean result = sut.hasVehicle(vehicle);

        assertThat(result).isFalse();
    }

    @Test
    public void givenExistingVehicleHasVehicleShouldReturnTrue() {
        Integer x = 1;
        Integer y = 1;
        Vehicle vehicle = mock(Vehicle.class);
        table.put(x, y, vehicle);

        Boolean result = sut.hasVehicle(vehicle);

        assertThat(result).isTrue();
        verify(table).containsValue(vehicle);
    }

    @DataProvider
    Object[][] invalidEmptyParams() {
        return new Object[][]{
            { null },
            { mock(Set.class) }
        };
    }

    @Test(dataProvider = "invalidEmptyParams",
            expectedExceptions = { NullPointerException.class, IllegalArgumentException.class })
    public void givenNullEmptyShouldThrowException(Set<Cell2D> cells) {
        sut.empty(cells);
    }

    @DataProvider
    Object[][] dummyCells() {
        Integer x = 1;
        Integer y = 1;
        Cell2D cell = new Cell2DImpl(x, y);
        Set<Cell2D> cells = spy(new HashSet<Cell2D>());
        cells.add(cell);

        return new Object[][]{
            { cells }
        };
    }

    @Test(dataProvider = "dummyCells")
    public void givenOccupiedCellsEmptyShouldReturnFalse(Set<Cell2D> cells) {
        given(table.contains(anyInt(), anyInt())).willReturn(Boolean.TRUE);
        Boolean result = sut.empty(cells);

        assertThat(result).isFalse();
        verify(table, times(2)).contains(anyInt(), anyInt());
    }

    @Test(dataProvider = "dummyCells")
    public void givenUnoccupiedCellsEmptyShouldReturnFalse(Set<Cell2D> cells) {
        given(table.contains(anyInt(), anyInt())).willReturn(Boolean.FALSE);

        Boolean result = sut.empty(cells);

        assertThat(result).isTrue();
        verify(table, times(2)).contains(anyInt(), anyInt());
    }
}
