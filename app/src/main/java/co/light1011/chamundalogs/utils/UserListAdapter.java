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
import co.light1011.chamundalogs.model.UserC;
import co.light1011.chamundalogs.model.UserC;

public class UserListAdapter extends ListAdapter<UserC, UserListAdapter.ActiveTableViewHolder> {

    public UserListAdapter(@NonNull DiffUtil.ItemCallback<UserC> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ActiveTableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ActiveTableViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ActiveTableViewHolder holder, int position) {
        UserC current = getItem(position);
        holder.bind(position,current);
    }

    public static class UserCDiff extends DiffUtil.ItemCallback<UserC> {

        @Override
        public boolean areItemsTheSame(@NonNull UserC oldItem, @NonNull UserC newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserC oldItem, @NonNull UserC newItem) {
            return oldItem.getId().equals(newItem.getId());
        }
    }

    static class ActiveTableViewHolder extends RecyclerView.ViewHolder {
        private final TextView tableIndex;
        private final TextView userName;

        private ActiveTableViewHolder(View itemView) {
            super(itemView);
            this.tableIndex = (TextView) itemView.findViewById(R.id.userIndex);
            this.userName = (TextView) itemView.findViewById(R.id.userName);
        }

        public void bind(int position, UserC user) {
            tableIndex.setText(user.getId());
            userName.setText(user.getName());
        }

        static ActiveTableViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_user, parent, false);
            return new ActiveTableViewHolder(view);
        }
    }
}