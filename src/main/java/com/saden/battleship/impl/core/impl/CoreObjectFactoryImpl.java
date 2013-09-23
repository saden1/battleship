/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl.core.impl;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.saden.battleship.impl.core.CoreObjectFactory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jvnet.hk2.annotations.Service;

@Service
public class CoreObjectFactoryImpl implements CoreObjectFactory {

    @Override
    public <T> Set<T> createHashSet(Integer initialCapacity) {
        return new HashSet<>(initialCapacity);
    }

    @Override
    public <T, S> Map<T, S> createHashMap(Integer initialCapacity) {
        return new HashMap<>(initialCapacity);
    }

    @Override
    public <T, S, C> Table<T, S, C> createHashTable(Integer x, Integer y) {
        return HashBasedTable.create(x, y);
    }
}
