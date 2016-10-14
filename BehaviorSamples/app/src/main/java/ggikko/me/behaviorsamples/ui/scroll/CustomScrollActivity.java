package ggikko.me.behaviorsamples.ui.scroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import ggikko.me.behaviorsamples.R;
import ggikko.me.behaviorsamples.ui.scroll.adapter.CustomScrollAdapter;

public class CustomScrollActivity extends AppCompatActivity {

    @BindView(R.id.customRecyclerView) RecyclerView customRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_scroll);
        ButterKnife.bind(this);

        customRecyclerView.setAdapter(new CustomScrollAdapter(Arrays.asList(Data.items)));
        customRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
