package com.robop.attendancerecord;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;

public class AlertDialogFragment extends DialogFragment {

    Realm realm;
    private int position;
    View view;

    public AlertDialogFragment(int clickPosition){
        this.position = clickPosition;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Realm.init(getActivity());
        realm = Realm.getDefaultInstance();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //xmlと紐づけ
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.activity_subjectdialog,null);

        builder.setView(inflater.inflate(R.layout.activity_subjectdialog, null))

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        setSubjectName(realm);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       AlertDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();

    }

    private void setSubjectName(Realm realm){
        EditText editText = (EditText)view.findViewById(R.id.customDlg_name);
        final String subjectName = editText.getText().toString();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SubjectRealmData subjectRealmData = realm.where(SubjectRealmData.class).equalTo("classId", position).findFirst();

                //subjectRealmData.setSubjectName(subjectName);
            }
        });
    }

}