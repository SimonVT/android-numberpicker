package net.simonvt.numberpicker.samples;

import android.app.Activity;
import android.os.Bundle;

import net.simonvt.numberpicker.NumberPicker;

/**
 * @author Simon Vig Therkildsen <simonvt@gmail.com>
 */
public class AppCompatThemeActivity extends Activity {

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
