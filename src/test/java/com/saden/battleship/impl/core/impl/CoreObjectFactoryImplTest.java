/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.impl.core.impl;

import com.google.common.collect.Table;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.*;

/**
 *
 * @author saden
 */
public class CoreObjectFactoryImplTest {

    CoreObjectFactoryImpl sut;

    @BeforeMethod
    public void initMocks() {
        sut = new CoreObjectFactoryImpl();
    }

    @DataProvider
    Object[][] invalidCapacity() {
        return new Object[][]{
            { null },
            { -1 }
        };
    }

    @DataProvider
    Object[][] validCapacity() {
        return new Object[][]{
            { 1 }
        };
    }

    @Test(dataProvider = "invalidCapacity",
            expectedExceptions = { NullPointerException.class, IllegalArgumentException.class })
    public void givenInvalidCapacityCreateHashMapShouldThrowException(Integer initialCapacity) {
        sut.createHashMap(initialCapacity);
    }

    @Test(dataProvider = "validCapacity")
    public void givenValidCapacityCreateHashMapShouldReturnMap(Integer initialCapacity) {
        Map<Object, Object> result = sut.createHashMap(initialCapacity);

        assertThat(result).isNotNull();
    }

    @Test(dataProvider = "invalidCapacity",
            expectedExceptions = { NullPointerException.class, IllegalArgumentException.class })
    public void givenInvalidCapacityCreateHashSetShouldThrowException(Integer initialCapacity) {
        sut.createHashSet(initialCapacity);
    }

    @Test(dataProvider = "validCapacity")
    public void givenValidCapacityCreateHashSetShouldReturnMap(Integer initialCapacity) {
        Set<Object> result = sut.createHashSet(initialCapacity);

        assertThat(result).isNotNull();
    }

    @DataProvider
    Object[][] invalidDimensions() {
        return new Object[][]{
            { null, 1 },
            { 1, null },
            { -1, 1 },
            { 1, -1 }
        };
    }

    @Test(dataProvider = "invalidDimensions",
            expectedExceptions = { NullPointerException.class, IllegalArgumentException.class })
    public void givenInvalidDimensionsCreateHashTableShouldThrowException(Integer x, Integer y) {
        sut.createHashTable(x, y);
    }

    @Test
    public void givenValidDimensionsCreateHashTableShouldReturnHashTable() {
        Integer x = 1;
        Integer y = 1;

        Table<Object, Object, Object> result = sut.createHashTable(x, y);

        assertThat(result).isNotNull();
    }
}
