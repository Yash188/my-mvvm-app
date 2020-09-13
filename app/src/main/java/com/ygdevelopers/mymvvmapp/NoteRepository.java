package com.ygdevelopers.mymvvmapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void Insert(Note note){
        new InsertNote(noteDao).execute(note);
    }

    public void Update(Note note){
        new UpdateNote(noteDao).execute(note);
    }

    public void Delete(Note note){
        new DeleteNote(noteDao).execute(note);
    }

    public void DeleteAllNotes(){
        new DeleteAllNote(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    public static class InsertNote extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        private InsertNote(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.Insert(notes[0]);
            return null;
        }
    }

    public static class DeleteAllNote extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        private DeleteAllNote(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.DeleteAllNotes();
            return null;
        }
    }

    public static class UpdateNote extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        private UpdateNote(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.Update(notes[0]);
            return null;
        }
    }

    public static class DeleteNote extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        private DeleteNote(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.Delete(notes[0]);
            return null;
        }
    }
}
