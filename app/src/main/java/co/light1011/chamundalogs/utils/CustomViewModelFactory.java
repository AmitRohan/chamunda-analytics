package co.light1011.chamundalogs.utils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import co.light1011.chamundalogs.ui.products.ProductsViewModel;
import co.light1011.chamundalogs.ui.tables.TablesViewModel;
import co.light1011.chamundalogs.ui.users.UsersViewModel;

public class CustomViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    private final Application application;

    private final ChamundaAnalyticsRepository chamundaAnalyticsRepository;

    public CustomViewModelFactory(@NonNull Application application, ChamundaAnalyticsRepository cAR) {
        this.application = application;
        this.chamundaAnalyticsRepository = cAR;
    }
    @NotNull
    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        if (modelClass == ProductsViewModel.class) {
            return (T) new ProductsViewModel(application, chamundaAnalyticsRepository);
        }else if (modelClass == TablesViewModel.class) {
            return (T) new TablesViewModel(application, chamundaAnalyticsRepository);
        }else if (modelClass == UsersViewModel.class) {
            return (T) new UsersViewModel(application, chamundaAnalyticsRepository);
        }

        return null;
    }
}


