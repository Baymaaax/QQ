package com.example.nyt.qq;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


@SuppressLint("ValidFragment")
public class RenameDialogFragment extends DialogFragment {
    @SuppressLint("ValidFragment")
    private int groupPosition;
    private int childPosition;
    private String[] groupNames;
    private String[][] contactNickNames;
    private int[][] contactAvatars;
    private ContactListAdapter contactListAdapter;

    public RenameDialogFragment(int groupPositon, String[] groupNames, String[][] contactNickNames,
                                ContactListAdapter contactListAdapter) {
        this.groupPosition = groupPositon;
        this.groupNames = groupNames;
        this.contactNickNames = contactNickNames;
        this.contactListAdapter = contactListAdapter;
        this.childPosition = -1;
    }

    public RenameDialogFragment(int groupPositon, int childPosition, String[] groupNames,
                                String[][] contactNickNames, ContactListAdapter contactListAdapter) {
        this.groupPosition = groupPositon;
        this.childPosition = childPosition;
        this.groupNames = groupNames;
        this.contactListAdapter = contactListAdapter;
        this.contactNickNames = contactNickNames;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rename_dialog, null);
        final EditText newName = (EditText) view.findViewById(R.id.new_name);
        Button okButton = (Button) view.findViewById(R.id.rename_ok);
        Button cancleButton = (Button) view.findViewById(R.id.rename_cancel);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childPosition == -1) {
                    groupNames[groupPosition] = newName.getText().toString();
                } else {
                    contactNickNames[groupPosition][childPosition] = newName.getText().toString();
                }
                contactListAdapter.notifyDataSetChanged();
                RenameDialogFragment.this.dismiss();
            }
        });
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RenameDialogFragment.this.dismiss();

            }
        });
        return view;
    }

    public interface DialogDismiss {
        void shutDown();
    }

    private DialogDismiss dialogDismiss;

}
