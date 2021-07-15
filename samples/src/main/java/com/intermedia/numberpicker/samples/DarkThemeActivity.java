package com.intermedia.numberpicker.samples;

import android.app.Activity;
import android.os.Bundle;

import com.intermedia.numberpicker.NumberPicker;

/**
 * @author Simon Vig Therkildsen <simonvt@gmail.com>
 */
public class DarkThemeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dark);

        NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);
        np.setMaxValue(20);
        np.setMinValue(0);
        np.setFocusable(true);
        np.setFocusableInTouchMode(true);
    }
}
