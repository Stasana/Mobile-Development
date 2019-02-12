package stas.melnyk.com.lab.ui.details;

import stas.melnyk.com.lab.data.db.DrinkDb;

public class DetailDrinkPresenter {


    private DetailDrinkView view;

    public DetailDrinkPresenter(DetailDrinkView view) {
        this.view = view;
    }

    public void isDrinkFavorite() {
        DrinkDb db = new DrinkDb(view.getContext());
        view.isFavorite(db.isDrinkFavorite(view.getDrink()));
    }

    public void addDrink(boolean favorite) {
        DrinkDb db = new DrinkDb(view.getContext());
        db.addDrink(view.getDrink(), favorite);
        view.isFavorite(db.isDrinkFavorite(view.getDrink()));
    }
}
