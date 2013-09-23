/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl._2d.impl;

import com.google.common.collect.Table;
import com.saden.battleship.BattleshipItemFactory;
import com.saden.battleship.annotation.Battleship2D;
import com.saden.battleship.impl._2d.Battleship2DObjectFactory;
import com.saden.battleship.impl.core.Board;
import com.saden.battleship.impl.core.CoreObjectFactory;
import com.saden.battleship.impl.core.Vehicle;
import java.util.Set;
import javax.inject.Inject;
import org.jvnet.hk2.annotations.Service;

@Battleship2D
@Service
public class Battleship2DObjectFactoryImpl implements Battleship2DObjectFactory {

    private final BattleshipItemFactory itemFactory;
    private final CoreObjectFactory objectFactory;

    @Inject
    public Battleship2DObjectFactoryImpl(@Battleship2D BattleshipItemFactory battleshipItemFactory,
            CoreObjectFactory objectFactory) {
        this.itemFactory = battleshipItemFactory;
        this.objectFactory = objectFactory;
    }

    @Override
    public Board createBoard(Integer x, Integer y) {
        Table<Integer, Integer, Vehicle> grid = objectFactory.createHashTable(x, y);
        Set<Vehicle> vehicles = objectFactory.createHashSet(x * y);

        return new Board2DImpl(grid, vehicles);
    }

    @Override
    public BattleshipItemFactory getItemFactory() {
        return itemFactory;
    }
}
