/**
 * Copyright (c) 2008 Nanometrics Inc. All Rights Reserved.
 */
package ca.nanometrics.gflot.client;

import ca.nanometrics.gflot.client.util.JSONObjectWrapper;

import com.google.gwt.json.client.JSONObject;

/**
 * A position in a plot. If the plot has dual axis x2 and/or y2 will be not null.
 *
 * @author Alexander De Leon
 */
public class PlotPosition
    extends JSONObjectWrapper
{
    private static final String X = "x";
    private static final String Y = "y";

    protected PlotPosition()
    {
        super();
    }

    protected PlotPosition( JSONObject obj )
    {
        super( obj );
    }

    public PlotPosition( Double x, Double y )
    {
        this();
        if ( x != null )
        {
            put( X, x );
        }
        if ( y != null )
        {
            put( Y, y );
        }
    }

    public Double getX()
    {
        return getDouble( X );
    }

    public Double getY()
    {
        return getDouble( Y );
    }

}
