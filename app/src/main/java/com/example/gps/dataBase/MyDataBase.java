package com.example.gps.dataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gps.Model.Note;
import com.example.gps.dataBase.Daos.NotesDao;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {
    public static final String DB_Name = "Note database";
    private static MyDataBase dataBase;

    public static MyDataBase getInstance(Context context) {
        if (dataBase == null) {
            dataBase = Room.databaseBuilder(context
                    , MyDataBase.class, DB_Name)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return dataBase;
    }

    public abstract NotesDao notesDao();
}
