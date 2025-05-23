/**
 * Copyright (C) 2010 Red Hat, Inc. (http://github.com/Commonjava/commonjava)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.commonjava.rwx.util;

import org.commonjava.rwx.error.CoercionException;

public abstract class ValueCoercion
{
    private final String description;

    protected ValueCoercion( String description )
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public abstract Object fromString( String value ) throws CoercionException;

    public String toString( final Object value ) throws CoercionException
    {
        return value == null ? null : String.valueOf( value );
    }

    public String toString()
    {
        return getDescription();
    }

    public Object upgradeCast( Object value )
    {
        return value;
    }
}
