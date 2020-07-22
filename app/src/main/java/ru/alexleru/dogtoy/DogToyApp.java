package ru.alexleru.dogtoy;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DogToyApp extends Application {
    public static boolean isConnectingToInternet(@NotNull Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }

    public static boolean isLike(int item, @NotNull Context context) {
        SharedPreferences sharedPreferences = context.
                getSharedPreferences(DogToyApp.class.getName(), Activity.MODE_PRIVATE);
        String itemString = String.valueOf(item);
        return sharedPreferences.getBoolean(itemString, false);
    }

    public static boolean setLike(int item, @NotNull Context context) {
        SharedPreferences sharedPreferences = context.
                getSharedPreferences(DogToyApp.class.getName(), Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String itemString = String.valueOf(item);
        boolean value = !isLike(item, context);
        editor.putBoolean(itemString,value);
        editor.apply();
        return value;
    }

    public static int getCountFavourites(@NotNull Context context){
        int sum = 0;
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(DogToyApp.class.getName(), Activity.MODE_PRIVATE);
        Map<String, Boolean> map = (Map<String, Boolean>) sharedPreferences.getAll();
        Collection<Boolean> arrayList = map.values();
        for (Boolean value : arrayList){
            if(value) sum++;
        }
        return sum;
    }

    public static String getSetOfNumbersOfFavourites(@NotNull Context context){
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(DogToyApp.class.getName(), Activity.MODE_PRIVATE);
        Map<String, Boolean> map = (Map<String, Boolean>) sharedPreferences.getAll();
        Set<String> key = map.keySet();
        int countFavorites = getCountFavourites(context);
        int[] integerItems = new int[countFavorites];
        //to Lambda
        int i = 0;
        for (Iterator<String> iterator = key.iterator(); iterator.hasNext();){
            String keyFor = iterator.next();
            boolean value = map.get(keyFor);
            if(value){
                integerItems[i] = Integer.parseInt(keyFor);
                i++;
            }
        }
        return arrayToString(integerItems);
    }

    private static String arrayToString(int[] intArray){
        int first = intArray[0];
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(first));
        for (int i = 1; i < intArray.length; i++) {
            stringBuilder.append("," + intArray[i]);
        }
        return stringBuilder.toString();
    }
}
