package com.googlecode.gflot.examples.client.examples.export;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class ExportPlace
    extends PlaceWithSources<ExportPlace>
{
    private static final String SOURCE_FILENAME;

    static final String UI_RAW_SOURCE_FILENAME = "ExportExample.ui.xml";

    private static final String[] RAW_SOURCE_FILENAMES = new String[] { UI_RAW_SOURCE_FILENAME };

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( ExportExample.class.getName() );
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
    public ExportPlace()
    {
        super();
    }

    public ExportPlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public ExportPlace createPlace()
    {
        return new ExportPlace();
    }

}
