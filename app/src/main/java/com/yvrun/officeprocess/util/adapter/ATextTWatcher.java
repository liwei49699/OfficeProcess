package com.yvrun.officeprocess.util.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

public abstract class ATextTWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        Log.d("--TAG--", "beforeTextChanged: " + s);
    }

    @Override
    public void afterTextChanged(Editable s) {

        Log.d("--TAG--", "beforeTextChanged: " + s);
    }
    
}
