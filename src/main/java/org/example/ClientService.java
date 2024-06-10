package org.example;

import java.io.IOError;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private static final String CREATE = "INSERT INTO client (name) VALUES (?)";
    private static final String GET_BY_ID = "SELECT * FROM client WHERE id = ?";
    private static final String UPDATE = "UPDATE client SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM client WHERE id = ?";
    private static final String GET_LIST = "SELECT * FROM client";

    public ClientService(){
        try {
            connection = DBConnect.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long create(String name){
        long result = 0;
        if(name.length() <= 2 && name.length() >= 1000){
            throw new IllegalArgumentException();
        }
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CREATE,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,name);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()){
                result = generatedKeys.getObject(1, Long.class);
            }
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
    public String getById(long id){
        String result = "";
        try{
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result = resultSet.getString(2);
            }
            connection.setAutoCommit(true);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void setName(long id, String name){
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            connection.setAutoCommit(true);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    void deleteById(long id){
        try{
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.setAutoCommit(true);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    List<Client> listAll(){
        List<Client> clientList = new ArrayList<>();

        try{
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(GET_LIST);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Client client = new Client();
                client.setId(resultSet.getInt(1));
                client.setName(resultSet.getString(2));
                clientList.add(client);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientList;
    }
}
