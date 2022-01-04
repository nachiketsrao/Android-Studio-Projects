package com.company.notexpress;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Note.class, version = 1) //annotation declares this class as a database class
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext()
            , NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(instance).execute();

        }
    };

    // populating the database with some data beforehand :

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
    {

        private NoteDao noteDao;

        private PopulateDbAsyncTask(NoteDatabase database)
        {
            noteDao = database.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDao.insert(new Note("Title 1", "Description 1"));
            noteDao.insert(new Note("Title 2", "Description 2"));
            noteDao.insert(new Note("Title 3", "Description 3"));

            return null;
        }
    }
}
