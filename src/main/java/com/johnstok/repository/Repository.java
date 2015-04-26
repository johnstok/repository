/*-----------------------------------------------------------------------------
 * Copyright © 2015 Keith Webster Johnston.
 * All rights reserved.
 *
 * This file is part of repository.
 *
 * repository is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * repository is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License
 * along with repository. If not, see <http://www.gnu.org/licenses/>.
 *---------------------------------------------------------------------------*/
package com.johnstok.repository;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import net.jcip.annotations.ThreadSafe;



/**
 * A repository represents all objects of a certain type as a conceptual set (usually emulated).
 *
 * Method naming:
 * - 'get*' — returns a single object or NULL;
 * - 'iterator* — returns an iterator on 0 or more objects;
 * - 'spliterator*' — returns a spliterator to process 0 or more objects.
 *
 * @param <T> The type of object this repository manages.
 * @param <U> The type of primary identifier for the object.
 *
 * @author Keith Webster Johnston.
 */
@ThreadSafe
public interface Repository<T, U> extends Iterable<T> {

    void add(T t);

    void replace(T t);

    void remove(T t);

    boolean contains(T t);

    T get(U id);

    default Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }

    int size();


    default boolean isEmpty() {
        return size()>0;
    }

    // TODO: bulk operations?

    // Specification based queries (see DDD p153)
    // E.g. iterator(Properties specification);
    //      spliterator(Properties specification)…

    // TODO: what is the equals & hashcode contract for a repository?
}
