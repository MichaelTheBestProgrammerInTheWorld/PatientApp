package com.michaelmagdy.patientapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.michaelmagdy.patientapp.MainActivity;
import com.michaelmagdy.patientapp.PatientModel;
import com.michaelmagdy.patientapp.R;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        final EditText nameEdt = root.findViewById(R.id.name_edt);
        final EditText ageEdt = root.findViewById(R.id.age_edt);
        final EditText emailEdt = root.findViewById(R.id.email_edt);
        final RadioGroup sexGroup = root.findViewById(R.id.radio_group);
        final ListView patientList = root.findViewById(R.id.patient_list);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        homeViewModel.getPatientsCount().observe(getViewLifecycleOwner(), new Observer<String[]>() {
            @Override
            public void onChanged(String[] strings) {
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, strings);
                patientList.setAdapter(arrayAdapter);
            }
        });

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameStr = nameEdt.getText().toString();
                String ageStr = ageEdt.getText().toString();
                String emailStr = emailEdt.getText().toString();
                RadioButton radioButton = root.findViewById(sexGroup.getCheckedRadioButtonId());
                String sexStr = radioButton.getText().toString();
                homeViewModel.addPatient(nameStr, sexStr, Integer.parseInt(ageStr), emailStr);

            }
        });

        return root;
    }
}