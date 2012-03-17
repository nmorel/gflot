package com.googlecode.gflot.examples.client.mvp;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;

public abstract class ActivityWithPlace<P extends Place>
    extends AbstractActivity
{
    private P place;

    protected P getPlace()
    {
        return place;
    }

    public Activity withPlace( P place )
    {
        this.place = place;
        return this;
    }
}
