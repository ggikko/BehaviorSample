package ggikko.me.behaviorsamples.ui;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ggikko.me.behaviorsamples.R;

public class CustomFabActivity extends AppCompatActivity {

    @OnClick(R.id.fab)
    void clickFabButton(){
        Snackbar.make(findViewById(R.id.container), "Hello! Ka Bank!", Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_fab);
        ButterKnife.bind(this);
    }
}
