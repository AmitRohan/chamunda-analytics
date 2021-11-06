package co.light1011.chamundalogs.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.light1011.chamundalogs.R;
import co.light1011.chamundalogs.model.TableC;

public class ActiveTableListAdapter extends RecyclerView.Adapter<ActiveTableListAdapter.ActiveTableViewHolder>{
    private ArrayList<TableC> listdata;

    // RecyclerView recyclerView;
    public ActiveTableListAdapter(ArrayList<TableC> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ActiveTableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_active_table, parent, false);
        ActiveTableViewHolder viewHolder = new ActiveTableViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ActiveTableViewHolder holder, int position) {
        final TableC myListData = listdata.get(position);
        holder.tableIndex.setText(""+ (position + 1));
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public void updateList(ArrayList<TableC> tables) {
        listdata = tables;
    }

    public static class ActiveTableViewHolder extends RecyclerView.ViewHolder {
        public TextView tableIndex;
        public ActiveTableViewHolder(View itemView) {
            super(itemView);
            this.tableIndex = (TextView) itemView.findViewById(R.id.tableIndex);
        }
    }
}