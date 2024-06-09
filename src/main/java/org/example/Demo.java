package org.example;

public class Demo {
    public static void main(String[] args) {
        DBConnect dbConnect = DBConnect.getInstance();
        ClientService clientService = new ClientService();
        long id = clientService.create("Nastya");
        System.out.println(id);
        System.out.println(clientService.getById(id));

        clientService.setName(id,"Dima");
        System.out.println(clientService.getById(id));
        clientService.deleteById(id);
    }
}
