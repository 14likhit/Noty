package com.likhit.noty.ui.notes;

import android.os.Bundle;

import com.likhit.noty.R;
import com.likhit.noty.base.BaseActivity;
import com.likhit.noty.data.models.Note;
import com.likhit.noty.utils.AppConstants;

public class NoteActivity extends BaseActivity {

    private static final String TAG = "NoteActivity";

    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setupToolbar(getString(R.string.app_name), true);
        if (getIntent().getExtras() != null) {
            note = (Note) getIntent().getSerializableExtra(AppConstants.BUNDLE_KEY_NOTE);
        }
        if (note != null) {
            replaceFragment(NoteFragment.newInstance(AppConstants.NOTE_MODE_EDIT, note), NoteFragment.TAG, false);
        } else {
            replaceFragment(NoteFragment.newInstance(AppConstants.NOTE_MODE_NEW, null), NoteFragment.TAG, false);
        }
    }
}
