package stas.melnyk.com.lab.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class Router {
    private AppCompatActivity activity;
    private int containerId;

    public Router(AppCompatActivity activity, @IdRes int containerId) {
        this.activity = activity;
        this.containerId = containerId;
    }

    public void addFragment(@NonNull Fragment fragment, @Nullable Bundle bundle) {
        if (bundle != null)
            fragment.setArguments(bundle);
        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(containerId, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void replaceFragment(@NonNull Fragment fragment, @Nullable Bundle bundle) {
        if (bundle != null)
            fragment.setArguments(bundle);
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void replaceFragment(@NonNull Fragment fragment) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, fragment)
                .commit();
    }
}
