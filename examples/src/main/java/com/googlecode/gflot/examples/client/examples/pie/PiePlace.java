package com.googlecode.gflot.examples.client.examples.pie;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class PiePlace
    extends PlaceWithSources<PiePlace>
{
    private static final String SOURCE_FILENAME;

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( PieExample.class.getName() );
    }

    @Override
    public String getSourceFilename()
    {
        return SOURCE_FILENAME;
    }

    /**
     * Default constructor. It will show the example
     */
    public PiePlace()
    {
        super();
    }

    public PiePlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public PiePlace createPlace()
    {
        return new PiePlace();
    }

}
