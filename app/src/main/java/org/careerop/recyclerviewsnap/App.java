package org.careerop.recyclerviewsnap;

/**
 * Created by Juyel on 8/17/2017.
 */

public class App {
    private int mDrawable;
    private String nName;
    private float mRating;

    public App(int mDrawable, String nName, float mRating) {
        this.mDrawable = mDrawable;
        this.nName = nName;
        this.mRating = mRating;
    }

    public int getmDrawable() {
        return mDrawable;
    }

    public String getnName() {
        return nName;
    }

    public float getmRating() {
        return mRating;
    }
}
