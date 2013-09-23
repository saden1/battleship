/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl;

import com.google.common.base.Optional;
import static com.google.common.base.Preconditions.*;
import com.saden.battleship.Battleship;
import com.saden.battleship.BattleshipItemFactory;
import com.saden.battleship.impl._2d.Cell2D;
import com.saden.battleship.impl.core.Board;
import com.saden.battleship.impl.core.Vehicle;
import com.saden.battleship.impl.enums.FireStatus;
import java.util.Set;

/**
 *
 * @author saden
 */
class Battleship2DImpl implements Battleship<Cell2D> {

    private final BattleshipItemFactory battleshipItemFactory;
    private final Board<Cell2D> board;

    public Battleship2DImpl(BattleshipItemFactory battleshipItemFactory,
            Board<Cell2D> board) {
        this.battleshipItemFactory = battleshipItemFactory;
        this.board = board;
    }

    @Override
    public FireStatus fire(Cell2D cell) {
        //fail fast by performing null and state checks
        checkNotNull(cell, "cell parameter is null");
        checkNotNull(cell.getX(), "cell x coordinate is null");
        checkNotNull(cell.getY(), "cell y coordinate is null");

        Optional<Vehicle> optionalVehicle = board.getVehichel(cell);

        if (optionalVehicle.isPresent()) {
            Vehicle vehicle = optionalVehicle.get();
            vehicle.fire(cell);

            Integer life = vehicle.getLife();

            if (life.equals(0)) {
                return FireStatus.SUNK;
            } else if (life < vehicle.getMaxLife()) {
                return FireStatus.HIT;
            }
        }

        //no ship? you've missed it!
        return FireStatus.MISS;
    }

    @Override
    public void addVehicle(Vehicle vehicle, Set<Cell2D> cells) {
        checkNotNull(vehicle, "vehicle parameter is null");
        checkNotNull(cells, "cells parameter is null");
        checkArgument(!cells.isEmpty(), "cells parameter is empty");

        //make sure none of the cells contain a vehicle
        checkArgument(board.empty(cells), "another vehicle already occupies the given cell locations");

        //lets add the vehicle to the board
        for (Cell2D cell : cells) {
            board.addVehicle(vehicle, cell);
        }
    }

    @Override
    public BattleshipItemFactory<Cell2D> getItemFactory() {
        return battleshipItemFactory;
    }
}
