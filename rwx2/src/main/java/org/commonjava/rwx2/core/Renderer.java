package org.commonjava.rwx2.core;

/**
 * Created by ruhan on 7/19/17.
 */
public interface Renderer<T>
{
    Object render( T value );
}
