package com.example.franciscustomersdata;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import java.util.ArrayList;

public class TownsRecyclerViewAdapter extends RecyclerView.Adapter<TownsRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "TownRecyclerViewAdapter";
    /* access modifiers changed from: private */
    public final Context context;
    private ArrayList<Town> towns = new ArrayList<>();

    public TownsRecyclerViewAdapter(Context context2) {
        this.context = context2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called");
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.town_name, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.txtTown.setText(this.towns.get(position).getTownName());
    }

    public int getItemCount() {
        return this.towns.size();
    }

    public void setTowns(ArrayList<Town> towns2) {
        this.towns = towns2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final MaterialCardView customerParent;
        private final ImageView imageData;
        private final MaterialCardView townParent;
        private final TextView txtCustomerName;
        /* access modifiers changed from: private */
        public final TextView txtTown;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.d(TownsRecyclerViewAdapter.TAG, "ViewHolder: called");
            this.townParent = (MaterialCardView) itemView.findViewById(R.id.townParent);
            this.customerParent = (MaterialCardView) itemView.findViewById(R.id.customerParent);
            this.imageData = (ImageView) itemView.findViewById(R.id.imageData);
            TextView textView = (TextView) itemView.findViewById(R.id.txtTown);
            this.txtTown = textView;
            this.txtCustomerName = (TextView) itemView.findViewById(R.id.txtCustomerName);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Utils.emptyList();
                    Utils.initTownCustomers(ViewHolder.this.txtTown.getText().toString(), TownsRecyclerViewAdapter.this.context);
                    TownsRecyclerViewAdapter.this.context.startActivity(new Intent(TownsRecyclerViewAdapter.this.context, SelectedTownActivity.class));
                }
            });
        }
    }
}
