/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl.core;

import com.google.common.collect.Table;
import java.util.Map;
import java.util.Set;
import org.jvnet.hk2.annotations.Contract;

/**
 *
 * @author saden
 */
@Contract
public interface CoreObjectFactory {

    <T> Set<T> createHashSet(Integer initialCapacity);

    <T, S> Map<T, S> createHashMap(Integer initialCapacity);

    <T, S, C> Table<T, S, C> createHashTable(Integer x, Integer y);
}
