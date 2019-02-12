package stas.melnyk.com.lab.ui.details;

import android.content.Context;

import stas.melnyk.com.lab.model.Drink;

public interface DetailDrinkView {
    void isFavorite(Boolean isFavorite);

    Context getContext();

    Drink getDrink();
}
