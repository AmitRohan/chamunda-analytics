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
import co.light1011.chamundalogs.model.ProductC;
import co.light1011.chamundalogs.model.ProductC;

public class ProductListAdapter extends ListAdapter<ProductC, ProductListAdapter.ActiveTableViewHolder> {

    public ProductListAdapter(@NonNull DiffUtil.ItemCallback<ProductC> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ActiveTableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ActiveTableViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ActiveTableViewHolder holder, int position) {
        ProductC current = getItem(position);
        holder.bind(""+(position + 1));
    }

    public static class ProductCDiff extends DiffUtil.ItemCallback<ProductC> {

        @Override
        public boolean areItemsTheSame(@NonNull ProductC oldItem, @NonNull ProductC newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ProductC oldItem, @NonNull ProductC newItem) {
            return oldItem.getId().equals(newItem.getId());
        }
    }

    static class ActiveTableViewHolder extends RecyclerView.ViewHolder {
        private final TextView tableIndex;

        private ActiveTableViewHolder(View itemView) {
            super(itemView);
            this.tableIndex = (TextView) itemView.findViewById(R.id.tableIndex);
        }

        public void bind(String text) {
            tableIndex.setText(text);
        }

        static ActiveTableViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_product, parent, false);
            return new ActiveTableViewHolder(view);
        }
    }
}