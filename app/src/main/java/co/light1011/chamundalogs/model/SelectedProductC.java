package co.light1011.chamundalogs.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "selected_productC_table")
public class SelectedProductC implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "productId")
    private String productId;

    @ColumnInfo(name = "quantity")
    private float quantity;

    @NonNull
    public String getId() {
        return id;
    }

    public float getQuantity() {
        return quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
