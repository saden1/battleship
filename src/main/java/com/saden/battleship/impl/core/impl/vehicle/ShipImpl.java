/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl.core.impl.vehicle;

import com.saden.battleship.impl.core.Cell;
import com.saden.battleship.impl.core.Vehicle;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author saden
 */
public class ShipImpl implements Vehicle {

    private final String name;
    private final Map<Cell, Boolean> damageState;
    private Integer life;
    private Integer maxLife;

    public ShipImpl(String name,
            Integer maxLife,
            Map<Cell, Boolean> damageState) {
        this.name = name;
        this.life = maxLife;
        this.maxLife = maxLife;
        this.damageState = damageState;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void fire(Cell cell) {
        Boolean damaged = damageState.get(cell);

        if (damaged == null || damaged) {
            return;
        }

        damageState.put(cell, Boolean.TRUE);
        life -= 1;
    }

    @Override
    public Integer getLife() {
        return life;
    }

    @Override
    public Integer getHealth() {
        return Math.round((life / maxLife) * 100);
    }

    @Override
    public Integer getMaxLife() {
        return maxLife;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        Vehicle vehicle = (Vehicle) obj;
        return new EqualsBuilder()
                .append(name, vehicle.getName())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(137, 223).
                append(name).
                toHashCode();
    }

    @Override
    public String toString() {
        return "ShipImpl [" + "name: " + name + ", life: " + life + ", maxLife: " + maxLife + ']';
    }
}
