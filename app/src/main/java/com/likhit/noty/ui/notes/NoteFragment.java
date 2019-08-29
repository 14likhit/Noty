package com.likhit.noty.ui.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.likhit.noty.base.BaseFragment;
import com.likhit.noty.data.models.Note;
import com.likhit.noty.databinding.FragmentNoteBinding;
import com.likhit.noty.utils.AppConstants;

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
                note.setNoteTitle(binding.noteTitleTextView.getText().toString());
                note.setNoteContent(binding.noteContentTextView.getText().toString());
            }
        });
    }
}
