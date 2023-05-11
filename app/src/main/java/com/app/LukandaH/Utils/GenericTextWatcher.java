package com.app.LukandaH.Utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.app.LukandaH.R;

public class GenericTextWatcher implements TextWatcher {
    private final EditText[] editText;
    private View view;

    public GenericTextWatcher(View view, EditText editText[]) {
        this.editText = editText;
        this.view = view;
    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        switch (view.getId()) {

            case R.id.et_otpBox1:
                if (text.length() == 1)
                    editText[1].requestFocus();
                break;
            case R.id.et_otpBox2:

                if (text.length() == 1)
                    editText[2].requestFocus();
                else if (text.length() == 0)
                    editText[0].requestFocus();
                break;
            case R.id.et_otpBox3:
                if (text.length() == 1)
                    editText[3].requestFocus();
                else if (text.length() == 0)
                    editText[1].requestFocus();
                break;
            case R.id.et_otpBox4:
                if (text.length() == 0)
                    editText[2].requestFocus();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
    }

    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
    }
}

