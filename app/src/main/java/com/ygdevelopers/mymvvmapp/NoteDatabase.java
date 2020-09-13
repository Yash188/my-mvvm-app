package com.ygdevelopers.mymvvmapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public static  NoteDatabase instance;

    public  abstract NoteDao noteDao();

    public  static  synchronized NoteDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDatabase(instance).execute();
        }
    };

    private static  class PopulateDatabase extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        private PopulateDatabase(NoteDatabase noteDatabase){
            noteDao = noteDatabase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.Insert(new Note("title 1","desc 1 ",1));
            noteDao.Insert(new Note("title 2","desc 2 ",2));
            noteDao.Insert(new Note("title 3","desc 3 ",3));
            return null;
        }
    }
}
