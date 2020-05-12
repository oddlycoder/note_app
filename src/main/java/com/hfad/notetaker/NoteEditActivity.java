package com.hfad.notetaker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class NoteEditActivity extends AppCompatActivity  {

    private String mTitle;
    private String mText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);



        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.edit_note_fragment);

        if (fragment == null){
            fragment = new NoteEditFragment();
            fm.beginTransaction().add(R.id.edit_note_fragment, fragment).commit();
        }



        Intent returnIntent = new Intent();
        returnIntent.putExtra("title",mTitle);
        returnIntent.putExtra("text",mText);
        setResult(Activity.RESULT_OK, returnIntent);





    }

}
