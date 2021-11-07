package co.light1011.chamundalogs.ui.tables;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

import co.light1011.chamundalogs.model.TableC;
import co.light1011.chamundalogs.utils.ChamundaAnalyticsRepository;

public class TablesViewModel extends AndroidViewModel {

    private ChamundaAnalyticsRepository mRepository;

    private LiveData<List<TableC>> tables;

    public TablesViewModel(Application application) {
        super(application);

        mRepository = new ChamundaAnalyticsRepository(application);
        tables = mRepository.getTables();

    }

    public LiveData<List<TableC>> getTables() {
        return  tables;
    }

    public void addTable(TableC _table){
        mRepository.addTable(_table);
    }
}