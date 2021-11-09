package co.light1011.chamundalogs.ui.users;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import co.light1011.chamundalogs.model.UserC;
import co.light1011.chamundalogs.utils.ChamundaAnalyticsRepository;

public class UsersViewModel extends AndroidViewModel {

    private ChamundaAnalyticsRepository mRepository;
    private LiveData<List<UserC>> users;

    public UsersViewModel(Application application, ChamundaAnalyticsRepository _mRepository) {
        super((application));
        mRepository = _mRepository;
        users = mRepository.getUsers();
    }

    public LiveData<List<UserC>> getUsers() {
        return users;
    }

    public void addUsers(UserC _user){
        mRepository.addUser(_user);
    }



}