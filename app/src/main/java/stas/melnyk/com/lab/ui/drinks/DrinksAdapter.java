package stas.melnyk.com.lab.ui.drinks;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import stas.melnyk.com.lab.R;
import stas.melnyk.com.lab.model.Drink;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder> {

    private List<Drink> data = new ArrayList<>();
    public AdapterAction action;


    @NonNull
    @Override
    public DrinksViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DrinksViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_drink, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksViewHolder drinksViewHolder, int i) {
        final Drink item = data.get(i);
        Picasso.get()
                .load(item.getStrDrinkThumb())
                .into(drinksViewHolder.image);
        drinksViewHolder.name.setText(item.getStrDrink());
        drinksViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Drink> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    interface AdapterAction {
        void onItemClick(Drink drink);
    }

    class DrinksViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView name;

        public DrinksViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        }
    }
}
