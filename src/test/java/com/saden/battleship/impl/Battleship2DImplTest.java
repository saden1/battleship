/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl;

import com.saden.battleship.impl.Battleship2DImpl;
import com.google.common.base.Optional;
import com.saden.battleship.BattleshipItemFactory;
import com.saden.battleship.impl._2d.Cell2D;
import com.saden.battleship.impl.core.Board;
import com.saden.battleship.impl.core.Vehicle;
import com.saden.battleship.impl.enums.FireStatus;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import org.testng.annotations.*;

/**
 *
 * @author saden
 */
public class Battleship2DImplTest {

    Battleship2DImpl sut;
    Board board;
    BattleshipItemFactory itemFactory;

    @BeforeMethod
    public void initMocks() {
        itemFactory = mock(BattleshipItemFactory.class);
        board = mock(Board.class);
        sut = new Battleship2DImpl(itemFactory, board);
    }

    private Cell2D createMockCell2D(Integer x, Integer y) {
        Cell2D cell = mock(Cell2D.class);
        given(cell.getX()).willReturn(x);
        given(cell.getY()).willReturn(y);

        return cell;
    }

    @DataProvider
    Object[][] invalidCellParams() {
        return new Object[][]{
            { null },
            { createMockCell2D(null, 1) },
            { createMockCell2D(1, null) }
        };
    }

    @DataProvider
    Object[][] dummyCellParams() {
        return new Object[][]{
            { createMockCell2D(1, 1) }
        };
    }

    @Test(dataProvider = "invalidCellParams",
            expectedExceptions = NullPointerException.class)
    public void givenInvalidCellParamsFireShouldThrowExcedption(Cell2D cell) {
        sut.fire(cell);
    }

    @Test(dataProvider = "dummyCellParams")
    public void givenCellWithoutShipFireShouldReturnMissFireStatus(Cell2D cell) {
        Optional<Vehicle> optionalVehicle = mock(Optional.class);

        given(board.getVehichel(cell)).willReturn(optionalVehicle);
        given(optionalVehicle.isPresent()).willReturn(Boolean.FALSE);

        FireStatus result = sut.fire(cell);

        assertThat(result).isEqualTo(FireStatus.MISS);
        verify(cell).getX();
        verify(cell).getY();
        verify(board).getVehichel(cell);
        verify(optionalVehicle).isPresent();
    }

    @DataProvider
    Object[][] invalidShipState() {
        return new Object[][]{
            { createMockCell2D(1, 1), null }
        };
    }

    @Test(dataProvider = "invalidShipState",
            expectedExceptions = { NullPointerException.class, IllegalStateException.class })
    public void givenCellWithShipThatHasInvalidStateFireShouldThrowException(Cell2D cell,
            Integer life) {
        Optional<Vehicle> optionalVehicle = mock(Optional.class);
        Vehicle vehicle = mock(Vehicle.class);

        given(board.getVehichel(cell)).willReturn(optionalVehicle);
        given(optionalVehicle.isPresent()).willReturn(Boolean.TRUE);
        given(optionalVehicle.get()).willReturn(vehicle);
        given(vehicle.getLife()).willReturn(life);

        sut.fire(cell);
    }

    @Test(dataProvider = "dummyCellParams")
    public void givenCellWithDamagedShipFireShouldReturnHitFireStatus(Cell2D cell) {
        Optional<Vehicle> optionalVehicle = mock(Optional.class);
        Vehicle vehicle = mock(Vehicle.class);

        given(board.getVehichel(cell)).willReturn(optionalVehicle);
        given(optionalVehicle.isPresent()).willReturn(Boolean.TRUE);
        given(optionalVehicle.get()).willReturn(vehicle);
        given(vehicle.getLife()).willReturn(1);
        given(vehicle.getMaxLife()).willReturn(2);

        FireStatus result = sut.fire(cell);

        assertThat(result).isEqualTo(FireStatus.HIT);
        verify(cell).getX();
        verify(cell).getY();
        verify(board).getVehichel(cell);
        verify(optionalVehicle).isPresent();
        verify(optionalVehicle).get();
        verify(vehicle).fire(cell);
        verify(vehicle).getLife();
        verify(vehicle).getMaxLife();
    }

    @Test(dataProvider = "dummyCellParams")
    public void givenCellWithDestoryedShipFireShouldReturnSunkFireStatus(Cell2D cell) {
        Optional<Vehicle> optionalVehicle = mock(Optional.class);
        Vehicle vehicle = mock(Vehicle.class);

        given(board.getVehichel(cell)).willReturn(optionalVehicle);
        given(optionalVehicle.isPresent()).willReturn(Boolean.TRUE);
        given(optionalVehicle.get()).willReturn(vehicle);
        given(vehicle.getLife()).willReturn(0);

        FireStatus result = sut.fire(cell);

        assertThat(result).isEqualTo(FireStatus.SUNK);
        verify(cell).getX();
        verify(cell).getY();
        verify(board).getVehichel(cell);
        verify(optionalVehicle).isPresent();
        verify(optionalVehicle).get();
        verify(vehicle).getLife();
    }

    @DataProvider
    Object[][] invalidAddVehicleParams() {
        return new Object[][]{
            { null, mock(Set.class) },
            { mock(Vehicle.class), null },
            { mock(Vehicle.class), mock(Set.class) }
        };
    }

    @Test(dataProvider = "invalidAddVehicleParams",
            expectedExceptions = { NullPointerException.class, IllegalArgumentException.class })
    public void givenInvalidParamsAddVehicleShouldThrowException(Vehicle vehicle, Set<Cell2D> cells) {
        sut.addVehicle(vehicle, cells);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void givenOccupiedCellsAddVehicleShouldThrowException() {
        Vehicle vehicle = mock(Vehicle.class);
        Set<Cell2D> cells = mock(Set.class);

        given(board.empty(cells)).willReturn(Boolean.FALSE);

        sut.addVehicle(vehicle, cells);
    }

    @Test
    public void givenUnoccupiedCellsAddVehicleShouldAddVehicle() {
        Integer x = 1;
        Integer y = 1;
        Vehicle vehicle = mock(Vehicle.class);
        Cell2D cell = createMockCell2D(x, y);
        Set<Cell2D> cells = spy(new HashSet<Cell2D>());
        cells.add(cell);

        given(board.empty(cells)).willReturn(Boolean.TRUE);

        sut.addVehicle(vehicle, cells);

        verify(cells).iterator();
        verify(board).addVehicle(vehicle, cell);
    }

    @Test
    public void givenValidStateGetItemFactoryShouldReturnBattleshipItemFactory() {
        BattleshipItemFactory<Cell2D> result = sut.getItemFactory();

        assertThat(result).isNotNull();
    }
}
