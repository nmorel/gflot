package com.googlecode.gflot.examples.client.examples.simple;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class SimplePlace
    extends PlaceWithSources<SimplePlace>
{
    private static final String SOURCE_FILENAME;

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( SimpleExample.class.getName() );
    }

    @Override
    public String getSourceFilename()
    {
        return SOURCE_FILENAME;
    }

    /**
     * Default constructor. It will show the example
     */
    public SimplePlace()
    {
        super();
    }

    public SimplePlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public SimplePlace createPlace()
    {
        return new SimplePlace();
    }

}
