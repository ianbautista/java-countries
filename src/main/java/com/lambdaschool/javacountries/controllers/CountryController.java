package com.lambdaschool.javacountries.controllers;

import com.lambdaschool.javacountries.models.Country;
import com.lambdaschool.javacountries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CountryController
{
    @Autowired
    private CountryRepository countryrepos;

    private List<Country> findCountries(List<Country> list, CheckCountry tester)
    {
        List<Country> tempList = new ArrayList<>();
        for (Country c : list)
        {
            if (tester.test(c))
            {
                tempList.add(c);
            }
        }
        return tempList;
    }

    //http://localhost:2020/names/all
    @GetMapping(value = "/names/all", produces = {"application/json"})
    public ResponseEntity<?> listAllCountries()
    {
        List<Country> allCountryList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(allCountryList::add);

        allCountryList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));

        for (Country c : allCountryList)
        {
            System.out.println((c));
        }

        return new ResponseEntity<>(allCountryList, HttpStatus.OK);
    }

    //http://localhost:2020/names/start/u
    @GetMapping(value = "/names/start/{letter}", produces = {"application/json"})
    public ResponseEntity<?> listAllByFirstLetter(@PathVariable char letter)
    {
        List<Country> allCountryList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(allCountryList::add);

        List<Country> returnList = findCountries(allCountryList, c -> c.getName().toLowerCase().charAt(0) == letter);
        return new ResponseEntity<>( returnList, HttpStatus.OK);
    }

    //http://localhost:2020/population/total
    @GetMapping(value = "/population/total", produces = {"application/json"})
    public ResponseEntity<?> totalPopulation()
    {
        List<Country> allCountryList = new ArrayList<>();
        countryrepos.findAll().iterator().forEachRemaining(allCountryList::add);

        long total = 0;
        for (Country c : allCountryList)
        {
            total = total + c.getPopulation();
        }

        System.out.println("The Total Population is " + total);
        return new ResponseEntity<>("Status OK", HttpStatus.OK);
    }



}