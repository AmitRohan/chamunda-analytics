package co.light1011.chamundalogs.ui.users;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New Customer");

        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_user, (ViewGroup) getView(), false);


// Set up the input
        final EditText input = viewInflated.findViewById(R.id.new_customer_name);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text



// Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = input.getText().toString();

                int index = usersViewModel.getUsers().getValue().size() + 1;
                UserC _user = new UserC();
                _user.setId(index+"");
                _user.setName(name);
                usersViewModel.addUsers(_user);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setView(viewInflated);
        builder.show();
    }
}