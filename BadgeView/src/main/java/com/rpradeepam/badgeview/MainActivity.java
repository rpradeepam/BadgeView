package com.rpradeepam.badgeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private BadgeView badge_view1, badge_view2,badge_view3;
    private static final String COUNT = "count";
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(COUNT);
        }
        setContentView(R.layout.activity_main);

        Button plus;
        Button minus;


        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        badge_view1 = (BadgeView) findViewById(R.id.button1);
        badge_view2 = (BadgeView) findViewById(R.id.button2);
        badge_view3 = (BadgeView) findViewById(R.id.button3);
        setBadgeValue();

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = count + 1;
                setBadgeValue();
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count != 0) {
                    count = count - 1;
                    setBadgeValue();
                }
            }
        });

    }

    private void setBadgeValue() {
        String val = "";
        val = count == 0 ? val = "" : count + "";
        badge_view1.setBadgeValue(val);
        badge_view2.setBadgeValue(val);
        badge_view3.setBadgeValue(val);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNT, count);
    }
}
