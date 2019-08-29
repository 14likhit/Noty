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

import java.util.ArrayList;
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
        binding.noteListRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), RecyclerView.VERTICAL, false));
        binding.noteListRecyclerView.setAdapter(adapter);

        if (noteList == null || noteList.size() == 0) {
            binding.noteListRecyclerView.setVisibility(View.GONE);
            binding.noteMessageTextView.setVisibility(View.VISIBLE);
            binding.addNoteButton.setVisibility(View.VISIBLE);
        } else {
            binding.noteMessageTextView.setVisibility(View.GONE);
            binding.addNoteButton.setVisibility(View.GONE);
            binding.noteListRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(Note item, int position, View view) {

    }
}
