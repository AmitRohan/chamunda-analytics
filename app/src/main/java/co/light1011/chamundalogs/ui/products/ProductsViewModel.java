package co.light1011.chamundalogs.ui.products;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import co.light1011.chamundalogs.model.ProductC;
import co.light1011.chamundalogs.utils.ChamundaAnalyticsRepository;

public class ProductsViewModel extends AndroidViewModel {


    public ProductsViewModel(Application application) {
        super((application));
        mRepository = new ChamundaAnalyticsRepository(application);
        products = mRepository.getProducts();
    }

    private ChamundaAnalyticsRepository mRepository;
    private LiveData<List<ProductC>> products;


    public LiveData<List<ProductC>> getProducts() {
        return products;
    }

    public void addProduct(ProductC _product){
        mRepository.addProduct(_product);
    }

}