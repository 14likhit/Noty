package com.likhit.noty.ui.notes;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.likhit.noty.R;
import com.likhit.noty.base.BaseFragment;
import com.likhit.noty.custom.OnItemClickListener;
import com.likhit.noty.data.models.Note;
import com.likhit.noty.databinding.FragmentNoteListBinding;
import com.likhit.noty.utils.ActivityLauncher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteListFragment extends BaseFragment implements OnItemClickListener<Note> {

    public static final String TAG = "NoteListFragment";

    private FragmentNoteListBinding binding;
    private List<Note> noteList;
    private NoteListAdapter adapter;

    public static NoteListFragment newInstance() {
        return new NoteListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNoteListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);

        if (noteList == null) {
            noteList = new ArrayList<>();
        }

        if (adapter == null) {
            adapter = new NoteListAdapter(this);
        }
        prepareNotes();

        if (noteList == null || noteList.size() == 0) {
            binding.noteListRecyclerView.setVisibility(View.GONE);
            binding.noteMessageImageView.setVisibility(View.VISIBLE);
            binding.noteMessageTextView.setVisibility(View.VISIBLE);
        } else {
            binding.noteMessageImageView.setVisibility(View.GONE);
            binding.noteMessageTextView.setVisibility(View.GONE);
            adapter.setNoteList(noteList);
            binding.noteListRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), RecyclerView.VERTICAL, false));
            binding.noteListRecyclerView.setAdapter(adapter);
            binding.noteListRecyclerView.setVisibility(View.VISIBLE);
        }

        binding.addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityLauncher.launchNoteActivity(getBaseActivity(), null);
            }
        });
    }

    /**
     * Method to read all files for the app and convert them to note lists.
     */
    private void prepareNotes() {
        File directory;
        directory = getBaseActivity().getFilesDir();
        File[] files = directory.listFiles();
        String theFile;
        for (int f = 1; f <= files.length; f++) {
            theFile = files[f - 1].getName();
            Note note = new Note();
            note.setNoteTitle(theFile);
            note.setNoteCreated(new Date(files[f - 1].lastModified()));
            note.setNoteContent(openNote(theFile));
            noteList.add(note);
        }
    }

    /**
     * Method to convert files to Note object
     *
     * @param fileName->filename
     * @return -> returns note content.
     */
    private String openNote(String fileName) {
        String content = "";
        try {
            InputStream in = getBaseActivity().openFileInput(fileName);
            if (in != null) {
                InputStreamReader tmp = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(tmp);
                String str;
                StringBuilder buf = new StringBuilder();
                while ((str = reader.readLine()) != null) {
                    buf.append(str).append("\n");
                }
                in.close();
                content = buf.toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    @Override
    public void onItemClick(Note item, int position, View view) {
        ActivityLauncher.launchNoteActivity(getBaseActivity(), item);
    }
}
