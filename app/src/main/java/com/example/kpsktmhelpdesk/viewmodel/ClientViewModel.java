package com.example.kpsktmhelpdesk.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.kpsktmhelpdesk.model.entity.Client;
import com.example.kpsktmhelpdesk.model.repository.ClientRepository;

import java.util.List;

public class ClientViewModel extends AndroidViewModel {
    private ClientRepository clientRepository;
    private LiveData<List<Client>> allClient;

    public ClientViewModel(@NonNull Application application) {
        super(application);
        clientRepository = new ClientRepository(application);
        allClient = clientRepository.getAllClient();
    }

    public void insert(Client client){
        clientRepository.insert(client);
    }

    public void update(Client client){
        clientRepository.update(client);
    }

    public void delete(Client client){
        clientRepository.delete(client);
    }

    public void deleteAllClient(){
        clientRepository.deleteAllClient();
    }

    public LiveData<List<Client>> getAllClient() {
        return allClient;
    }
}
