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
    private static final String PAGE_X = "pageX";
    private static final String PAGE_Y = "pageY";

    protected PlotPosition( JSONObject obj )
    {
        super( obj );
    }

    /**
     * @return the x axis coordinate for the first x axis
     */
    public Double getX()
    {
        return getX( 1 );
    }

    /**
     * @param xAxisNumber number of the axis, starting at 1
     * @return the x axis coordinate for the x axis number xAxisNumber
     */
    public Double getX( int xAxisNumber )
    {
        assert xAxisNumber > 0 : "xAxisNumber starts at 1";
        return getDouble( X + xAxisNumber );
    }

    /**
     * @return the y axis coordinate for the first y axis
     */
    public Double getY()
    {
        return getY( 1 );
    }

    /**
     * @param yAxisNumber number of the axis, starting at 1
     * @return they axis coordinate for the y axis number yAxisNumber
     */
    public Double getY( int yAxisNumber )
    {
        assert yAxisNumber > 0 : "yAxisNumber starts at 1";
        return getDouble( Y + yAxisNumber );
    }

    /**
     * @return the global screen x coordinates
     */
    public Integer getPageX()
    {
        return getInteger( PAGE_X );
    }

    /**
     * @return the global screen y coordinates
     */
    public Integer getPageY()
    {
        return getInteger( PAGE_Y );
    }

}
