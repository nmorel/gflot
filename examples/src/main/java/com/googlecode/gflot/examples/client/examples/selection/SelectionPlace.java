package com.googlecode.gflot.examples.client.examples.selection;

import com.googlecode.gflot.examples.client.source.PlaceWithSources;

/**
 * @author Nicolas Morel
 */
public class SelectionPlace
    extends PlaceWithSources<SelectionPlace>
{
    private static final String SOURCE_FILENAME;

    static final String UI_RAW_SOURCE_FILENAME = "SelectionExample.ui.xml";

    private static final String[] RAW_SOURCE_FILENAMES = new String[] { UI_RAW_SOURCE_FILENAME };

    static
    {
        SOURCE_FILENAME = extractSourceFilenameFromClassName( SelectionExample.class.getName() );
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
    public SelectionPlace()
    {
        super();
    }

    public SelectionPlace( String filename, boolean rawSource )
    {
        super( filename, rawSource );
    }

    @Override
    public SelectionPlace createPlace()
    {
        return new SelectionPlace();
    }

}
