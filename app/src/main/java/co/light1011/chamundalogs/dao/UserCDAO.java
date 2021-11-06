package co.light1011.chamundalogs.dao;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;

import co.light1011.chamundalogs.model.UserC;

@Dao
public interface UserCDAO {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UserC table);

    @Query("DELETE FROM userC_table")
    void deleteAll();

    @Query("SELECT * FROM userC_table ORDER BY id ASC")
    MutableLiveData<ArrayList<UserC>> getUsers();
}
