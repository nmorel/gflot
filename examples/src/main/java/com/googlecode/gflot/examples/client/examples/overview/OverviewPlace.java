package com.googlecode.gflot.examples.client.examples.overview;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class OverviewPlace
    extends PlaceWithSources<OverviewPlace>
{
    private static final String SOURCE_FILENAME;

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( OverviewExample.class.getName() );
    }

    @Override
    public String getSourceFilename()
    {
        return SOURCE_FILENAME;
    }

    /**
     * Default constructor. It will show the example
     */
    public OverviewPlace()
    {
        super();
    }

    public OverviewPlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public OverviewPlace createPlace()
    {
        return new OverviewPlace();
    }

}