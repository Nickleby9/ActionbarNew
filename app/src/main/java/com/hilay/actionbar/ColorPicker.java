package com.hilay.actionbar;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ColorPicker.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ColorPicker extends Fragment implements SeekBar.OnSeekBarChangeListener, TextWatcher, View.OnFocusChangeListener, View.OnClickListener {

    private SeekBar sbRed, sbGreen, sbBlue;
    private int r = 0, g = 0, b = 0;
    private boolean isChanging = false;
    private EditText etRed, etGreen, etBlue;
    private Button btnOK, btnCancel, btnRandom;

    private OnFragmentInteractionListener mListener;

    public ColorPicker() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color_picker, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sbRed = (SeekBar) view.findViewById(R.id.sbRed);
        sbGreen = (SeekBar) view.findViewById(R.id.sbGreen);
        sbBlue = (SeekBar) view.findViewById(R.id.sbBlue);

        sbRed.setOnSeekBarChangeListener(this);
        sbGreen.setOnSeekBarChangeListener(this);
        sbBlue.setOnSeekBarChangeListener(this);

        etRed = (EditText) view.findViewById(R.id.etRed);
        etGreen = (EditText) view.findViewById(R.id.etGreen);
        etBlue = (EditText) view.findViewById(R.id.etBlue);

        etRed.addTextChangedListener(this);
        etGreen.addTextChangedListener(this);
        etBlue.addTextChangedListener(this);

        etRed.setOnFocusChangeListener(this);
        etGreen.setOnFocusChangeListener(this);
        etBlue.setOnFocusChangeListener(this);

        btnOK = (Button) view.findViewById(R.id.btnOK);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnRandom = (Button) view.findViewById(R.id.btnRandom);

        btnOK.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnRandom.setOnClickListener(this);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        try {
            if (isChanging) {
                TextView tvTextColor = (TextView) getView().findViewById(R.id.tvTextColor);
                r = sbRed.getProgress();
                etRed.setText(String.valueOf(r));
                g = sbGreen.getProgress();
                etGreen.setText(String.valueOf(g));
                b = sbBlue.getProgress();
                etBlue.setText(String.valueOf(b));
                tvTextColor.setText("#" + (Integer.toHexString(Color.rgb(r, g, b))));
                tvTextColor.setBackgroundColor(Color.rgb(r, g, b));
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        isChanging = true;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        isChanging = false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        try {
            if (!isChanging) {
                sbRed.setProgress(Integer.parseInt(etRed.getText().toString()));
                sbGreen.setProgress(Integer.parseInt(etGreen.getText().toString()));
                sbBlue.setProgress(Integer.parseInt(etBlue.getText().toString()));
                TextView tvTextColor = (TextView) getView().findViewById(R.id.tvTextColor);
                r = sbRed.getProgress();
                g = sbGreen.getProgress();
                b = sbBlue.getProgress();
                tvTextColor.setText("#" + (Integer.toHexString(Color.rgb(r, g, b))));
//                ColorPicker.OnFragmentInteractionListener(Color(r,g,b));
//                tvTextColor.setBackgroundColor(Color.rgb(r, g, b));
            }
        } catch (NumberFormatException e) {

        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            if (v.getId() == R.id.btnOK) {
                mListener.onFragmentInteraction(Color.rgb(r, g, b));
            }
            if (v.getId() == R.id.btnRandom){
                Random rand = new Random();
                mListener.onFragmentInteraction(Color.rgb(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)));
            }
            if (v.getId() == R.id.btnCancel){

            }
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int color);
    }
}
