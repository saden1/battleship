/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship;

import com.google.common.collect.ImmutableSet;
import com.saden.battleship.impl.core.Cell;
import com.saden.battleship.impl.core.Vehicle;
import com.saden.battleship.impl.enums.FireStatus;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.testng.annotations.*;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author saden
 */
public class BattleshipITest {

    BattleshipFactory sut;

    @BeforeClass
    public void initClass() {
        ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        sut = locator.getService(BattleshipFactory.class);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void givenNullFireShouldThrowException() {
        Battleship battleship = sut.create2DGame(10, 10);
        battleship.fire(null);
    }

    @Test
    public void givenCellWithNoShipFireShouldMiss() {
        Battleship battleship = sut.create2DGame(10, 10);
        BattleshipItemFactory itemFactrory = battleship.getItemFactory();
        Cell cell = itemFactrory.createCell(1, 1);
        FireStatus result = battleship.fire(cell);

        assertThat(result).isEqualTo(FireStatus.MISS);
    }

    @Test
    public void givenOneCellShipFireShouldSink() {
        Battleship battleship = sut.create2DGame(10, 10);
        BattleshipItemFactory itemFactrory = battleship.getItemFactory();
        Cell cell = itemFactrory.createCell(1, 1);
        Vehicle vehicle = itemFactrory.createVehicle("ship", ImmutableSet.of(cell));
        battleship.addVehicle(vehicle, ImmutableSet.of(cell));

        FireStatus result = battleship.fire(cell);

        assertThat(result).isEqualTo(FireStatus.SUNK);
    }

    @Test
    public void givenMultiCellShipFireShouldHit() {
        Battleship battleship = sut.create2DGame(10, 10);
        BattleshipItemFactory itemFactrory = battleship.getItemFactory();
        Cell cellA = itemFactrory.createCell(1, 1);
        Cell cellB = itemFactrory.createCell(1, 2);

        ImmutableSet<Cell> vehicleCells = ImmutableSet.of(cellA, cellB);

        Vehicle vehicle = itemFactrory.createVehicle("ship", vehicleCells);
        battleship.addVehicle(vehicle, vehicleCells);

        FireStatus result = battleship.fire(cellA);

        assertThat(result).isEqualTo(FireStatus.HIT);
    }
}
