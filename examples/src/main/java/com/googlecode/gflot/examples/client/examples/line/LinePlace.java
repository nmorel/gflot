package com.googlecode.gflot.examples.client.examples.line;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class LinePlace
    extends PlaceWithSources<LinePlace>
{
    private static final String SOURCE_FILENAME;

    static final String UI_RAW_SOURCE_FILENAME = "LineExample.ui.xml";

    private static final String[] RAW_SOURCE_FILENAMES = new String[] { UI_RAW_SOURCE_FILENAME };

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( LineExample.class.getName() );
    }

    @Override
    public String getSourceFilename()
    {
        return SOURCE_FILENAME;
    }

    @Override
    public String[] getRawSourceFilenames()
    {
        return RAW_SOURCE_FILENAMES;
    }

    /**
     * Default constructor. It will show the example
     */
    public LinePlace()
    {
        super();
    }

    public LinePlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public LinePlace createPlace()
    {
        return new LinePlace();
    }

}
