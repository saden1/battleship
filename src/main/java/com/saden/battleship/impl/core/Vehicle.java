/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl.core;

/**
 *
 * @author saden
 */
public interface Vehicle {

    String getName();

    void fire(Cell cell);

    Integer getLife();

    Integer getMaxLife();

    Integer getHealth();
}
