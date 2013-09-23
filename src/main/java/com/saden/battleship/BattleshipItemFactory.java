/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship;

import com.saden.battleship.impl.core.Cell;
import com.saden.battleship.impl.core.Vehicle;
import java.util.Set;
import org.jvnet.hk2.annotations.Contract;

/**
 *
 * @author saden
 */
@Contract
public interface BattleshipItemFactory<T extends Cell> {

    T createCell(Integer... dimensions);

    Vehicle createVehicle(String name, Set<T> cells);
}
