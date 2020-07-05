package com.michaelmagdy.patientapp.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.michaelmagdy.patientapp.R;

import static android.content.Context.MODE_PRIVATE;
import static com.michaelmagdy.patientapp.ui.home.HomeFragment.CURRENT_PREF_KEY;

public class SettingsFragment extends Fragment {

    public static final String MY_PREFS_NAME = "PatientSharedPref";
    public static final String USERNAME_PREF_KEY = "username";
    public static final String MAX_PREF_KEY = "maxNum";
    private SettingsViewModel galleryViewModel;
    private EditText usernameEdt, maxNumEdt,currentNumEdt;
    private int maxNum = 5;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        usernameEdt = root.findViewById(R.id.username_edt);
        maxNumEdt = root.findViewById(R.id.max_num_edt);
        currentNumEdt = root.findViewById(R.id.current_num_edt);

        getSavedValues();

        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameStr = usernameEdt.getText().toString();
                String maxNumSrt = maxNumEdt.getText().toString();
                SharedPreferences.Editor editor = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                if (!usernameStr.isEmpty()){
                    editor.putString(USERNAME_PREF_KEY, usernameStr);
                    editor.apply();
                }
                if (!maxNumSrt.isEmpty()){
                    editor.putInt(MAX_PREF_KEY, Integer.valueOf(maxNumSrt));
                    editor.apply();
                }
                Toast.makeText(getContext(), "saved successfully", Toast.LENGTH_SHORT).show();

            }
        });

        return root;
    }

    private void getSavedValues(){

        SharedPreferences prefs = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        if (prefs.contains(USERNAME_PREF_KEY)) {
            String username = prefs.getString(USERNAME_PREF_KEY, null);
            usernameEdt.setText(username);
        }
        if (prefs.contains(MAX_PREF_KEY)) {
            maxNum = prefs.getInt(MAX_PREF_KEY, 5);
            maxNumEdt.setText(String.valueOf(maxNum));
        } else {
            maxNumEdt.setText(String.valueOf(maxNum));
        }
        if (prefs.contains(CURRENT_PREF_KEY)) {
            currentNumEdt.setText(String.valueOf(prefs.getInt(CURRENT_PREF_KEY, 0)));
        }
    }
}