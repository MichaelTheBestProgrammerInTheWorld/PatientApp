package com.michaelmagdy.patientapp.ui.home;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.michaelmagdy.patientapp.PatientModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ArrayList<PatientModel> patientArrayList
            = new ArrayList<>();
    String[] patientRows;
    private MutableLiveData<String[]> countLiveData=new MutableLiveData<>();

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<String[]> getPatientsCount() {

        return countLiveData;
    }

    public void addPatient(String fullname, String sex, int age, String email) {

        char gender;
        if (sex.equals("Male")){
            gender = 'M';
        } else if (sex.equals("Female")){
            gender = 'F';
        } else {
            gender = 'O';
        }
        PatientModel patientModel = new PatientModel(
                fullname, email, gender, age);
        patientArrayList.add(patientModel);
        patientRows = new String[patientArrayList.size()];
        for (int index = 0; index < patientArrayList.size();index++){
            patientRows[index] = patientArrayList.get(index).getFullname()
            + " " + patientArrayList.get(index).getGender() + " " +
             String.valueOf(patientArrayList.get(index).getAge());
        }
        countLiveData.setValue(patientRows);

    }

    public void clearData(){
        patientArrayList.clear();
        patientRows = new String[0];
        countLiveData.setValue(patientRows);
    }
}