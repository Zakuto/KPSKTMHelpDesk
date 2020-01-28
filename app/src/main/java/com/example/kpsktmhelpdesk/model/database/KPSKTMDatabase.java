package com.example.kpsktmhelpdesk.model.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.kpsktmhelpdesk.model.dao.ClientDAO;
import com.example.kpsktmhelpdesk.model.entity.Client;

import java.text.SimpleDateFormat;
import java.util.Date;

@Database(entities = {Client.class},version = 1)
public abstract class KPSKTMDatabase extends RoomDatabase {

    private static KPSKTMDatabase instance;


    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static Date date = new Date();
    private static String currentDate = formatter.format(date);

    public abstract ClientDAO clientDAO();

    public static synchronized KPSKTMDatabase getInstance(Context c){
        if(instance == null){
            instance = Room.databaseBuilder(c.getApplicationContext(),KPSKTMDatabase.class,
                    "kpsktm_database")
                    .fallbackToDestructiveMigration()
                    .addCallback((roomCallback))
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(instance).execute();
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void,Void,Void>{
        private final ClientDAO clientDAO;
        private PopulateAsyncTask(KPSKTMDatabase database){
            clientDAO = database.clientDAO();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            clientDAO.deleteAllClient();
            return null;
        }
    }
}
