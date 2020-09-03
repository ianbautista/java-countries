package com.lambdaschool.javacountries.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController
{
    @Autowired
    private CountryController countryrepos;
}