package org.careerop.recyclerviewsnap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Juyel on 8/17/2017.
 */


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<App> mApps;
    private boolean mHorizontal;
    private boolean mPager;

    public String name;

    public Adapter(List<App> mApps, boolean mHorizontal, boolean mPager) {
        this.mApps = mApps;
        this.mHorizontal = mHorizontal;
        this.mPager = mPager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mPager) {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_pager, parent, false));
        } else {
            return mHorizontal ? new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter, parent, false)) : new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_verticle, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final App app = mApps.get(position);
        holder.imageView.setImageResource(app.getmDrawable());
        holder.txtName.setText(app.getnName());
        holder.txtRating.setText(String.valueOf(app.getmRating()));

        name = app.getnName();

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public TextView txtName;
        public TextView txtRating;

        public ViewHolder(final View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtRating = (TextView) itemView.findViewById(R.id.txtRating);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(itemView.getContext(), "Clicked \n" + txtName.getText() + "\n" + txtRating.getText(), Toast.LENGTH_SHORT).show();
        }
    }
}
