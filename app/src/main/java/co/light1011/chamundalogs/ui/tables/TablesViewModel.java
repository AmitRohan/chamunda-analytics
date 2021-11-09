package co.light1011.chamundalogs.ui.tables;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

import co.light1011.chamundalogs.model.ProductC;
import co.light1011.chamundalogs.model.TableC;
import co.light1011.chamundalogs.model.UserC;
import co.light1011.chamundalogs.utils.ChamundaAnalyticsRepository;

public class TablesViewModel extends AndroidViewModel {

    private ChamundaAnalyticsRepository mRepository;

    private LiveData<List<TableC>> tables;
    private LiveData<List<UserC>> users;
    private LiveData<List<ProductC>> products;

    public TablesViewModel(Application application,ChamundaAnalyticsRepository _mRepository) {
        super(application);

        mRepository = _mRepository;
        tables = mRepository.getTables();
        users = mRepository.getUsers();
        products = mRepository.getProducts();

    }

    public LiveData<List<TableC>> getTables() {
        return  tables;
    }

    public LiveData<List<ProductC>> getProducts() {
        return products;
    }

    public LiveData<List<UserC>> getUsers() {
        return users;
    }

    public LiveData<List<UserC>> getUsersByName(String userName) {
        return mRepository.getUserByName(userName);
    }

    public void addTable(TableC _table){
        mRepository.addTable(_table);
    }

    public void addUsers(UserC user) {
        mRepository.addUser(user);
    }
}