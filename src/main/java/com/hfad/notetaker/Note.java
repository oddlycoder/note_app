package com.hfad.notetaker;

import java.util.UUID;

public class Note {

    private String mNoteTitle;
    private UUID mNoteId;
    private String mNoteText;

    Note(){
        mNoteId = UUID.randomUUID();
    }

    public UUID getNoteId(){
        return mNoteId;
    }

    public String getNoteTitle() {
        return mNoteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        mNoteTitle = noteTitle;
    }

    public String getNoteText() {
        return mNoteText;
    }

    public void setNoteText(String noteText) {
        mNoteText = noteText;
    }
}
