package com.likhit.noty.ui.home;

import android.os.Bundle;

import com.likhit.noty.R;
import com.likhit.noty.base.BaseActivity;
import com.likhit.noty.ui.notes.NoteListFragment;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupToolbar(getString(R.string.app_name), false);
        replaceFragment(NoteListFragment.newInstance(), NoteListFragment.TAG, false);
    }
}
