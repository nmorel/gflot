package com.googlecode.gflot.examples.client.examples.hover;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class HoverPlace
    extends PlaceWithSources<HoverPlace>
{
    private static final String SOURCE_FILENAME;

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( HoverExample.class.getName() );
    }

    @Override
    public String getSourceFilename()
    {
        return SOURCE_FILENAME;
    }

    /**
     * Default constructor. It will show the example
     */
    public HoverPlace()
    {
        super();
    }

    public HoverPlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public HoverPlace createPlace()
    {
        return new HoverPlace();
    }

}