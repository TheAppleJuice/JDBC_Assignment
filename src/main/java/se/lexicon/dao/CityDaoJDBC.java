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
        return null;
    }

    @Override
    public List<City> findAll() {
        return null;
    }

    @Override
    public City add(City city) {
        return null;
    }

    @Override
    public City update(City city) {
        return null;
    }

    @Override
    public int delete(City city) {
        return 0;
    }
}
