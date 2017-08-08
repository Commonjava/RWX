package org.commonjava.rwx.test.koji;

import org.commonjava.rwx.anno.DataKey;
import org.commonjava.rwx.anno.StructPart;

/**
 * Created by ruhan on 7/19/17.
 */
@StructPart
public class KojiNVR
{
    @DataKey( "packageID" )
    private String packageId;

    @DataKey( "userID" )
    private String userId;

    @DataKey( "tagID" )
    private String tagId;

    @DataKey( "name" )
    private String name;

    @DataKey( "version" )
    private String version;

    @DataKey( "release" )
    private String release;

    public KojiNVR() {}

    public KojiNVR( String name, String version, String release )
    {
        this.name = name;
        this.version = version;
        this.release = release;
    }

    public String getPackageId()
    {
        return packageId;
    }

    public String getUserId()
    {
        return userId;
    }

    public String getTagId()
    {
        return tagId;
    }

    public String getName()
    {
        return name;
    }

    public String getVersion()
    {
        return version;
    }

    public String getRelease()
    {
        return release;
    }

    public void setPackageId( String packageId )
    {
        this.packageId = packageId;
    }

    public void setUserId( String userId )
    {
        this.userId = userId;
    }

    public void setTagId( String tagId )
    {
        this.tagId = tagId;
    }

    public void setRelease( String release )
    {
        this.release = release;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public void setVersion( String version )
    {
        this.version = version;
    }

    public String renderString()
    {
        return String.format( "%s-%s-%s", getName(), getVersion().replace( '-', '_' ), getRelease() );
    }
}
