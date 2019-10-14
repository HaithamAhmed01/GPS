package com.example.gps.dataBase.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gps.Model.Note;

import java.util.List;

@Dao
public interface NotesDao {
    @Insert
    void addNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("delete from Note where id=:id")
    void deleteNoteById(int id);

    @Query("select * from Note")
    List<Note> getAllNote();
}