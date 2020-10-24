package br.com.rentcar.jdbc;

import br.com.rentcar.model.Vehicle;
import br.com.rentcar.util.Fuel;
import br.com.rentcar.util.Status;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VehicleDB {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public Vehicle save(Vehicle vehicle) {
        try {
            String sql = "";
            if (vehicle.getId() != 0){
                sql = "UPDATE vehicle SET brand = ?, name = ?, year = ?, model = ?, fuel = ?, daily_value = ?, status = ? WHERE id =  ?";
            }else{
                sql = "INSERT INTO vehicle (brand, name, year, model, fuel, daily_value, status) values (?, ?, ?, ?, ?, ?, ?)";
            }
            connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = this.connection
                    .prepareStatement(sql);

            stmt.setString(1, vehicle.getBrand());
            stmt.setString(2, vehicle.getName());
            stmt.setInt(3, vehicle.getYear());
            stmt.setInt(4, vehicle.getModel());
            stmt.setInt(5, vehicle.getFuel().getId());
            stmt.setDouble(6, vehicle.getDailyValue());
            stmt.setInt(7, vehicle.getStatus().getId());

            if (vehicle.getId() != 0) stmt.setInt(8, vehicle.getId());

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
            connection = ConnectionFactory.getConnection();
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
            connection = ConnectionFactory.getConnection();
            ps = this.connection.prepareStatement("SELECT id, brand, name, year, model, fuel, daily_value, status FROM vehicle");
            rs = ps.executeQuery();

            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setName(rs.getString("name"));
                vehicle.setYear(rs.getInt("year"));
                vehicle.setModel(rs.getInt("model"));
                vehicle.setFuel(Fuel.valueOf(rs.getString("fuel")));
                vehicle.setDailyValue(rs.getDouble("daily_value"));
                vehicle.setStatus(Status.valueOf(rs.getString("status")));

                lstCadastro.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstCadastro;
    }

    public Vehicle findOne(int id) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = this.connection.prepareStatement("SELECT id, brand, name, year, model, fuel, daily_value, status FROM vehicle WHERE id = " + id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setBrand(rs.getString("brand"));
                vehicle.setName(rs.getString("name"));
                vehicle.setYear(rs.getInt("year"));
                vehicle.setModel(rs.getInt("model"));
                vehicle.setFuel(Fuel.valueOf(rs.getString("fuel")));
                vehicle.setDailyValue(rs.getDouble("daily_value"));
                vehicle.setStatus(Status.valueOf(rs.getString("status")));
                return vehicle;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Vehicle();
    }
}
