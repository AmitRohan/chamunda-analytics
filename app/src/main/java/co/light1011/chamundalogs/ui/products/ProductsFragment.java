package co.light1011.chamundalogs.ui.products;

import android.content.DialogInterface;
import android.os.Bundle;
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
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.List;

import co.light1011.chamundalogs.R;
import co.light1011.chamundalogs.model.ProductC;
import co.light1011.chamundalogs.model.UserC;
import co.light1011.chamundalogs.ui.tables.TablesViewModel;
import co.light1011.chamundalogs.ui.users.UsersViewModel;
import co.light1011.chamundalogs.utils.ChamundaAnalyticsRepository;
import co.light1011.chamundalogs.utils.CustomViewModelFactory;
import co.light1011.chamundalogs.utils.ProductListAdapter;
import co.light1011.chamundalogs.utils.UserListAdapter;

public class ProductsFragment extends Fragment {

    ChamundaAnalyticsRepository repository;
    public ProductsFragment(ChamundaAnalyticsRepository car){
        // require a empty public constructor
        repository = car;
    }

    private ProductsViewModel productsViewModel;


    private RecyclerView savedUsersRecyclerView;
    private ProductListAdapter savedUserListAdapter;
    private TextView noUserTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        productsViewModel = ViewModelProviders.of(this,
                        new CustomViewModelFactory(getActivity().getApplication(), repository))
                        .get(ProductsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_products, container, false);

        noUserTextView = root.findViewById(R.id.text_no_products);
        savedUsersRecyclerView = root.findViewById(R.id.saved_products_recycler_view);

        productsViewModel.getProducts().observe(getViewLifecycleOwner(),savedProductsListUpdateObserver);

        root.findViewById(R.id.add_product).setOnClickListener( v -> onAddNewUserClicked());


        savedUserListAdapter = new ProductListAdapter(new ProductListAdapter.ProductCDiff());

        savedUsersRecyclerView.setHasFixedSize(true);
        savedUsersRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,1));

        savedUsersRecyclerView.setAdapter(savedUserListAdapter);

        return root;
    }

    Observer<List<ProductC>> savedProductsListUpdateObserver = new Observer<List<ProductC>>() {
        @Override
        public void onChanged(@Nullable List<ProductC> _tables) {
            if(_tables.size() == 0){
                noUserTextView.setText("Add Products");
                return;
            }
            noUserTextView.setVisibility(View.GONE);
            savedUsersRecyclerView.setVisibility(View.VISIBLE);

            savedUserListAdapter.submitList(_tables);


        }
    };

    private void onAddNewUserClicked(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New Product");

        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.dialog_new_product, (ViewGroup) getView(), false);


        // Set up the input
        final EditText inputName = viewInflated.findViewById(R.id.new_product_name);
        final EditText inputPrice = viewInflated.findViewById(R.id.new_product_price);


        // Set up the buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = inputName.getText().toString();
                String price = inputPrice.getText().toString();

                int index = productsViewModel.getProducts().getValue().size() + 1;
                ProductC _product = new ProductC();
                _product.setId(index+"");
                _product.setName(name);
                _product.setPrice(Float.parseFloat(price));
                productsViewModel.addProduct(_product);

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