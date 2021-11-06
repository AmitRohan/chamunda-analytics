package co.light1011.chamundalogs.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import co.light1011.chamundalogs.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_no_customer);
        homeViewModel.getTables().observe(getViewLifecycleOwner(), _tables -> {

            if(_tables.size() == 0){
                textView.setText("Add Tables");
                return;
            }
            textView.setVisibility(View.GONE);
        });
        return root;
    }
}