package co.light1011.chamundalogs.dao;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;

import co.light1011.chamundalogs.model.TableC;

@Dao
public interface TableCDAO {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TableC table);

    @Query("DELETE FROM tableC_table")
    void deleteAll();

    @Query("SELECT * FROM tableC_table ORDER BY id ASC")
    MutableLiveData<ArrayList<TableC>> getTables();
}
