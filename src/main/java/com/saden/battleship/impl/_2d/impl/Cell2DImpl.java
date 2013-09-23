/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl._2d.impl;

import com.saden.battleship.impl._2d.Cell2D;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author saden
 */
class Cell2DImpl implements Cell2D {

    private final Integer x;
    private final Integer y;

    public Cell2DImpl(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Integer getX() {
        return x;
    }

    @Override
    public Integer getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Cell2D (" + "x: " + x + ", y: " + y + ')';
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

        Cell2D cell = (Cell2D) obj;
        return new EqualsBuilder()
                .append(x, cell.getX())
                .append(y, cell.getY())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(19, 79).
                append(x).
                append(y).
                toHashCode();
    }
}
