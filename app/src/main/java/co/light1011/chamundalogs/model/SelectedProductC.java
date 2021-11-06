package co.light1011.chamundalogs.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "selected_productC_table")
public class SelectedProductC {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "productId")
    private String productId;

    @ColumnInfo(name = "quantity")
    private float quantity;

}
