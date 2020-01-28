package com.example.kpsktmhelpdesk.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.kpsktmhelpdesk.model.entity.Client;

import java.util.List;

@Dao
public interface ClientDAO {

    @Insert
    void insert(Client client);

    @Update
    void update(Client client);

    @Delete
    void delete(Client client);

    @Query("DELETE FROM client_table")
    void deleteAllClient();

    @Query("SELECT * FROM client_table")
    LiveData<List<Client>> getAllClients();
}
