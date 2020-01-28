package com.example.kpsktmhelpdesk.model.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.kpsktmhelpdesk.model.database.KPSKTMDatabase;
import com.example.kpsktmhelpdesk.model.dao.ClientDAO;
import com.example.kpsktmhelpdesk.model.entity.Client;

import java.util.List;

public class ClientRepository {
    private ClientDAO clientDAO;
    private LiveData<List<Client>> allClient;

    public ClientRepository(Application application){
        KPSKTMDatabase database = KPSKTMDatabase.getInstance(application);
        clientDAO = database.clientDAO();
        allClient = clientDAO.getAllClients();
    }

    public void insert(Client client){
        new InsertClientAsyncTask(clientDAO).execute(client);
    }

    public void update(Client client){
        new UpdateClientAsyncTask(clientDAO).execute(client);
    }

    public void delete(Client client){
        new DeleteClientAsyncTask(clientDAO).execute(client);
    }

    public void deleteAllClient(){
        new DeleteAllClientAsyncTask(clientDAO).execute();
    }

    public LiveData<List<Client>> getAllClient(){
        return allClient;
    }

    //Asynctask
    private static class InsertClientAsyncTask extends AsyncTask<Client,Void,Void>{
        private ClientDAO clientDAO;

        private InsertClientAsyncTask(ClientDAO clientDAO){
            this.clientDAO = clientDAO;
        }
        @Override
        protected Void doInBackground(Client... clients) {
            clientDAO.insert(clients[0]);
            return null;
        }
    }


    private static class UpdateClientAsyncTask extends AsyncTask<Client,Void,Void>{
        private ClientDAO clientDAO;

        private UpdateClientAsyncTask(ClientDAO clientDAO){
            this.clientDAO = clientDAO;
        }
        @Override
        protected Void doInBackground(Client... clients) {
            clientDAO.update(clients[0]);
            return null;
        }
    }

    private static class DeleteClientAsyncTask extends AsyncTask<Client,Void,Void>{
        private ClientDAO clientDAO;

        private DeleteClientAsyncTask(ClientDAO clientDAO){
            this.clientDAO = clientDAO;
        }
        @Override
        protected Void doInBackground(Client... clients) {
            clientDAO.delete(clients[0]);
            return null;
        }
    }

    private static class DeleteAllClientAsyncTask extends AsyncTask<Client,Void,Void>{
        private ClientDAO clientDAO;

        private DeleteAllClientAsyncTask(ClientDAO clientDAO){
            this.clientDAO = clientDAO;
        }
        @Override
        protected Void doInBackground(Client... clients) {
            clientDAO.deleteAllClient();
            return null;
        }
    }

}
