package co.light1011.chamundalogs;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;


import co.light1011.chamundalogs.ui.dashboard.DashboardFragment;
import co.light1011.chamundalogs.ui.products.ProductsFragment;
import co.light1011.chamundalogs.ui.tables.TablesFragment;
import co.light1011.chamundalogs.ui.users.UsersFragment;
import co.light1011.chamundalogs.utils.ChamundaAnalyticsRepository;

public class MainActivity extends AppCompatActivity {

    DashboardFragment dashboardFragment;
    ProductsFragment productsFragment;
    TablesFragment  tablesFragment;
    UsersFragment usersFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChamundaAnalyticsRepository chamundaAnalyticsRepository =new ChamundaAnalyticsRepository(getApplication());


        BottomNavigationView navView = findViewById(R.id.nav_view);

        dashboardFragment = new DashboardFragment();
        productsFragment = new ProductsFragment(chamundaAnalyticsRepository);
        tablesFragment = new TablesFragment(chamundaAnalyticsRepository);
        usersFragment = new UsersFragment(chamundaAnalyticsRepository);


        navView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_tables:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, tablesFragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, dashboardFragment).commit();
                    return true;
                case R.id.navigation_products:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, productsFragment).commit();
                    return true;
                case R.id.navigation_users:
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, usersFragment).commit();
                    return true;
            }
            return false;
        });
        navView.setSelectedItemId(R.id.navigation_tables);

    }

}