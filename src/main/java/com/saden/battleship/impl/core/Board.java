/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl.core;

import com.google.common.base.Optional;
import java.util.Set;

/**
 *
 * @author saden
 */
public interface Board<T extends Cell> {

    Optional<Vehicle> getVehichel(T cell);

    Set<Vehicle> getVehicles();

    void addVehicle(Vehicle vehicle, T cell);

    Boolean hasVehicle(Vehicle vehicle);

    Boolean empty(Set<T> cells);
}
