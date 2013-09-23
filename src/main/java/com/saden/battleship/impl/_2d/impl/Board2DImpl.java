/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl._2d.impl;

import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Table;
import com.saden.battleship.impl._2d.Cell2D;
import com.saden.battleship.impl.core.Board;
import com.saden.battleship.impl.core.Vehicle;
import java.util.Set;

/**
 *
 * @author saden
 */
class Board2DImpl implements Board<Cell2D> {

    private final Table<Integer, Integer, Vehicle> table;
    private final Set<Vehicle> vehicles;

    public Board2DImpl(Table<Integer, Integer, Vehicle> table, Set<Vehicle> vehicles) {
        this.table = table;
        this.vehicles = vehicles;
    }

    @Override
    public Set<Vehicle> getVehicles() {
        return ImmutableSet.copyOf(vehicles);
    }

    @Override
    public Optional<Vehicle> getVehichel(Cell2D cell) {
        Vehicle vehicle = table.get(cell.getX(), cell.getY());

        return Optional.fromNullable(vehicle);
    }

    @Override
    public void addVehicle(Vehicle vehicle, Cell2D cell) {
        table.put(cell.getX(), cell.getY(), vehicle);
    }

    @Override
    public Boolean hasVehicle(Vehicle vehicle) {
        return table.containsValue(vehicle);
    }

    @Override
    public Boolean empty(Set<Cell2D> cells) {
        checkNotNull(cells, "cells parameter is null");
        checkArgument(!cells.isEmpty(), "cells parameter is empty");

        for (Cell2D cell : cells) {
            if (table.contains(cell.getX(), cell.getY())) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }
}
