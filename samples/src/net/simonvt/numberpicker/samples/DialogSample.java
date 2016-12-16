package net.simonvt.numberpicker.samples;

import net.simonvt.numberpicker.NumberPicker;
import net.simonvt.numberpicker.NumberPickerDialog;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DialogSample extends Activity {

    private TextView mNumberDisplay;

    private int mValue;

    static final int NUMBER_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        mNumberDisplay = (TextView) findViewById(R.id.numberDisplay);

        findViewById(R.id.btnDialog).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(NUMBER_DIALOG_ID);
            }
        });

        updateDisplay();
    }

    // updates the time we display in the TextView
    private void updateDisplay() {
        mNumberDisplay.setText(String.valueOf(mValue));
    }

    // the callback received when the user "sets" the number in the dialog
    private NumberPickerDialog.OnNumberSetListener mNumberSetListener = 
    		new NumberPickerDialog.OnNumberSetListener() {
				@Override
				public void onNumberSet(NumberPicker picker, int value) {
					mValue = value;
					updateDisplay();					
				}
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case NUMBER_DIALOG_ID:
            	return new NumberPickerDialog(this, mNumberSetListener, 0, 0, 20);             
        }
        return null;
    }
}
