package com.ygdevelopers.mymvvmapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    public void InsertNote(Note note){
        noteRepository.Insert(note);
    }

    public void DeleteNote(Note note){
        noteRepository.Delete(note);
    }

    public void UpdateNote(Note note){
        noteRepository.Update(note);
    }

    public void DeleteAllNote(){
        noteRepository.DeleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }
}
