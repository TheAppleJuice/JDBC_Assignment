package se.lexicon;


import se.lexicon.dao.CityDao;
import se.lexicon.dao.CityDaoJDBC;
import se.lexicon.model.City;

import java.util.List;

public class App
{
    public static void main( String[] args ) {
        CityDao cityDao = new CityDaoJDBC();

        City city = cityDao.findById(12);
        System.out.println("City result: " + city);

        System.out.println("----------------------------------");

        List<City> findByCode = cityDao.findByCode("NLD");
        findByCode.forEach(System.out::println);

        System.out.println("----------------------------------");

        List<City> findByName = cityDao.findByName("Amsterdam");
        findByName.forEach(System.out::println);

        System.out.println("----------------------------------");

        List<City> findAll = cityDao.findAll();
        findAll.forEach(System.out::println);







    }
}
