/*
 * Copyright © 2013, Sharmarke Aden. All rights reserved.
 */
package com.saden.battleship.annotation;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Target;
import javax.inject.Qualifier;

/**
 *
 * @author saden
 */
@Qualifier
@Retention(RUNTIME)
@Target({ TYPE, METHOD, FIELD, PARAMETER })
public @interface Battleship2D {
}
