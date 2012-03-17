package com.googlecode.gflot.examples.client.examples.multipleaxes;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class MultipleAxesPlace
    extends PlaceWithSources<MultipleAxesPlace>
{
    private static final String SOURCE_FILENAME;

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( MultipleAxesExample.class.getName() );
    }

    @Override
    public String getSourceFilename()
    {
        return SOURCE_FILENAME;
    }

    /**
     * Default constructor. It will show the example
     */
    public MultipleAxesPlace()
    {
        super();
    }

    public MultipleAxesPlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public MultipleAxesPlace createPlace()
    {
        return new MultipleAxesPlace();
    }

}