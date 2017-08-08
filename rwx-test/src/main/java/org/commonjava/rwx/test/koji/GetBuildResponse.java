package org.commonjava.rwx.test.koji;

import org.commonjava.rwx.anno.DataIndex;
import org.commonjava.rwx.anno.Response;

/**
 * Created by ruhan on 7/19/17.
 */
@Response
public class GetBuildResponse
{
    @DataIndex(0)
    private KojiBuildInfo buildInfo;

    public KojiBuildInfo getBuildInfo()
    {
        return buildInfo;
    }

    public void setBuildInfo( KojiBuildInfo buildInfo )
    {
        this.buildInfo = buildInfo;
    }

}
