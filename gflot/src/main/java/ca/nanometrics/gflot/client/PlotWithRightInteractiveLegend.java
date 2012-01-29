/*
 * Copyright (c) 2008 Nanometrics Inc.
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in
 *	all copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *	THE SOFTWARE.
 */
package ca.nanometrics.gflot.client;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Alexander De Leon
 */
public class PlotWithRightInteractiveLegend
    extends PlotWithInteractiveLegend
{

    private class RightLegendItem
        extends DefaultLegendItem
    {
        public RightLegendItem( String color, String label )
        {
            super( color, label );
        }

        @Override
        protected void init()
        {
            FlexTable table = new FlexTable();
            table.setWidth( "100%" );

            HTML colorBand = new HTML( "<div style=\"width: 100%; height: " + COLOR_BAND_HEIGHT + "; background-color: " + color + ";\"></div>" );
            table.setWidget( 0, 0, colorBand );
            table.getFlexCellFormatter().setColSpan( 0, 0, 2 );

            checkBox = new CheckBox();
            checkBox.setValue( true );
            table.setWidget( 1, 0, checkBox );
            table.getCellFormatter().setHorizontalAlignment( 1, 0, HasHorizontalAlignment.ALIGN_LEFT );

            m_labelsPanel = new HorizontalPanel();
            m_labelsPanel.add( new Label( label ) );
            table.setWidget( 1, 1, m_labelsPanel );
            table.getCellFormatter().setHorizontalAlignment( 1, 1, HasHorizontalAlignment.ALIGN_LEFT );
            table.getCellFormatter().setWidth( 1, 1, "100%" );

            initWidget( table );
        }
    }

    public PlotWithRightInteractiveLegend( PlotWidget plot )
    {
        super( plot );
    }

    @Override
    protected Widget createUi()
    {
        HorizontalPanel panel = new HorizontalPanel();
        Widget plotWidget = m_plot.getWidget();

        m_legendPanel = new VerticalPanel();

        panel.add( plotWidget );
        panel.add( m_legendPanel );

        return panel;
    }

    @Override
    protected LegendItem createLegendItem( String color, String label )
    {
        return new RightLegendItem( color, label );
    }

}
