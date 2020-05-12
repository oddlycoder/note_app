package com.hfad.notetaker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NoteEditFragment extends Fragment {

    private EditText mTitleEditText;
    private EditText mTextEditText;

    private static final String ARGS_NOTE_ID = "NOTEID";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_edit, container, false);
        mTitleEditText = view.findViewById(R.id.text_edit);
        mTextEditText = view.findViewById(R.id.title_edit);


        String title = mTitleEditText.getText().toString();
        String text = mTextEditText.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("noteTitle", title);
        getActivity().setResult(Activity.RESULT_OK, intent);



        return view;
    }

}
