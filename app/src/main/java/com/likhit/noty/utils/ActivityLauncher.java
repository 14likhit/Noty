package com.likhit.noty.utils;

import android.app.Activity;
import android.content.Intent;

import com.likhit.noty.data.models.Note;
import com.likhit.noty.ui.home.HomeActivity;
import com.likhit.noty.ui.notes.NoteActivity;

public class ActivityLauncher {

    public static void launchHomeActivity(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }

    public static void launchNoteActivity(Activity activity, Note note) {
        Intent intent = new Intent(activity, NoteActivity.class);
        intent.putExtra(AppConstants.BUNDLE_KEY_NOTE, note);
        activity.startActivity(intent);
    }

}
