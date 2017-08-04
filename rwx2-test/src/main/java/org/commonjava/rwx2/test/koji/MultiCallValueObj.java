package org.commonjava.rwx2.test.koji;

import org.commonjava.rwx.binding.anno.ArrayPart;
import org.commonjava.rwx.binding.anno.DataIndex;

import java.util.List;

/**
 * Created by ruhan on 8/4/17.
 */
@ArrayPart
public class MultiCallValueObj
{
    @DataIndex( 0 )
    private Object data;

    public Object getData()
    {
        return data;
    }

    public void setData( Object data )
    {
        this.data = data;
    }
}
