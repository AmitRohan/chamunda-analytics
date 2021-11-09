package co.light1011.chamundalogs.utils;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import co.light1011.chamundalogs.dao.ProductCDAO;
import co.light1011.chamundalogs.dao.SelectedProductCDAO;
import co.light1011.chamundalogs.dao.TableCDAO;
import co.light1011.chamundalogs.dao.UserCDAO;
import co.light1011.chamundalogs.model.ProductC;
import co.light1011.chamundalogs.model.SelectedProductC;
import co.light1011.chamundalogs.model.TableC;
import co.light1011.chamundalogs.model.UserC;

public class ChamundaAnalyticsRepository {

    private ProductCDAO productCDAO;
    private UserCDAO userCDAO;
    private TableCDAO tableCDAO;
    private SelectedProductCDAO selectedProductCDAO;

    private LiveData<List<TableC>> allTables;
    private LiveData<List<ProductC>> allProducts;
    private LiveData<List<UserC>> allUsers;
    private LiveData<List<SelectedProductC>> allSelectedProducts;

    // Note that in order to unit test the ChamundaAnalyticsRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public ChamundaAnalyticsRepository(Application application) {
        ChamundaAnalyticsDatabase db = ChamundaAnalyticsDatabase.getDatabase(application);
        productCDAO = db.productCDAO();
        userCDAO = db.userCDAO();
        tableCDAO = db.tableCDAO();
        selectedProductCDAO = db.selectedProductCDAO();

        allProducts = productCDAO.getProducts();
        allUsers = userCDAO.getUsers();
        allTables = tableCDAO.getTables();
        allSelectedProducts = selectedProductCDAO.getAllSelectedProducts();

    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<TableC>> getTables() {
        return allTables;
    }

    public LiveData<List<UserC>> getUsers() {
        return allUsers;
    }

    public LiveData<List<UserC>> getUserByName(String userName) {
        return userCDAO.getUsersByName(userName);
    }

    public LiveData<List<ProductC>> getProducts() {
        return allProducts;
    }

    public LiveData<List<SelectedProductC>> getSelectedProducts() {
        return allSelectedProducts;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void addTable(TableC tableC) {
        ChamundaAnalyticsDatabase.databaseWriteExecutor.execute(() -> {
            tableCDAO.insert(tableC);
        });
    }

    public void addProduct(ProductC productC) {
        ChamundaAnalyticsDatabase.databaseWriteExecutor.execute(() -> {
            productCDAO.insert(productC);
        });
    }

    public void addUser(UserC userC) {
        ChamundaAnalyticsDatabase.databaseWriteExecutor.execute(() -> {
            userCDAO.insert(userC);
        });
    }

    public void addSelectedProduct(SelectedProductC selectedProductC) {
        ChamundaAnalyticsDatabase.databaseWriteExecutor.execute(() -> {
            selectedProductCDAO.insert(selectedProductC);
        });
    }
}