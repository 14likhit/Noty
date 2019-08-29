package com.likhit.noty.ui.notes;

import android.os.Bundle;

import com.likhit.noty.R;
import com.likhit.noty.base.BaseActivity;

public class NoteActivity extends BaseActivity {

    private static final String TAG = "NoteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }
}
