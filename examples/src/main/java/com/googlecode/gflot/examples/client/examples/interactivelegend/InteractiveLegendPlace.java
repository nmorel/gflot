package com.googlecode.gflot.examples.client.examples.interactivelegend;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class InteractiveLegendPlace
    extends PlaceWithSources<InteractiveLegendPlace>
{
    private static final String SOURCE_FILENAME;

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( InteractiveLegendExample.class.getName() );
    }

    @Override
    public String getSourceFilename()
    {
        return SOURCE_FILENAME;
    }

    /**
     * Default constructor. It will show the example
     */
    public InteractiveLegendPlace()
    {
        super();
    }

    public InteractiveLegendPlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public InteractiveLegendPlace createPlace()
    {
        return new InteractiveLegendPlace();
    }

}