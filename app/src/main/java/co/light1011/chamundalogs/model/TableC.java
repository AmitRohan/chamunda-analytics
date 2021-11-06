package co.light1011.chamundalogs.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import co.light1011.chamundalogs.utils.SelectedProductListConverter;

@Entity(tableName = "tableC_table")
public class TableC {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "user")
    private String userId;

    @ColumnInfo(name = "products")
    @TypeConverters(SelectedProductListConverter.class)
    private List<SelectedProductC> selectedProductCS;

    public List<SelectedProductC> getSelectedProductCS() {
        return selectedProductCS;
    }

    public void setSelectedProductCS(List<SelectedProductC> selectedProductCS) {
        this.selectedProductCS = selectedProductCS;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }
}
