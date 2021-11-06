package co.light1011.chamundalogs.utils;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import co.light1011.chamundalogs.dao.ProductCDAO;
import co.light1011.chamundalogs.dao.TableCDAO;
import co.light1011.chamundalogs.dao.UserCDAO;
import co.light1011.chamundalogs.model.ProductC;
import co.light1011.chamundalogs.model.TableC;
import co.light1011.chamundalogs.model.UserC;

public class ChamundaAnalyticsRepository {

    private ProductCDAO productCDAO;
    private UserCDAO userCDAO;
    private TableCDAO tableCDAO;

    private MutableLiveData<ArrayList<TableC>> allTables;
    private MutableLiveData<ArrayList<ProductC>> allProducts;
    private MutableLiveData<ArrayList<UserC>> allUsers;

    // Note that in order to unit test the ChamundaAnalyticsRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    ChamundaAnalyticsRepository(Application application) {
        ChamundaAnalyticsDatabase db = ChamundaAnalyticsDatabase.getDatabase(application);
        productCDAO = db.productCDAO();
        userCDAO = db.userCDAO();
        tableCDAO = db.tableCDAO();

        allProducts = productCDAO.getProducts();
        allUsers = userCDAO.getUsers();
        allTables = tableCDAO.getTables();

    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    MutableLiveData<ArrayList<TableC>> getTables() {
        return allTables;
    }

    MutableLiveData<ArrayList<UserC>> getUsers() {
        return allUsers;
    }

    MutableLiveData<ArrayList<ProductC>> getProducts() {
        return allProducts;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void addTable(TableC tableC) {
        ChamundaAnalyticsDatabase.databaseWriteExecutor.execute(() -> {
            tableCDAO.insert(tableC);
        });
    }

    void addProduct(ProductC productC) {
        ChamundaAnalyticsDatabase.databaseWriteExecutor.execute(() -> {
            productCDAO.insert(productC);
        });
    }

    void addUser(UserC userC) {
        ChamundaAnalyticsDatabase.databaseWriteExecutor.execute(() -> {
            userCDAO.insert(userC);
        });
    }
}