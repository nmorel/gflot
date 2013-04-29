/*
 * Copyright (c) 2012 Nicolas Morel
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package com.googlecode.gflot.client.options.errorbars;

import com.google.gwt.canvas.dom.client.Context2d;

/**
 * @author Nicolas Morel
 */
public abstract class DrawCap
{
    private static class DrawArrow extends DrawCap
    {
        @Override
        public void draw( Context2d ctx, double x, double y, double radius )
        {
            ctx.beginPath();
            ctx.moveTo( x + radius, y + radius );
            ctx.lineTo( x, y );
            ctx.lineTo( x - radius, y + radius );
            ctx.stroke();
        }
    }

    private static class DrawSemiCircle extends DrawCap
    {
        @Override
        public void draw( Context2d ctx, double x, double y, double radius )
        {
            ctx.beginPath();
            ctx.arc( x, y, radius, 0, Math.PI, false );
            ctx.moveTo( x - radius, y );
            ctx.lineTo( x + radius, y );
            ctx.stroke();
        }
    }

    private static DrawArrow drawArrowInstance;
    private static DrawSemiCircle drawSemiCircleInstance;

    /**
     * @return an instance of {@link DrawCap} that draws an arrow
     */
    public static DrawCap drawArrow()
    {
        if ( null == drawArrowInstance )
        {
            drawArrowInstance = new DrawArrow();
        }
        return drawArrowInstance;
    }

    /**
     * @return an instance of {@link DrawCap} that draws a semi circle
     */
    public static DrawCap drawSemiCircle()
    {
        if ( null == drawSemiCircleInstance )
        {
            drawSemiCircleInstance = new DrawSemiCircle();
        }
        return drawSemiCircleInstance;
    }

    public abstract void draw( Context2d ctx, double x, double y, double radius );
}
