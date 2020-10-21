package br.com.rentcar.jdbc;

import br.com.rentcar.model.Vehicle;

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

    public Vehicle save(Vehicle vehicle) {
        try {
            String sql = "";
            if (vehicle.getId() == 0){
                sql = "UPDATE vehicle SET brand = ?, name = ?, year = ?, model = ?, fuel = ?, dailyvalue = ?, status = ? WHERE id =  ?";
            }else{
                sql = "INSERT INTO client (name, year, model, fuel, dailyvalue, status) values (?, ?, ?, ?, ?, ?, ?)";
            }
            PreparedStatement stmt = this.connection
                    .prepareStatement(sql);

            stmt.setString(1, vehicle.getName());
            stmt.setString(2, vehicle.getYear());
            stmt.setString(3, vehicle.getModel());
            stmt.setString(4, vehicle.getFuel());
            stmt.setDouble(5, vehicle.getDailyValue());
            stmt.setString(6, vehicle.getStatus());

            if (vehicle.getId() != 0) stmt.setInt(7, vehicle.getId());

            stmt.execute();
            return vehicle;
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Vehicle();
    }

    public boolean delete(int id) {
        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("DELETE FROM vehicle WHERE id =  ?");
            stmt.setInt(1, id);
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

    public List<Vehicle> findAll() {

        List<Vehicle> lstCadastro = new ArrayList<>();
        try {
            ps = this.connection.prepareStatement("SELECT id, name, year, model, fuel, dailyvalue, status FROM vehicle");
            rs = ps.executeQuery();

            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setName(rs.getString("name"));
                vehicle.setYear(rs.getString("year"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setFuel(rs.getString("fuel"));
                vehicle.setDailyValue(rs.getDouble("dailyvalue"));
                vehicle.setStatus(rs.getString("status"));

                lstCadastro.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstCadastro;
    }

    public Vehicle findOne(int id) {
        try {
            ps = this.connection.prepareStatement("SELECT id, name, year, model, fuel, dailyvalue, status FROM vehicle WHERE id = " + id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setName(rs.getString("name"));
                vehicle.setYear(rs.getString("year"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setFuel(rs.getString("fuel"));
                vehicle.setDailyValue(rs.getDouble("dailyvalue"));
                vehicle.setStatus(rs.getString("status"));
                return vehicle;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Vehicle();
    }
}
