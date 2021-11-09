package co.light1011.chamundalogs.ui.tables;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import co.light1011.chamundalogs.R;
import co.light1011.chamundalogs.model.SelectedProductC;
import co.light1011.chamundalogs.model.TableC;
import co.light1011.chamundalogs.model.UserC;
import co.light1011.chamundalogs.utils.ActiveTableListAdapter;
import co.light1011.chamundalogs.utils.ChamundaAnalyticsRepository;
import co.light1011.chamundalogs.utils.CustomViewModelFactory;

public class TablesFragment extends Fragment {

    ChamundaAnalyticsRepository repository;
    public TablesFragment(ChamundaAnalyticsRepository car){
        // require a empty public constructor
        repository = car;
    }

    private RecyclerView activeTables;
    private ActiveTableListAdapter activeTablesListAdapter;

    private TablesViewModel tablesViewModel;
    private TextView noCustomerTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        tablesViewModel = ViewModelProviders.of(this,
                new CustomViewModelFactory(getActivity().getApplication(), repository))
                .get(TablesViewModel.class);
//                new ViewModelProvider(getActivity()).get(TablesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tables, container, false);

        noCustomerTextView = root.findViewById(R.id.text_no_customer);

        activeTables = root.findViewById(R.id.activeTables);

        tablesViewModel.getTables().observe(getViewLifecycleOwner(),tableListUpdateObserver);

        root.findViewById(R.id.addTable).setOnClickListener( v -> onAddNewTableClicked());


        activeTablesListAdapter = new ActiveTableListAdapter(new ActiveTableListAdapter.TableCDiff());

        activeTables.setHasFixedSize(true);
        activeTables.setLayoutManager(new StaggeredGridLayoutManager(2,1));

        activeTables.setAdapter(activeTablesListAdapter);

        return root;
    }

    Observer<List<TableC>> tableListUpdateObserver = new Observer<List<TableC>>() {
        @Override
        public void onChanged(@Nullable List<TableC> _tables) {
            if(_tables.size() == 0){
                noCustomerTextView.setText("Add Tables");
                return;
            }
            noCustomerTextView.setVisibility(View.GONE);
            activeTables.setVisibility(View.VISIBLE);

            activeTablesListAdapter.submitList(_tables);
//            activeTablesListAdapter.notifyDataSetChanged();


        }
    };

    private void onAddNewTableClicked(){

        List<UserC> users = tablesViewModel.getUsers().getValue();
        String[] userNames = new String[0];
        final int existingUsedDbSize = users != null ? users.size() : 0;
        if (users != null) {
            userNames = new String[existingUsedDbSize];
            for(int i = 0; i < users.size(); i++)
                userNames[i] = users.get(i).getName();
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, userNames);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New Table");

        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_table, (ViewGroup) getView(), false);


// Set up the input
        final AutoCompleteTextView input = viewInflated.findViewById(R.id.new_table_owner_name);
        input.setAdapter(adapter);
        input.setThreshold(2);

// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text



// Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int index = tablesViewModel.getTables().getValue().size() + 1;
                String customerName = input.getText().toString();

                String userId = "";
                List<UserC> existingUsersWithSameName = tablesViewModel.getUsersByName(customerName).getValue();
                if(existingUsersWithSameName != null){
                    for (UserC userC: existingUsersWithSameName){
                        if(userC.getName().equals(customerName)){
                            userId = userC.getId();
                            break;
                        }
                    }
                }else{
                    userId = ""+ (existingUsedDbSize + 1);
                    UserC _user = new UserC();
                    _user.setId(userId);
                    _user.setName(customerName);
                    tablesViewModel.addUsers(_user);
                }

                TableC tableC = new TableC();
                tableC.setId(index+"");
                tableC.setUserId(userId);
                tableC.setUserName(customerName);
                tableC.setSelectedProductCS(Collections.<SelectedProductC>emptyList());
                tablesViewModel.addTable(tableC);


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