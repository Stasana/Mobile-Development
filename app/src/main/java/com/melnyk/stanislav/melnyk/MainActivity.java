package com.melnyk.stanislav.melnyk;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.data.ApnSetting;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.melnyk.stanislav.melnyk.Entity.Book;
import com.melnyk.stanislav.melnyk.Entity.Result;
import com.melnyk.stanislav.melnyk.RVAdapter.BookAdapter;
import com.melnyk.stanislav.melnyk.Retrofit.APIServise;
import com.melnyk.stanislav.melnyk.Retrofit.ApiUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private APIServise apiService;
    private BookAdapter adapter;
    @BindView(R.id.rv_main)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_main)
    SwipeRefreshLayout pullToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiService = ApiUtil.getSOService();
        ButterKnife.bind(this);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadBooks();
                pullToRefresh.setRefreshing(false);
            }
        });
        adapter = new BookAdapter(this, new ArrayList<Book>(0));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        loadBooks();

    }

    private void loadBooks() {
        apiService.getBools().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    adapter.updateBooks(response.body().getBooks());
                } else {
                    Toast.makeText(MainActivity.this, "error loading from API",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
                Toast.makeText(MainActivity.this, "error loading from API",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


}
