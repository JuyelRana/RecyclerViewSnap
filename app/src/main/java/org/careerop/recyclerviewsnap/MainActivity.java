package org.careerop.recyclerviewsnap;

import android.app.Activity;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements Toolbar.OnMenuItemClickListener {

    public static final String ORIENTATION = "orientation";

    private RecyclerView mRecyclerView;
    private boolean mHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main);
        toolbar.setOnMenuItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        if (savedInstanceState == null) {
            mHorizontal = true;
        } else {
            mHorizontal = savedInstanceState.getBoolean(ORIENTATION);
        }

        setUpAdapter();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ORIENTATION, mHorizontal);
    }

    private void setUpAdapter() {
        List<App> apps = getApps();

        SnapAdapter snapAdapter = new SnapAdapter();
        if (mHorizontal) {
            snapAdapter.addSnap(new Snap(Gravity.CENTER_HORIZONTAL, "Snap Center", apps));
            snapAdapter.addSnap(new Snap(Gravity.START, "Snap Start", apps));
            snapAdapter.addSnap(new Snap(Gravity.END, "Snap End", apps));
            snapAdapter.addSnap(new Snap(Gravity.CENTER, "Pager Snap", apps));
        } else {
            snapAdapter.addSnap(new Snap(Gravity.CENTER_VERTICAL, "Snap Center", apps));
            snapAdapter.addSnap(new Snap(Gravity.TOP, "Snap Top", apps));
            snapAdapter.addSnap(new Snap(Gravity.BOTTOM, "Snap Bottom", apps));
        }
        mRecyclerView.setAdapter(snapAdapter);
    }

    private List<App> getApps() {
        List<App> apps = new ArrayList<>();
        apps.add(new App(R.drawable.google, "Google+", 4.6f));
        apps.add(new App(R.drawable.gmail, "Gmail", 4.8f));
        apps.add(new App(R.drawable.inbox, "Inbox", 4.5f));
        apps.add(new App(R.drawable.hangouts, "Keep", 4.8f));
        apps.add(new App(R.drawable.drive, "Google Drive", 4.7f));
        apps.add(new App(R.drawable.hangouts, "Hangouts", 4.6f));
        apps.add(new App(R.drawable.photos, "Photos", 4.6f));
        apps.add(new App(R.drawable.messenger, "Messenger", 4.7f));
        apps.add(new App(R.drawable.sheets, "Sheets", 4.3f));
        apps.add(new App(R.drawable.slide, "Slide", 4.2f));
        apps.add(new App(R.drawable.docs, "Docs", 4.6f));
        return apps;
    }

    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.layoutType) {
            mHorizontal = !mHorizontal;
            setUpAdapter();
            item.setTitle((mHorizontal ? "Vertical" : "Horizontal"));
        }

        return false;
    }
}
