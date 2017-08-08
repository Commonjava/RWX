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
