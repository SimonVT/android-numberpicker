
package net.simonvt.numberpicker.samples;

import net.simonvt.numberpicker.NumberPicker;
import android.app.Activity;
import android.os.Bundle;

/**
 * @author Simon Vig Therkildsen <simonvt@gmail.com>
 */
public class DarkThemeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dark);

        NumberPicker np = (NumberPicker) findViewById(R.id.numberPicker);
        np.setFocusable(true);
        np.setFocusableInTouchMode(true);
    }
}
