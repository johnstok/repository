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
package com.johnstok.repository.memory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;
import com.johnstok.repository.Repository;

public class HashSetRepository<T, U> implements Repository<T, U> {

	private final HashMap<U,T> _set;
	private final Function<T, U> _idAccessor;


	public HashSetRepository(final HashMap<U,T> set, final Function<T, U> idAccessor) {
		_set = set;
		_idAccessor = idAccessor;
	}


	public HashSetRepository(final Function<T, U> idAccessor) {
		this(new HashMap<>(), idAccessor);
	}


	@Override
	public Iterator<T> iterator() {
		return _set.values().iterator();
	}


	@Override
	public void add(final T t) {
		Objects.requireNonNull(t);
		final T existingValue = _set.putIfAbsent(_idAccessor.apply(t), t);
		if (null!=existingValue) {
			throw new RuntimeException("The repository already includes a value for this ID - use 'replace()' instead.");
		}
	}


	@Override
	public void remove(final T t) {
		Objects.requireNonNull(t);
		_set.remove(_idAccessor.apply(t)); // TODO: should we check the return value?
	}


	@Override
	public boolean contains(final T t) {
		Objects.requireNonNull(t);
		return _set.containsKey(_idAccessor.apply(t));
	}


	@Override
	public int size() {
		return _set.size();
	}


	@Override
	public boolean isEmpty() {
		return _set.isEmpty();
	}


	@Override
	public T get(final U id) {
		Objects.requireNonNull(id);
		return _set.get(id);
	}


	@Override
	public void replace(final T t) {
		Objects.requireNonNull(t);
		final T existingValue = _set.replace(_idAccessor.apply(t), t);
		if (null==existingValue) {
			throw new RuntimeException("The repository does not already include a value for this ID - use 'add()' instead.");
		}
	}
}
