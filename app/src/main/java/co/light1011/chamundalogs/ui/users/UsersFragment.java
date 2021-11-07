package co.light1011.chamundalogs.ui.users;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.List;

import co.light1011.chamundalogs.R;
import co.light1011.chamundalogs.model.UserC;
import co.light1011.chamundalogs.utils.UserListAdapter;

public class UsersFragment extends Fragment {

    private UsersViewModel usersViewModel;
    private RecyclerView savedUsersRecyclerView;
    private UserListAdapter savedUserListAdapter;
    private TextView noUserTextView;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        usersViewModel =
                new ViewModelProvider(this).get(UsersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_users, container, false);

        noUserTextView = root.findViewById(R.id.text_no_user);

        savedUsersRecyclerView = root.findViewById(R.id.saved_user_recycler_view);

        usersViewModel.getUsers().observe(getViewLifecycleOwner(),savedUsersListUpdateObserver);

        root.findViewById(R.id.add_user).setOnClickListener( v -> onAddNewUserClicked());


        savedUserListAdapter = new UserListAdapter(new UserListAdapter.UserCDiff());

        savedUsersRecyclerView.setHasFixedSize(true);
        savedUsersRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));

        savedUsersRecyclerView.setAdapter(savedUserListAdapter);

        return root;
    }

    Observer<List<UserC>> savedUsersListUpdateObserver = new Observer<List<UserC>>() {
        @Override
        public void onChanged(@Nullable List<UserC> _tables) {
            if(_tables.size() == 0){
                noUserTextView.setText("Add User");
                return;
            }
            noUserTextView.setVisibility(View.GONE);
            savedUsersRecyclerView.setVisibility(View.VISIBLE);

            savedUserListAdapter.submitList(_tables);


        }
    };

    private void onAddNewUserClicked(){
        int index = usersViewModel.getUsers().getValue().size() + 1;
        UserC _user = new UserC();
        _user.setId(index+"");
        _user.setName("Name "+index);
        usersViewModel.addUsers(_user);
    }
}