package stas.melnyk.com.lab.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import stas.melnyk.com.lab.R;
import stas.melnyk.com.lab.ui.drinks.ListOfDrinksFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Router router = new Router(this, R.id.fragmentContainer);
        router.replaceFragment(new ListOfDrinksFragment());
    }


}
