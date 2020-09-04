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


}