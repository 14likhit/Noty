package com.likhit.noty.ui.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.likhit.noty.R;
import com.likhit.noty.custom.OnItemClickListener;
import com.likhit.noty.data.models.Note;
import com.likhit.noty.databinding.ItemNoteBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder> {


    private List<Note> noteList;

    private OnItemClickListener<Note> onItemClickListener;

    private LayoutInflater layoutInflater;

    public NoteListAdapter(OnItemClickListener<Note> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        return new NoteListAdapter.NoteListViewHolder(layoutInflater.inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteListViewHolder holder, int position) {
        final Note note = noteList.get(position);

        holder.binding.noteTitleTextView.setText(note.getNoteTitle());
        Date date = note.getNoteCreated();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        holder.binding.noteDateTextView.setText(formatter.format(date));
        holder.binding.noteItemLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(note, holder.getAdapterPosition(), v);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (noteList != null && noteList.size() > 0) {
            return noteList.size();
        }
        return 0;
    }

    class NoteListViewHolder extends RecyclerView.ViewHolder {
        private ItemNoteBinding binding;

        NoteListViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
