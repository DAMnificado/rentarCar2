package org.example.repository;

import org.example.model.Client;

import java.util.ArrayList;

public class ClientRepository implements IClientRepository {

    private ArrayList<Client> clients;
    private static ClientRepository clientRepository;

    private ClientRepository() {
        clients = new ArrayList<>();
    }

    /**
     * Método nos da un ClientRepository en caso de que exista. Si no existe lo crea y nos lo devuelve
     * @return devuelve un objeto tipo ClientRepository
     */
    public static ClientRepository getClientRepository() {
        if(clientRepository == null){
            clientRepository = new ClientRepository();
        }
        return clientRepository;
    }

    /**
     * Metodo que le da al cliente que le pasamos la siguiente id disponible y lo añade a la lista
     * @param client tipo Client
     */
    public void add(Client client){
        client.setId(nextIdAvailable());
        clients.add(client);
    }

    /**
     * Método que busca el cliente que tiene asignado el ID que le pasamos
     * @param id tipo Long
     * @return devuelve un cliente si está en nuestra base de datos o un null si no encuentra el cliente
     */
    public Client findById(Long id) {
        for (Client client : clients) {
            if(client.getId() == id){
                return client;
            }
        }
        return null;
    }

    /**
     * Método que añade un nuevo cliente en la lista sustituyéndolo por el que tiene asignado su id
     * @param client
     */
    public void update(Client client) {
        clients.set(clients.indexOf(findById(client.getId())), client);
    }

    /**
     * Método que enseña los clientes de nuestra lista
     * @return devuelve la lista de clientes
     */
    public ArrayList findAll(){
        return clients;
    }

    /**
     * Método que elimina el cliente que tiene el id que le pasamos
     * @param id tipo Long
     */
    public void deleteById(Long id){
        for (Client client : clients) {
            if (client.getId() == id) {
                clients.remove(client);
                break;
            }
        }
    }

    /**
     * Metodo que asigna la siguiente ID disponible
     * @return devuelve un 1 si la lista está vacía o la siguiente id disponible
     */
    public Long nextIdAvailable(){
        if(!clients.isEmpty()){
            return clients.get(clients.size()-1).getId() + 1;
        }
        else{
            return (long)1;
        }
    }

    /**
     * Métod que encuentra el cliente que tiene asignado el dni que le pasamos
     * @param dni
     * @return
     */
    public Client findByDni(String dni){
        for (Client client : clients) {
            if(client.getDni().equals(dni)){
                return client;
            }
        }
        return null;
    }

    /**
     * Método que borra la lista de clientes (crea una nueva en su lugar es como borrarla)
     */
    public void cleanUp(){
        clients = new ArrayList<>();
    }

}
