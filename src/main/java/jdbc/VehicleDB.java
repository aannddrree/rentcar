package jdbc;

import model.Client;
import model.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDB {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public VehicleDB() {
        connection = ConnectionFactory.getConnection();
    }

    public boolean save(Vehicle vehicle) throws SQLException {
        try {
            String sql = "";
            if (vehicle.getId() == 0){
                sql = "UPDATE vehicle SET brand = ?, name = ?, year = ?, model = ?, fuel = ?, dailyvalue = ?, status = ? WHERE id =  ?";
            }else{
                sql = "INSERT INTO client (name, year, model, fuel, dailyvalue, status) values (?, ?, ?, ?, ?, ?, ?)";
            }
            PreparedStatement stmt = this.connection
                    .prepareStatement(sql);

            stmt.setInt(1, vehicle.getId());
            stmt.setString(2, vehicle.getName());
            stmt.setString(3, vehicle.getYear());
            stmt.setString(4, vehicle.getModel());
            stmt.setString(5, vehicle.getFuel());
            stmt.setDouble(6, vehicle.getDailyValue());
            stmt.setString(7, vehicle.getStatus());

            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            connection.close();
        }
        return false;
    }

    public boolean delete(int id) throws SQLException {
        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("DELETE FROM client WHERE id =  ?");
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            connection.close();
        }
        return false;
    }

    public List<Client> findAll() {

        List<Client> lstCadastro = new ArrayList<>();
        try {
            ps = this.connection.prepareStatement("SELECT cpf, nome, datebirth FROM client");
            rs = ps.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setCpf(rs.getString("cpf"));
                client.setName(rs.getString("nome"));
                client.setDateBirth(rs.getString("datebirth"));
                lstCadastro.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstCadastro;
    }

    public Client findById(String cpf) {
        try {
            ps = this.connection.prepareStatement("SELECT cpf, nome, datebirth FROM client WHERE cpf = " + cpf);
            rs = ps.executeQuery();

            if (rs.next()) {
                Client client = new Client();
                client.setCpf(rs.getString("cpf"));
                client.setName(rs.getString("nome"));
                client.setDateBirth(rs.getString("datebirth"));
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Client();
    }
}
