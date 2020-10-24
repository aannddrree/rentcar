package br.com.rentcar.jdbc;

import br.com.rentcar.model.Client;
import br.com.rentcar.model.Rent;
import br.com.rentcar.model.Vehicle;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentDB {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public Rent save(Rent rent) {
        try {
            String sql = "";
            if (rent.getId() == 0){
                sql = "UPDATE rent SET client_cpf = ?, vehicle_id = ?, rent_date = ?, value_rent = ? WHERE id =  ?";
            }else{
                sql = "INSERT INTO client (client_cpf, vehicle_id, rent_date, value_rent) values (?, ?, ?, ?)";
            }

            connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = this.connection
                    .prepareStatement(sql);

            stmt.setLong(1, rent.getClient().getCpf());
            stmt.setInt(2, rent.getVehicle().getId());
            stmt.setString(3, rent.getRentDate());
            stmt.setDouble(4, rent.getValueRent());

            if (rent.getId() != 0) stmt.setInt(7, rent.getId());

            stmt.execute();
            return rent;
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new Rent();
    }

    public boolean delete(int id) {
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = this.connection
                    .prepareStatement("DELETE FROM rent WHERE id =  ?");
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

    public List<Rent> findAll() {

        List<Rent> lstCadastro = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            ps = this.connection.prepareStatement("SELECT id, client_cpf, vehicle_id, rent_date, value_rent FROM rent");
            rs = ps.executeQuery();

            while (rs.next()) {
                Rent rent = new Rent();
                rent.setId(rs.getInt("id"));
                rent.setClient(new Client(rs.getInt("client_cpf")));
                rent.setVehicle(new Vehicle(rs.getInt("vehicle_id")));
                rent.setRentDate(rs.getString("rent_date"));
                rent.setValueRent(rs.getDouble("value_rent"));
                lstCadastro.add(rent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lstCadastro;
    }

    public Rent findOne(int id) {
        try {
            connection = ConnectionFactory.getConnection();
            ps = this.connection.prepareStatement("SELECT id, client_cpf, vehicle_id, rent_date, value_rent FROM rent WHERE id = " + id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Rent rent = new Rent();
                rent.setId(rs.getInt("id"));
                rent.setClient(new Client(rs.getInt("client_cpf")));
                rent.setVehicle(new Vehicle(rs.getInt("vehicle_id")));
                rent.setRentDate(rs.getString("rent_date"));
                rent.setValueRent(rs.getDouble("value_rent"));
                return rent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Rent();
    }
}
