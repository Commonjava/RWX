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
package org.commonjava.rwx.model;

/**
 * Each request contains a single XML document, whose root element is a methodCall element.
 * Each methodCall element contains a methodName element and a params element. The methodName element identifies
 * the name of the procedure to be called, while the params element contains a list of parameters and their values.
 * <p>
 * Created by ruhan on 7/13/17.
 */
public final class MethodCall
                extends RpcObject
{
    private String methodName;

    public String getMethodName()
    {
        return methodName;
    }

    public void setMethodName( String methodName )
    {
        this.methodName = methodName;
    }
}
