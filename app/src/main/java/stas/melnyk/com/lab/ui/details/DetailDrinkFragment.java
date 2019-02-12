package stas.melnyk.com.lab.ui.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import stas.melnyk.com.lab.R;
import stas.melnyk.com.lab.model.Drink;
import com.squareup.picasso.Picasso;

public class DetailDrinkFragment extends Fragment implements DetailDrinkView {

    private ImageView image;
    private TextView name;
    private TextView category;
    private TextView desc;
    private ImageView favorite;
    private DetailDrinkPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_drink, container, false);
        image = view.findViewById(R.id.image);
        name = view.findViewById(R.id.name);
        category = view.findViewById(R.id.category);
        desc = view.findViewById(R.id.desc);
        favorite = view.findViewById(R.id.favorite);
        presenter = new DetailDrinkPresenter(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initDrink(getDrink());
    }


    private void initDrink(Drink drink) {
        Picasso.get()
                .load(drink.getStrDrinkThumb())
                .into(image);
        name.setText(drink.getStrDrink());
        category.setText(drink.getStrCategory());
        desc.setText(drink.getStrAlcoholic());
        presenter.isDrinkFavorite();
    }

    @Override
    public void isFavorite(final Boolean isFavorite) {
        favorite.setImageResource(isFavorite ? R.drawable.ic_favorite_red_24dp : R.drawable.ic_favorite_grey_24dp);

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addDrink(!isFavorite);

            }
        });
    }

    @Override
    public Drink getDrink() {
        return (Drink) getArguments().getSerializable("item");
    }
}
