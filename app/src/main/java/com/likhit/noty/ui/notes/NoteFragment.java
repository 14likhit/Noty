package com.likhit.noty.ui.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.likhit.noty.R;
import com.likhit.noty.base.BaseFragment;
import com.likhit.noty.data.models.Note;
import com.likhit.noty.databinding.FragmentNoteBinding;
import com.likhit.noty.utils.ActivityLauncher;
import com.likhit.noty.utils.AppConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class NoteFragment extends BaseFragment {
    public static final String TAG = "NoteFragment";

    private FragmentNoteBinding binding;

    private Note note;
    private int noteMode;

    public static NoteFragment newInstance(int noteMode, Note note) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();
        args.putInt(AppConstants.BUNDLE_KEY_NOTE_MODE, noteMode);
        args.putSerializable(AppConstants.BUNDLE_KEY_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            noteMode = getArguments().getInt(AppConstants.BUNDLE_KEY_NOTE_MODE);
            if (noteMode == AppConstants.NOTE_MODE_EDIT) {
                note = (Note) getArguments().getSerializable(AppConstants.BUNDLE_KEY_NOTE);
            } else {
                note = new Note();
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        if (noteMode == AppConstants.NOTE_MODE_EDIT) {
            binding.noteTitleTextView.setText(note.getNoteTitle());
            binding.noteContentTextView.setText(note.getNoteContent());
        }
        binding.saveNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean title_changed = false;
                String previous_title = null;
                if (noteMode == AppConstants.NOTE_MODE_EDIT && !note.getNoteTitle().equals(binding.noteTitleTextView.toString())) {
                    title_changed = true;
                    previous_title = note.getNoteTitle();
                }
                note.setNoteTitle(binding.noteTitleTextView.getText().toString());
                note.setNoteContent(binding.noteContentTextView.getText().toString());
                saveNote(note, title_changed, previous_title);
                ActivityLauncher.launchHomeActivity(getBaseActivity());
            }
        });
    }

    /**
     * Method to save the note and rename the existing note
     * @param note -> Note Object
     * @param title_changed -> If title changed hence rename file
     * @param previous_title -> previous title.
     */
    private void saveNote(Note note, boolean title_changed, String previous_title) {
        try {
            if (title_changed) {
                File directory = getBaseActivity().getFilesDir();
                File from = new File(directory, previous_title);
                File to = new File(directory, note.getNoteTitle());
                if (from.exists()) {
                    from.renameTo(to);
                }
            }
            OutputStreamWriter out = new OutputStreamWriter(getBaseActivity().openFileOutput(note.getNoteTitle(), 0));
            out.write(note.getNoteContent());
            out.close();
            showMessage(R.string.note_save_successful);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            showMessage(R.string.note_save_unsuccesful);
        } catch (IOException e) {
            e.printStackTrace();
            showMessage(R.string.note_save_unsuccesful);
        }
    }

}
