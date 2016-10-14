package ggikko.me.behaviorsamples.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ggikko.me.behaviorsamples.R;
import ggikko.me.behaviorsamples.ui.fab.BasicFabActivity;
import ggikko.me.behaviorsamples.ui.fab.CustomFabActivity;
import ggikko.me.behaviorsamples.ui.scroll.CustomScrollActivity;

public class MainActivity extends AppCompatActivity {

    @OnClick({R.id.goToBasicFabExample, R.id.goToCustomFabExample, R.id.goToCustomScrollExample})
    void callOnClick(View view){
        switch (view.getId()){
            case R.id.goToBasicFabExample:
                goToAnoterActivity(BasicFabActivity.class);
                break;
            case R.id.goToCustomFabExample:
                goToAnoterActivity(CustomFabActivity.class);
                break;
            case R.id.goToCustomScrollExample:
                goToAnoterActivity(CustomScrollActivity.class);
                break;
        }
    }

    public void goToAnoterActivity(Class clazz){
        startActivity(new Intent(MainActivity.this, clazz));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }
}
