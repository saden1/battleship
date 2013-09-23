/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl._2d;

import com.saden.battleship.BattleshipItemFactory;
import com.saden.battleship.impl.core.Board;
import org.jvnet.hk2.annotations.Contract;

/**
 *
 * @author saden
 */
@Contract
public interface Battleship2DObjectFactory {

    Board createBoard(Integer x, Integer y);

    public BattleshipItemFactory getItemFactory();
}
