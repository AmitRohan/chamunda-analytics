package co.light1011.chamundalogs.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import co.light1011.chamundalogs.model.ProductC;

@Dao
public interface ProductCDAO {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ProductC table);

    @Query("DELETE FROM productC_table")
    void deleteAll();

    @Query("SELECT * FROM productC_table ORDER BY id ASC")
    LiveData<List<ProductC>> getProducts();
}
