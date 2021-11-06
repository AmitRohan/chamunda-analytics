package co.light1011.chamundalogs.model;

import java.util.ArrayList;

public class TableC {
    private String id;
    private UserC userC;
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
