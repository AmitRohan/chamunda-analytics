package co.light1011.chamundalogs.utils;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class ProductListConverter {
    @TypeConverter
    public List<String> storedStringToProductIds(String value) {
        List<String> toReturn = Arrays.asList(value.split("\\s*,\\s*"));
        return toReturn;
    }

    @TypeConverter
    public String productIdsToStoredString(List<String> productIdsInString) {
        String value = "";

        for (String lang : productIdsInString)
            value += lang + ",";

        return value;
    }
}