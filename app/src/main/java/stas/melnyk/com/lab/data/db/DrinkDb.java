package stas.melnyk.com.lab.data.db;

import android.content.Context;
import android.content.SharedPreferences;

import stas.melnyk.com.lab.model.Drink;

public class DrinkDb {
    private Context context;
    private SharedPreferences sharedPreferences;


    public DrinkDb(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
    }

    public void addDrink(Drink drink, boolean isFavorite) {
        sharedPreferences.edit().putBoolean(drink.getIdDrink(), isFavorite).apply();
    }

    public boolean isDrinkFavorite(Drink drink) {
        return sharedPreferences.getBoolean(drink.getIdDrink(), false);
    }
}
