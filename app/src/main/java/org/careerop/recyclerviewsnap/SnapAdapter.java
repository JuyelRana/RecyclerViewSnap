package org.careerop.recyclerviewsnap;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

import java.util.ArrayList;

/**
 * Created by Juyel on 8/17/2017.
 */

public class SnapAdapter extends RecyclerView.Adapter<SnapAdapter.ViewHolder> implements GravitySnapHelper.SnapListener {

    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    private ArrayList<Snap> mSnaps;
    //Disable touch detection for parent recyclerView if we use vertical nested recyclerViews
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        }
    };

    public SnapAdapter() {
        mSnaps = new ArrayList<>();
    }

    public void addSnap(Snap snap) {
        mSnaps.add(snap);
    }

    @Override
    public int getItemViewType(int position) {
        Snap snap = mSnaps.get(position);
        switch (snap.getmGravity()) {
            case Gravity.CENTER_VERTICAL:
                return VERTICAL;
            case Gravity.CENTER_HORIZONTAL:
                return HORIZONTAL;
            case Gravity.START:
                return HORIZONTAL;
            case Gravity.TOP:
                return VERTICAL;
            case Gravity.END:
                return HORIZONTAL;
            case Gravity.BOTTOM:
                return VERTICAL;
        }
        return HORIZONTAL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = viewType == VERTICAL ? LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_snap_vertical, parent, false)
                : LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_snap, parent, false);

        if (viewType == VERTICAL) {
            view.findViewById(R.id.recyclerView).setOnTouchListener(mTouchListener);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Snap snap = mSnaps.get(position);
        holder.txtSnap.setText(snap.getmText());

        if (snap.getmGravity() == Gravity.START || snap.getmGravity() == Gravity.END) {

            holder.recyclerView.setLayoutManager(new LinearLayoutManager(
                    holder.recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false)
            );

            holder.recyclerView.setOnFlingListener(null);
            new GravitySnapHelper(snap.getmGravity(), false, this).attachToRecyclerView(holder.recyclerView);

        } else if (snap.getmGravity() == Gravity.CENTER_HORIZONTAL) {
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(

                    holder.recyclerView.getContext(), snap.getmGravity() == Gravity.CENTER_HORIZONTAL
                    ? LinearLayoutManager.HORIZONTAL : LinearLayoutManager.VERTICAL, false)
            );

            holder.recyclerView.setOnFlingListener(null);
            new LinearSnapHelper().attachToRecyclerView(holder.recyclerView);

        } else if (snap.getmGravity() == Gravity.CENTER) {

            holder.recyclerView.setLayoutManager(new LinearLayoutManager(
                    holder.recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false)
            );

            holder.recyclerView.setOnFlingListener(null);
            new PagerSnapHelper().attachToRecyclerView(holder.recyclerView);

        } else {

            holder.recyclerView.setLayoutManager(new LinearLayoutManager(
                    holder.recyclerView.getContext())
            );

            holder.recyclerView.setOnFlingListener(null);
            new GravitySnapHelper(snap.getmGravity()).attachToRecyclerView(holder.recyclerView);
        }

        holder.recyclerView.setAdapter(
                new Adapter(
                        snap.getmApps(),
                        snap.getmGravity() == Gravity.START
                                || snap.getmGravity() == Gravity.END
                                || snap.getmGravity() == Gravity.CENTER_HORIZONTAL,
                        snap.getmGravity() == Gravity.CENTER
                )
        );
    }

    @Override
    public int getItemCount() {
        return mSnaps.size();
    }

    @Override
    public void onSnap(int position) {
        Log.d("Snapped : ", position + "");
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtSnap;
        public RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            txtSnap = (TextView) itemView.findViewById(R.id.txtSnap);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
        }
    }
}
