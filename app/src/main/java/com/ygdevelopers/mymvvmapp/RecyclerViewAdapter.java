package com.ygdevelopers.mymvvmapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<Note> allNotes = new ArrayList<>();

    public RecyclerViewAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(allNotes.get(position).getTitle());
        holder.desc.setText(allNotes.get(position).getDescription());
        holder.priority.setText(allNotes.get(position).getPriority()+"");
    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public void setNotes(List<Note> notes){
        allNotes = notes;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,priority,desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_main_text);
            priority = itemView.findViewById(R.id.note_priority);
            desc = itemView.findViewById(R.id.note_description);
        }
    }
}
