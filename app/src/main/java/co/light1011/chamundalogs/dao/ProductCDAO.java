package co.light1011.chamundalogs.dao;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;

import co.light1011.chamundalogs.model.TableC;

@Dao
public interface ProductCDAO {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TableC table);

    @Query("DELETE FROM productC_table")
    void deleteAll();

    @Query("SELECT * FROM productC_table ORDER BY id ASC")
    MutableLiveData<ArrayList<TableC>> getProducts();
}
