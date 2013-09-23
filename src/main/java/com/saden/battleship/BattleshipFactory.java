/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship;

import org.jvnet.hk2.annotations.Contract;

/**
 *
 * @author saden
 */
@Contract
public interface BattleshipFactory {

    Battleship create2DGame(Integer x, Integer y);
}
