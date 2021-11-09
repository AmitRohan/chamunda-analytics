package co.light1011.chamundalogs.utils;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import co.light1011.chamundalogs.model.SelectedProductC;

public class SelectedProductListConverter {
    Gson gson = new Gson();
    @TypeConverter
    public List<SelectedProductC> storedStringToProductIds(String value) {
        List<String> _toReturn = Arrays.asList(value.split("\\s*,-,\\s*"));
        List<SelectedProductC> toReturn = Collections.<SelectedProductC>emptyList();
        if(_toReturn.size() == 0 || !_toReturn.get(0).equals("")){
            for (String json : _toReturn)
                toReturn.add(gson.fromJson(json, SelectedProductC.class));
        }

        return toReturn;
    }

    @TypeConverter
    public String productIdsToStoredString(List<SelectedProductC> productIdsInString) {
        if(productIdsInString == null){
            return "";
        }

        String value = "";

        for (SelectedProductC myObject : productIdsInString)
            value += gson.toJson(myObject) + ",-,";

        return value;
    }
}