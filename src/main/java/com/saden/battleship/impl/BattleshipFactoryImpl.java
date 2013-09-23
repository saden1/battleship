/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl;

import static com.google.common.base.Preconditions.checkNotNull;
import com.saden.battleship.Battleship;
import com.saden.battleship.BattleshipFactory;
import com.saden.battleship.BattleshipItemFactory;
import com.saden.battleship.annotation.Battleship2D;
import com.saden.battleship.impl._2d.Battleship2DObjectFactory;
import com.saden.battleship.impl.core.Board;
import javax.inject.Inject;
import org.jvnet.hk2.annotations.Service;

@Service
public class BattleshipFactoryImpl implements BattleshipFactory {

    private final Battleship2DObjectFactory objectFactory;

    @Inject
    public BattleshipFactoryImpl(@Battleship2D Battleship2DObjectFactory battleship2DObjectFactory) {
        this.objectFactory = battleship2DObjectFactory;
    }

    @Override
    public Battleship create2DGame(Integer x, Integer y) {
        checkNotNull(x, "x dimension is null");
        checkNotNull(y, "y dimension is null");
        Board board = objectFactory.createBoard(x, y);
        BattleshipItemFactory itemFactory = objectFactory.getItemFactory();

        return new Battleship2DImpl(itemFactory, board);
    }
}
