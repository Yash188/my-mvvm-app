package com.ygdevelopers.mymvvmapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void Insert(Note note);

    @Delete
    void  Delete(Note note);

    @Update
    void  Update(Note note);

    @Query("Delete From note_table")
    void DeleteAllNotes();

    @Query("Select * From note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();
}
