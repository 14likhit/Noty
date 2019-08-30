package com.likhit.noty.data.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Model class for note.
 */

public class Note implements Serializable {

    private String noteTitle;

    private String noteContent;

    private Date noteCreated;

    public Note() {
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public Date getNoteCreated() {
        return noteCreated;
    }

    public void setNoteCreated(Date noteCreated) {
        this.noteCreated = noteCreated;
    }
}
