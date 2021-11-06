package co.light1011.chamundalogs.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "tableC_table")
public class TableC {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "user")
    private UserC userC;

    @ColumnInfo(name = "products")
    private ArrayList<ProductC> productCs;

    public String getId() {
        return id;
    }

    public UserC getUserC() {
        return userC;
    }

    public ArrayList<ProductC> getProductCs() {
        return productCs;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserC(UserC userC) {
        this.userC = userC;
    }

    public void setProductCs(ArrayList<ProductC> productCs) {
        this.productCs = productCs;
    }
}
