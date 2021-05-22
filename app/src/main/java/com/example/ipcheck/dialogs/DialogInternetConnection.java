package com.example.ipcheck.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;


public class DialogInternetConnection extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle("Отсутствует подключение к сети").setMessage("Попробуйте еще раз")
                .setPositiveButton("Ok", null)
                .create();
    }
}
