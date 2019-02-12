package stas.melnyk.com.lab.ui.drinks;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stas.melnyk.com.lab.R;
import stas.melnyk.com.lab.model.Drink;
import stas.melnyk.com.lab.ui.Router;
import stas.melnyk.com.lab.ui.details.DetailDrinkFragment;

import java.util.List;

public class ListOfDrinksFragment extends Fragment implements DrinkView, DrinksAdapter.AdapterAction {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView list;
    private View loadingView;
    private View emptyView;
    private DrinksPresenter presenter = new DrinksPresenter(this);
    private DrinksAdapter adapter = new DrinksAdapter();
    private Router router;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        router = new Router((AppCompatActivity) context, R.id.fragmentContainer);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_of_drinks, container, false);
        swipeRefreshLayout = view.findViewById(R.id.swipe);
        list = view.findViewById(R.id.list);
        loadingView = view.findViewById(R.id.loadingView);
        emptyView = view.findViewById(R.id.emptyView);
        list.setLayoutManager(new LinearLayoutManager(view.getContext()));
        list.setAdapter(adapter);
        adapter.action = this;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDrinks();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getDrinks();
    }

    @Override
    public void showDrinks(List<Drink> drinks) {
        if (drinks.isEmpty()) {
            showNoData();
        } else {
            adapter.setData(drinks);
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNoData() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClick(Drink drink) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("item", drink);
        router.replaceFragment(new DetailDrinkFragment(), bundle);
    }
}
