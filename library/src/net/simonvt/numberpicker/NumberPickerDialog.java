/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.simonvt.numberpicker;

import net.simonvt.numberpicker.NumberPicker.OnValueChangeListener;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * A dialog that prompts the user for a number {@link NumberPicker}.
 *
 * <p>See the <a href="{@docRoot}guide/topics/ui/controls/pickers.html">Pickers</a>
 * guide.</p>
 */
public class NumberPickerDialog extends AlertDialog
        implements OnClickListener, OnValueChangeListener {

    /**
     * The callback interface used to indicate the user is done filling in
     * the number (they clicked on the 'Set' button).
     */
    public interface OnNumberSetListener {

        /**
         * Called upon a change of the current value.
         *
         * @param picker The NumberPicker associated with this listener.
         * @param newVal The new value.
         */
        void onNumberSet(NumberPicker picker, int value);
    }

    private static final String VALUE = "value";

    private final NumberPicker mNumberPicker;
    private final OnNumberSetListener mCallback;

    /**
     * @param context Parent.
     * @param callBack How parent is notified.
     * @param value The initial value.
     * @param valueMin The minimum value.
     * @param valueMax The maximum value.
     */
    public NumberPickerDialog(Context context,
            OnNumberSetListener callBack,
            int value, 
            int valueMin, 
            int valueMax) {
        this(context, Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB ? R.style.Theme_Dialog_Alert : 0, callBack, value, valueMin, valueMax);
    }

    /**
     * @param context Parent.
     * @param theme the theme to apply to this dialog
     * @param callBack How parent is notified.
     * @param value The initial value.
     * @param valueMin The minimum value.
     * @param valueMax The maximum value.
     */
    public NumberPickerDialog(Context context,
            int theme,
            OnNumberSetListener callBack,
            int value, 
            int valueMin, 
            int valueMax) {
        super(context, theme);
        mCallback = callBack;
        setIcon(0);
        setTitle(R.string.number_picker_dialog_title);

        Context themeContext = getContext();
        setButton(BUTTON_POSITIVE, themeContext.getText(R.string.number_done), this);

        LayoutInflater inflater =
                (LayoutInflater) themeContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.number_picker_dialog, null);
        setView(view);

        // initialize state
        mNumberPicker = (NumberPicker) view.findViewById(R.id.number);
        mNumberPicker.setMinValue(valueMin);
        mNumberPicker.setMaxValue(valueMax);
        mNumberPicker.setValue(value);
        mNumberPicker.setOnValueChangedListener(this);
    }

    public void onClick(DialogInterface dialog, int which) {
        tryNotifyNumberSet();
    }

    public void updateNumber(int value) {
    	mNumberPicker.setValue(value);
    }

	@Override
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {}
	
    private void tryNotifyNumberSet() {
        if (mCallback != null) {
            mNumberPicker.clearFocus();
            mCallback.onNumberSet(mNumberPicker, mNumberPicker.getValue());
        }
    }

    @Override
    protected void onStop() {
    	tryNotifyNumberSet();
        super.onStop();
    }

    @Override
    public Bundle onSaveInstanceState() {
        Bundle state = super.onSaveInstanceState();
		state.putInt(VALUE, mNumberPicker.getValue());
        return state;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int value = savedInstanceState.getInt(VALUE);
		mNumberPicker.setValue(value);
    }

}
