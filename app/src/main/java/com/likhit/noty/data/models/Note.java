package com.likhit.noty.data.models;

import java.util.Date;

public class Note {

    private String note_title;

    private String note_content;

    private Date note_created;

    public Note(String note_title) {
        this.note_title = note_title;
        this.note_created = new Date(System.currentTimeMillis());
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_content() {
        return note_content;
    }

    public void setNote_content(String note_content) {
        this.note_content = note_content;
    }

    public Date getNote_created() {
        return note_created;
    }

    public void setNote_created(Date note_created) {
        this.note_created = note_created;
    }
}
