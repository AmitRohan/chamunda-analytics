package co.light1011.chamundalogs.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import co.light1011.chamundalogs.model.TableC;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<TableC>> tables;

    public HomeViewModel() {
        tables = new MutableLiveData<>();
        tables.setValue(new ArrayList<>());
    }

    public LiveData<ArrayList<TableC>> getTables() {
        return  tables;
    }
}