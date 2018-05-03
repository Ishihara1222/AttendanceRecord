package com.robop.attendancerecord;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v4.app.DialogFragment;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class EditSubjectNameDialog extends DialogFragment {

    Realm realm;
    private int position;
    View view;

    public static EditSubjectNameDialog newInstance(int position){
        EditSubjectNameDialog editSubjectNameDialog = new EditSubjectNameDialog();

        Bundle data = new Bundle();
        data.putInt("position", position);
        editSubjectNameDialog.setArguments(data);

        return editSubjectNameDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        realm = Realm.getInstance(realmConfiguration);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        view = getActivity().getLayoutInflater().inflate(R.layout.activity_subjectdialog,null);

        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       EditSubjectNameDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }

}