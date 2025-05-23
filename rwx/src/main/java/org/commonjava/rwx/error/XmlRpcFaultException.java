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
package org.commonjava.rwx.error;

import org.commonjava.rwx.model.Fault;

/**
 * Created by ruhan on 7/20/17.
 */
public class XmlRpcFaultException
                extends XmlRpcException
{

    private Fault fault;

    public XmlRpcFaultException( final Fault fault )
    {
        super( fault.getValue().toString() );
        this.fault = fault;
    }

    public Fault getFault()
    {
        return fault;
    }
}
