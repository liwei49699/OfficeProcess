package com.yvrun.officeprocess.jetpack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yvrun.officeprocess.jetpack.ui.test.TestFragment;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TestFragment.newInstance())
                    .commitNow();
        }
    }
}
