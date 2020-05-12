package com.hfad.notetaker;

import java.util.UUID;

public interface SendData {
    public void passData(String title, String text, UUID noteId);
}
