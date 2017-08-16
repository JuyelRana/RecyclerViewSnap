package org.careerop.recyclerviewsnap;

import java.util.List;

/**
 * Created by Juyel on 8/17/2017.
 */

public class Snap {
    private int mGravity;
    private String mText;
    private List<App> mApps;

    public Snap(int mGravity, String mText, List<App> mApps) {
        this.mGravity = mGravity;
        this.mText = mText;
        this.mApps = mApps;
    }

    public int getmGravity() {
        return mGravity;
    }

    public String getmText() {
        return mText;
    }

    public List<App> getmApps() {
        return mApps;
    }
}
