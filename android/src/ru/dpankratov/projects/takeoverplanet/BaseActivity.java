package ru.dpankratov.projects.takeoverplanet;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @VisibleForTesting
    public ProgressBar ProgressBar;

    public void setProgressBar(ProgressBar progressBar) {
        ProgressBar = progressBar;
    }

    public void showProgressBar() {
        if (ProgressBar != null) {
            ProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        if (ProgressBar != null) {
            ProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    public void hideKeyboard(View view) {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressBar();
    }

}
