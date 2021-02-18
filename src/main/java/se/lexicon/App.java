package se.lexicon;


import se.lexicon.dao.CityDao;
import se.lexicon.dao.CityDaoJDBC;
import se.lexicon.model.City;

import java.util.List;

public class App
{
    public static void main( String[] args ) {
        CityDao cityDao = new CityDaoJDBC();



       // City city = cityDao.findById(12);
       // System.out.println("City result: " + city);

        System.out.println("----------------------------------");

      //  List<City> findByCode = cityDao.findByCode("NLD");
      //  findByCode.forEach(System.out::println);

        System.out.println("----------------------------------");


        //List<City> findByName = cityDao.findByName("Amsterdam");
        //findByName.forEach(System.out::println);

        System.out.println("----------------------------------");


       // List<City> findAll = cityDao.findAll();
       // findAll.forEach(System.out::println);


        System.out.println("----------------------------------");
/*
        City addCity = new City("Vemboo","SWE","Urshult", 95);
        City insertedCity = cityDao.add(addCity);
        System.out.println(insertedCity);

 */

        System.out.println("----------------------------------");
/*
        City updateCity = new City(4086, "Vemboö", "SWE", "Urshult", 120);
        City updatedCity = cityDao.update(updateCity);
        System.out.println(updatedCity);

 */

        System.out.println("----------------------------------");

        City deleteCity = new City(4089, "Vemboo", "SWE", "Urshult", 95);
        int deletedCity = cityDao.delete(deleteCity);
















    }
}
