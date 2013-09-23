/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship;

import com.saden.battleship.impl.core.Cell;
import com.saden.battleship.impl.core.Vehicle;
import com.saden.battleship.impl.enums.FireStatus;
import java.util.Set;

/**
 *
 * @author saden
 */
public interface Battleship<T extends Cell> {

    FireStatus fire(T cell);

    public void addVehicle(Vehicle vehicle, Set<T> cells);

    public BattleshipItemFactory<T> getItemFactory();
}
