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
        holder.bind(position,current);
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
        private final TextView productId;
        private final TextView productName;
        private final TextView productPrice;

        private ActiveTableViewHolder(View itemView) {
            super(itemView);
            this.productId = (TextView) itemView.findViewById(R.id.productIndex);
            this.productName = (TextView) itemView.findViewById(R.id.productName);
            this.productPrice = (TextView) itemView.findViewById(R.id.productCost);
        }

        public void bind(int position,ProductC productC) {
            productId.setText(productC.getId());
            productName.setText(productC.getName());
            productPrice.setText(productC.getPrice() + " INR");
        }

        static ActiveTableViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_product, parent, false);
            return new ActiveTableViewHolder(view);
        }
    }
}