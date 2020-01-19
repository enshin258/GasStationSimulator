package sample.Objects;

import sample.Objects.Client;

import java.util.LinkedList;

public class QueueToCash extends LinkedList<Client> {
    public int limitOfQueue;

    public QueueToCash(int limitOfQueue)
    {
        this.limitOfQueue = limitOfQueue;
    }

    public boolean addClient(Client client) {
        if(size() >= limitOfQueue)
        {
            return false;
        }
        else
        {
            super.add(client);
            return true;
        }
    }

    public Client popClient() {
        Client client = get(0);
        remove(get(0));
        return client;
    }
}
