package co.light1011.chamundalogs.ui.home;

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

import java.util.ArrayList;

import co.light1011.chamundalogs.R;
import co.light1011.chamundalogs.model.TableC;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView noCustomerTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        noCustomerTextView = root.findViewById(R.id.text_no_customer);
        homeViewModel.getTables().observe(getViewLifecycleOwner(),tableListUpdateObserver);

        root.findViewById(R.id.addTable).setOnClickListener( v -> onAddNewTableClicked());

        return root;
    }

    Observer<ArrayList<TableC>> tableListUpdateObserver = new Observer<ArrayList<TableC>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TableC> _tables) {
            if(_tables.size() == 0){
                noCustomerTextView.setText("Add Tables");
                return;
            }
            noCustomerTextView.setVisibility(View.GONE);
        }
    };

    private void onAddNewTableClicked(){
        homeViewModel.addTable(new TableC());
    }
}