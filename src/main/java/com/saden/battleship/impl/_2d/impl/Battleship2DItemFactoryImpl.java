/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl._2d.impl;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;
import com.saden.battleship.BattleshipItemFactory;
import com.saden.battleship.annotation.Battleship2D;
import com.saden.battleship.impl._2d.Cell2D;
import com.saden.battleship.impl.core.Cell;
import com.saden.battleship.impl.core.CoreObjectFactory;
import com.saden.battleship.impl.core.Vehicle;
import com.saden.battleship.impl.core.impl.vehicle.ShipImpl;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import org.jvnet.hk2.annotations.Service;

@Battleship2D
@Service
public class Battleship2DItemFactoryImpl implements BattleshipItemFactory<Cell2D> {

    private final CoreObjectFactory objectFactory;

    @Inject
    public Battleship2DItemFactoryImpl(CoreObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    @Override
    public Cell2D createCell(Integer... dimensions) {
        return new Cell2DImpl(dimensions[0], dimensions[1]);
    }

    @Override
    public Vehicle createVehicle(String name, Set<Cell2D> cells) {
        checkArgument(!isNullOrEmpty(name), "name parameter is null or empty");
        checkNotNull(cells, "cells paramter is null");
        checkArgument(!cells.isEmpty(), "cells paramter is empty");

        Integer maxLife = cells.size();
        Map<Cell, Boolean> damageState = objectFactory.createHashMap(maxLife);

        for (Cell2D cell : cells) {
            damageState.put(cell, Boolean.FALSE);
        }

        return new ShipImpl(name, maxLife, damageState);
    }
}
