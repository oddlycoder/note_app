package com.hfad.notetaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NoteListFragment extends Fragment {
    private List<Note> mNotes = new ArrayList<>();

    private RecyclerView mNoteRecyclerView;
    private NoteAdapter mAdapter;

    private FloatingActionButton floatingActionButton;

    private static final String ARGS_TITLE = "com.hfad.notetaker.args_title";
    private static final String ARGS_TEXT = "com.hfad.notetaker.args_text";
    private static final String ARGS_NOTE_ID = "com.hfad.notetaker.args.text";

    private String mTitle;
    private String mText;
    private int mPosition;


    public static NoteListFragment newInstance(String title, String text, UUID noteId) {
        Bundle args = new Bundle();
        args.putString(ARGS_TITLE, title);
        args.putString(ARGS_TEXT, text);
        args.putSerializable(ARGS_NOTE_ID, noteId);
        NoteListFragment fragment = new NoteListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("FracgmentOnActivityRe","OnActivityResult called");
        if (data != null){
            Log.d("nullData", "Data is null");
        }
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            mTitle = data.getStringExtra("noteTitle");
            mText = data.getStringExtra("text");
            if (mTitle != null){
                Log.d("mTitle","Title is null");
            }
            updateNoteCard(mTitle, mText);
        }
    }

    //    @Override
//    public void onResume() {
//        super.onResume();
////        String title = getArguments().getString(ARGS_TITLE);
////        String text = getArguments().getString((ARGS_TEXT));
//        UUID noteId = (UUID) getArguments().getSerializable(ARGS_NOTE_ID);
//        Note note = new NoteBuilder().getNote(noteId, mNotes);
//        note.setNoteTitle("ssss");
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        mNoteRecyclerView = view.findViewById(R.id.recycler_view);
        floatingActionButton = view.findViewById(R.id.fab);

        mNoteRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d("onCreateView", "on Create View called.");
        NoteBuilder noteBuilder = new NoteBuilder();
        mNotes.add(noteBuilder.getNote());
        mAdapter = new NoteAdapter(mNotes);
        mNoteRecyclerView.setAdapter(mAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteBuilder noteBuilder1 = new NoteBuilder();
                Note note = noteBuilder1.getNote();
                mNotes.add(note);
                mAdapter.notifyItemInserted(mNotes.size());
                mNoteRecyclerView.smoothScrollToPosition(mNotes.size());
            }
        });


        return view;
    }

    public void updateNoteCard(String title, String text){
        if (title.equals("")){
            Log.d("methodTitle", "method Title not null");
        }
        Note note = mNotes.get(mPosition);
        note.setNoteTitle(title);
        note.setNoteText(text);
        mAdapter.notifyItemChanged(mPosition);

    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mNoteTitle;
        private TextView mNoteText;
        private Note mNote;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNoteTitle = itemView.findViewById(R.id.note_title);
            mNoteText = itemView.findViewById(R.id.note_text);

        }


        public void updateUI(Note note){
            mNote = note;
            mNoteText.setText(mNote.getNoteText());
            mNoteTitle.setText(mNote.getNoteTitle());
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), NoteEditActivity.class);
            mPosition = getLayoutPosition();
            startActivityForResult(intent,1);
        }
    }


    public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
        private List<Note> mNotes;

        public NoteAdapter(List<Note> notes){
            mNotes = notes;
        }

        @NonNull
        @Override
        public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_note, parent, false);
            NoteViewHolder noteViewHolder = new NoteViewHolder(v);
            return noteViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
            Log.d("Adapter","onBindViewHolder called");
            Note note = mNotes.get(position);
            holder.updateUI(note);

        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }
    }

}
