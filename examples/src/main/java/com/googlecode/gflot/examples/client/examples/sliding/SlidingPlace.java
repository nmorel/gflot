package com.googlecode.gflot.examples.client.examples.sliding;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class SlidingPlace
    extends PlaceWithSources<SlidingPlace>
{
    private static final String SOURCE_FILENAME;

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( SlidingExample.class.getName() );
    }

    @Override
    public String getSourceFilename()
    {
        return SOURCE_FILENAME;
    }

    /**
     * Default constructor. It will show the example
     */
    public SlidingPlace()
    {
        super();
    }

    public SlidingPlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public SlidingPlace createPlace()
    {
        return new SlidingPlace();
    }

}
