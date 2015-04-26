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

import java.util.function.Function;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.johnstok.repository.Repository;


/**
 * TODO: Describe this classes responsibility.
 *
 * TODO: Add a description for this type.
 *
 * @author Keith Webster Johnston.
 */
public class HashSetRepositoryTest {

    private final Repository<String, String> _repo =
        new HashSetRepository<>(Function.identity());

    /**
     * TODO: Add a description for this method.
     *
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {}


    /**
     * TODO: Add a description for this method.
     *
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {}


    @Test
    public void test() {

        _repo.add("foo");

        // ASSERT
        Assert.assertTrue(_repo.contains("foo"));
    }

}
