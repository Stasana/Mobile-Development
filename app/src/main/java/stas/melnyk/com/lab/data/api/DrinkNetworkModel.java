package stas.melnyk.com.lab.data.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import stas.melnyk.com.lab.model.Drink;
import stas.melnyk.com.lab.model.Example;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DrinkNetworkModel {
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://thecocktaildb.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit = builder.build();
    private DrinkInterface drinkInterface = retrofit.create(DrinkInterface.class);

    public Observable<List<Drink>> getDrinks() {
        return drinkInterface.imageOfAlcohol()
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<Example, ObservableSource<List<Drink>>>() {
                    @Override
                    public ObservableSource<List<Drink>> apply(Example example) {
                        return Observable.just(example.getDrinks());
                    }
                });
    }
}
