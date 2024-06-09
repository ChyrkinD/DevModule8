package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private static final String CREATE = "INSERT INTO client (name) VALUES (?)";
    private static final String GET_BY_ID = "SELECT name FROM client WHERE id = ?";

    public ClientService(){
        try {
            connection = DBConnect.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long create(String name){
        long result = 0;

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
        String result;
        try{
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(GET_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getResultSet();
            result = resultSet.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    void setName(long id, String name){

    }
    void deleteById(long id){

    }
    List<Client> listAll(){
        return null;
    }
}
