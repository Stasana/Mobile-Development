package stas.melnyk.com.lab.ui.drinks;

import stas.melnyk.com.lab.model.Drink;
import stas.melnyk.com.lab.mvp.BaseView;

import java.util.List;

public interface DrinkView extends BaseView {
    void showDrinks(List<Drink> drinks);

    void showNoData();
}
