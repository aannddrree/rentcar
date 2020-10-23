package br.com.rentcar.jdbc;

import br.com.rentcar.model.Client;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDB {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public ClientDB() {
        connection = ConnectionFactory.getConnection();
    }

    public Client save(Client client) {
        try {
            String sql = "";
            PreparedStatement stmt;

            if (client.getCpf().equals("") || client.getCpf().equals(null)){
                sql = "UPDATE client SET name = ?, date_birth = ?, status = ? WHERE cpf =  ?";
                stmt = this.connection
                        .prepareStatement(sql);
                stmt.setString(1, client.getName());
                stmt.setString(2, client.getDateBirth());
                stmt.setBoolean(3,client.isStatus());
                stmt.setString(4, client.getCpf());
            }else{
                sql = "INSERT INTO client (cpf, name, status, date_birth) values (?, ?, ?, ?)";
                stmt = this.connection
                        .prepareStatement(sql);
                stmt.setString(1, client.getCpf());
                stmt.setString(2, client.getName());
                stmt.setBoolean(3,client.isStatus());
                stmt.setString(4, client.getDateBirth());
            }
            stmt.execute();
            return client;
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Client();
    }

    public boolean delete(String cpf) {
        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("DELETE FROM client WHERE cpf =  ?");
            stmt.setString(1, cpf);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Client> findAll() {

        List<Client> lstCadastro = new ArrayList<>();
        try {
            ps = this.connection.prepareStatement("SELECT cpf, name, date_birth, status FROM client");
            rs = ps.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setCpf(rs.getString("cpf"));
                client.setName(rs.getString("name"));
                client.setDateBirth(rs.getString("date_birth"));
                client.setStatus(rs.getBoolean("status"));
                lstCadastro.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstCadastro;
    }

    public Client findOne(String cpf) {
        try {
            ps = this.connection.prepareStatement("SELECT cpf, name, date_birth, status FROM client WHERE cpf = " + cpf);
            rs = ps.executeQuery();

            if (rs.next()) {
                Client client = new Client();
                client.setCpf(rs.getString("cpf"));
                client.setName(rs.getString("name"));
                client.setDateBirth(rs.getString("date_birth"));
                client.setStatus(rs.getBoolean("status"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Client();
    }
}
