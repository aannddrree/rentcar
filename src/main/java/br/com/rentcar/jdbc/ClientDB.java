package br.com.rentcar.jdbc;

import br.com.rentcar.model.Client;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDB {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public Client save(Client client) {
        try {
            String sql = "";
            PreparedStatement stmt;

            connection = ConnectionFactory.getConnection();

            if (client.getCpf() == 0){
                sql = "UPDATE client SET name = ?, date_birth = ?, status = ? WHERE cpf =  ?";
                stmt = this.connection
                        .prepareStatement(sql);
                stmt.setString(1, client.getName());
                stmt.setDate(2, new Date(client.getDateBirth().getTime()));
                stmt.setBoolean(3,client.isStatus());
                stmt.setLong(4, client.getCpf());
            }else{
                sql = "INSERT INTO client (cpf, name, status, date_birth) values (?, ?, ?, ?)";
                stmt = this.connection
                        .prepareStatement(sql);
                stmt.setLong(1, client.getCpf());
                stmt.setString(2, client.getName());
                stmt.setBoolean(3,client.isStatus());
                stmt.setDate(4, new Date(client.getDateBirth().getTime()));
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

            connection = ConnectionFactory.getConnection();

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

            connection = ConnectionFactory.getConnection();
            ps = this.connection.prepareStatement("SELECT cpf, name, date_birth, status FROM client");
            rs = ps.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setCpf(rs.getLong("cpf"));
                client.setName(rs.getString("name"));
                client.setDateBirth(rs.getDate("date_birth"));
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
            connection = ConnectionFactory.getConnection();
            ps = this.connection.prepareStatement("SELECT cpf, name, date_birth, status FROM client WHERE cpf = '" + cpf + "'");
            rs = ps.executeQuery();

            if (rs.next()) {
                Client client = new Client();
                client.setCpf(rs.getLong("cpf"));
                client.setName(rs.getString("name"));
                client.setDateBirth(rs.getDate("date_birth"));
                client.setStatus(rs.getBoolean("status"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Client();
    }
}
