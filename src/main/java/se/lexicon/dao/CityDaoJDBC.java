package se.lexicon.dao;


import se.lexicon.dao.CityDao;
import se.lexicon.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao {

    public static Connection mySqlConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "12345678");
            System.out.println("connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public City findById(int id) {
        Connection connection = mySqlConnection();
        String query = "select * from city where id=?";
        City city = new City();
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(query);
         ){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                city.setId(resultSet.getInt("id"));
                city.setName(resultSet.getString("name"));
                city.setCountrycode(resultSet.getString("CountryCode"));
                city.setDistrict(resultSet.getString(4));
                city.setPopulation(resultSet.getInt(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return city;
    }

    @Override
    public List<City> findByCode(String Code) {
        Connection connection = mySqlConnection();
        String query = "select * from city where CountryCode=?";
        List <City> cityList = new ArrayList<>();
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setString(1, Code);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                cityList.add(new City(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)

                ));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cityList;
    }

    @Override
    public List<City> findByName(String name) {
        Connection connection = mySqlConnection();
        String query = "select * from city where name=?";
        List <City> cityList = new ArrayList<>();
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cityList.add(new City(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)

                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    @Override
    public List<City> findAll() {
        Connection connection = mySqlConnection();
        String query = "select * from city";
        List <City> cityList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                cityList.add(new City(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)

                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    @Override
    public City add(City city) {
        Connection connection = mySqlConnection();
        String query = "insert into city (name, countryCode, district, population) values (?,?,?,?)";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountrycode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());

           int resultSet = preparedStatement.executeUpdate();
            System.out.println((resultSet == 1) ? "New city added to database" : "City not added");
            // get generated key from prepared statement
            ResultSet rs= preparedStatement.getGeneratedKeys();
            int idKey = 0;
            while (rs.next()) {
                idKey= rs.getInt(1);
            }
            // get from resultset
            // set to city object + id variable
            city.setId(idKey);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return city;
    }

    @Override
    public City update(City city) {
        Connection connection = mySqlConnection();
        String query = "update city set name=?, countrycode=?, district=?, population=? where id= ?";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountrycode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getId());

            int resultSet = preparedStatement.executeUpdate();
            System.out.println((resultSet == 1) ? "City updated to database" : "City not updated");
            // get generated key from prepared statement


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return city;
    }

    @Override
    public int delete(City city) {
        Connection connection = mySqlConnection();
        String query = "delete from city where id= ?";
        try(
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){

            preparedStatement.setInt(1, city.getId());
            int resultSet = preparedStatement.executeUpdate();
            System.out.println((resultSet == 1) ? "City deleted from database" : "City not deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 1;
    }
}
