package com.example.kpsktmhelpdesk.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kpsktmhelpdesk.R;
import com.example.kpsktmhelpdesk.model.entity.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientListAdapter extends RecyclerView.Adapter<ClientListAdapter.ClientHolder> {

    public static final String TAG = "ClientListAdapter";
    private List<Client> clients = new ArrayList<>();
    private CustomOnItemClickListener listener;

    @NonNull
    @Override
    public ClientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_item,parent,false);
        return new ClientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientHolder holder, int position) {
        Client currentClient = clients.get(position);
        holder.textViewTitle.setText(currentClient.getClientName());
        holder.desc.setText(currentClient.getProbSpec());
        holder.priority.setText(String.valueOf(currentClient.getClientId()));
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public void setClients(List<Client> clients){
        this.clients = clients;
        notifyDataSetChanged();
    }

    /*public Client getClientAt(int position){
        return clients.get(position);
    }*/
    class ClientHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView desc;
        private TextView priority;

        public ClientHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            desc = itemView.findViewById(R.id.text_view_description);
            priority = itemView.findViewById(R.id.text_view_priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Log.d(TAG,"position is : " + position);
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(clients.get(position),v);
                    }
                }
            });
        }
    }

    public interface CustomOnItemClickListener{
        void onItemClick(Client client, View v);
    }

    public void setCustomOnItemClickListener(CustomOnItemClickListener listener){
            this.listener = listener;
    }
}
