package br.com.rentcar.jdbc;

import br.com.rentcar.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                sql = "UPDATE client SET name = ?, datebirth = ? WHERE cpf =  ?";
                stmt = this.connection
                        .prepareStatement(sql);
                stmt.setString(1, client.getName());
                stmt.setString(2, client.getDateBirth());
                stmt.setString(3, client.getCpf());
            }else{
                sql = "INSERT INTO client (cpf, name, datebirth) values (?, ?, ?)";
                stmt = this.connection
                        .prepareStatement(sql);
                stmt.setString(1, client.getCpf());
                stmt.setString(2, client.getName());
                stmt.setString(3, client.getDateBirth());
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
            ps = this.connection.prepareStatement("SELECT cpf, name, datebirth FROM client");
            rs = ps.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setCpf(rs.getString("cpf"));
                client.setName(rs.getString("name"));
                client.setDateBirth(rs.getString("datebirth"));
                lstCadastro.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstCadastro;
    }

    public Client findOne(String cpf) {
        try {
            ps = this.connection.prepareStatement("SELECT cpf, name, datebirth FROM client WHERE cpf = " + cpf);
            rs = ps.executeQuery();

            if (rs.next()) {
                Client client = new Client();
                client.setCpf(rs.getString("cpf"));
                client.setName(rs.getString("name"));
                client.setDateBirth(rs.getString("datebirth"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Client();
    }
}
