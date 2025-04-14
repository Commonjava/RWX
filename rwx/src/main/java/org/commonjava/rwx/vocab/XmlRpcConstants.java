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
package org.commonjava.rwx.vocab;

public class XmlRpcConstants
{
    private XmlRpcConstants()
    {
        throw new UnsupportedOperationException( "This is a utility class and cannot be instantiated" );
    }

    public static final String REQUEST = "methodCall";

    public static final String RESPONSE = "methodResponse";

    public static final String FAULT = "fault";

    public static final String METHOD_NAME = "methodName";

    public static final String PARAMS = "params";

    public static final String STRUCT = "struct";

    public static final String ARRAY = "array";

    public static final String VALUE = "value";

    public static final String MEMBER = "member";

    public static final String DATA = "data";

    public static final String NAME = "name";

    public static final String PARAM = "param";

    public static final String NIL = "nil";

    protected static final String[] DATETIME_FORMAT = { "yyyyMMdd'T'HHmmss", "yyyyMMdd'T'HH:mm:ss" };
}
