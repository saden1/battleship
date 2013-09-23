/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl.core.impl.vehicle;

import com.saden.battleship.impl.core.Cell;
import com.saden.battleship.impl._2d.Cell2D;
import com.saden.battleship.impl.core.Vehicle;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import org.testng.annotations.*;

/**
 *
 * @author saden
 */
public class ShipImplTest {

    ShipImpl sut;
    String name;
    Integer maxLife;
    Map<Cell, Boolean> damageState;

    @BeforeMethod
    public void initMocks() {
        Integer size = 4;
        name = "test";
        maxLife = new Integer(size);
        damageState = mock(Map.class);

        sut = new ShipImpl(name, maxLife, damageState);

        given(damageState.size()).willReturn(size);
    }

    @Test
    public void givenNullNameGetNameShouldReturnNull() {
        sut = new ShipImpl(null, null, null);

        String resutl = sut.getName();

        assertThat(resutl).isNull();
    }

    @Test
    public void givenValidNameGetNameShouldReturnName() {
        String resutl = sut.getName();

        assertThat(resutl)
                .isNotNull()
                .isEqualTo(name);
    }

    @Test
    public void givenNullCellFireShouldDoNothing() {
        sut.fire(null);

        verify(damageState).get(null);
        verify(damageState, times(0)).put(any(Cell2D.class), anyBoolean());
        assertThat(sut.getLife()).isEqualTo(maxLife);
    }

    @Test
    public void givenAlreadyDamagedCellFireShouldDoNothing() {
        Cell2D cell = mock(Cell2D.class);
        given(damageState.get(cell)).willReturn(Boolean.TRUE);

        sut.fire(cell);

        verify(damageState).get(cell);
        verify(damageState, times(0)).put(any(Cell2D.class), anyBoolean());
        assertThat(sut.getLife()).isEqualTo(maxLife);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void givenNullMaxLifeStateGetHealthShouldThrowException() {
        sut = new ShipImpl(null, null, null);

        sut.getHealth();
    }

    @Test
    public void givenUndagmedShipGetHealthShouldReturn100Percent() {
        Integer result = sut.getHealth();

        assertThat(result).isEqualTo(100);
    }

    @Test
    public void givenInvalidMaxLifeStateGetMaxLifeShouldReturnNull() {
        sut = new ShipImpl(null, null, null);

        Integer result = sut.getMaxLife();

        assertThat(result).isNull();
    }

    @Test
    public void givenValidMaxLifeStateGetMaxLifeShouldReturnMaxLife() {
        Integer result = sut.getMaxLife();

        assertThat(result).isEqualTo(maxLife);
    }

    @Test
    public void givenUndamagedCellFireShouldDamageShip() {
        Cell2D cell = mock(Cell2D.class);
        given(damageState.get(cell)).willReturn(Boolean.FALSE);

        sut.fire(cell);

        verify(damageState).get(cell);
        verify(damageState).put(cell, Boolean.TRUE);
        assertThat(sut.getLife()).isEqualTo(maxLife - 1);
    }

    @DataProvider
    Object[][] invalidShip() {
        return new Object[][]{
            { null },
            { "" }
        };
    }

    @Test(dataProvider = "invalidShip")
    public void givenInvalidShipNameEqualsShouldReturnFalse(String name) {
        Vehicle shipA = new ShipImpl(name, null, null);
        Vehicle shipB = new ShipImpl("test", null, null);


        Boolean result = shipA.equals(shipB);

        assertThat(result).isFalse();
    }

    @Test
    public void givenTwoUnequalShipsEqualsShouldReturnFalse() {
        Vehicle shipA = new ShipImpl("shipA", null, null);
        Vehicle shipB = new ShipImpl("shipB", null, null);

        assertThat(shipA).isNotEqualTo(shipB);
    }

    @Test
    public void givenTwoEqualShipsEqualsShouldReturnTrue() {
        Vehicle shipA = new ShipImpl("ship", null, null);
        Vehicle shipB = new ShipImpl("ship", null, null);

        assertThat(shipA).isEqualTo(shipB);
    }

    @Test
    public void givenSameShipsEqualsShouldReturnTrue() {
        Vehicle ship = new ShipImpl("ship", null, null);

        assertThat(ship).isEqualTo(ship);
    }

    @Test
    public void givenNullEqualsShouldReturnFalse() {
        Vehicle shipA = new ShipImpl("ship", null, null);
        Vehicle shipB = null;
        Boolean result = shipA.equals(shipB);

        assertThat(result).isFalse();
    }

    @Test
    public void givenDifferentClassEqualsShouldReturnFalse() {
        Vehicle shipA = new ShipImpl("ship", null, null);
        Integer shipB = 1;

        Boolean result = shipA.equals(shipB);

        assertThat(result).isFalse();
    }

    @Test(dataProvider = "invalidShip")
    public void givenInvalidCellStateHashCodeShouldBeDiffrent(String name) {
        Vehicle shipA = new ShipImpl(name, null, null);
        Vehicle shipB = new ShipImpl("test", null, null);

        assertThat(shipA.hashCode()).isNotEqualTo(shipB.hashCode());
    }

    @Test
    public void givenTwoUnequalShipsHashCodeShouldBeDiffrent() {
        Vehicle shipA = new ShipImpl("shipA", null, null);
        Vehicle shipB = new ShipImpl("shipB", null, null);

        assertThat(shipA.hashCode()).isNotEqualTo(shipB.hashCode());
    }

    @Test
    public void givenTwoEqualShipsHashCodeShouldBeTheSame() {
        Vehicle shipA = new ShipImpl("ship", null, null);
        Vehicle shipB = new ShipImpl("ship", null, null);

        assertThat(shipA.hashCode()).isEqualTo(shipB.hashCode());
    }
}
