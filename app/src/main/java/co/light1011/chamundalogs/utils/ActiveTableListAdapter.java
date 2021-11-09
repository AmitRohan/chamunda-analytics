package co.light1011.chamundalogs.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import co.light1011.chamundalogs.R;
import co.light1011.chamundalogs.model.TableC;

public class ActiveTableListAdapter extends ListAdapter<TableC,ActiveTableListAdapter.ActiveTableViewHolder> {

    public ActiveTableListAdapter(@NonNull DiffUtil.ItemCallback<TableC> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ActiveTableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ActiveTableViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ActiveTableViewHolder holder, int position) {
        TableC current = getItem(position);
        holder.bind(position,current);
    }

    public static class TableCDiff extends DiffUtil.ItemCallback<TableC> {

        @Override
        public boolean areItemsTheSame(@NonNull TableC oldItem, @NonNull TableC newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull TableC oldItem, @NonNull TableC newItem) {
            return oldItem.getUserId().equals(newItem.getUserId());
        }
    }

    static class ActiveTableViewHolder extends RecyclerView.ViewHolder {
        private final TextView tableIndex;
        private final TextView tableUser;

        private ActiveTableViewHolder(View itemView) {
            super(itemView);
            this.tableIndex = (TextView) itemView.findViewById(R.id.tableIndex);
            this.tableUser = (TextView) itemView.findViewById(R.id.tableUser);
        }

        public void bind(int position,TableC tableC) {

            tableIndex.setText(tableC.getId());
            tableUser.setText(tableC.getUserName());
        }

        static ActiveTableViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_active_table, parent, false);
            return new ActiveTableViewHolder(view);
        }
    }
}