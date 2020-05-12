package com.hfad.notetaker;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoteBuilder {

    private List<Note> mNotes = new ArrayList<>();
    private Note mNote;

    NoteBuilder(){
        mNote = new Note();
        mNote.setNoteText("Text");
        mNote.setNoteTitle("Title");
    }



    public Note getNote(){
        return mNote;
    }


    public static Note getNote(UUID noteId, List<Note> notes){
        for (Note note : notes) {
            if (note.getNoteId().equals(noteId)) {
                return note;
            }
        }
        return null;
    }
}
