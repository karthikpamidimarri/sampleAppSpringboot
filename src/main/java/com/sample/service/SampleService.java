package com.sample.service;

import java.util.List;

import com.sample.entity.Country;
import com.sample.entity.Qualification;
import com.sample.entity.State;

public interface SampleService {

	Qualification getQualificationById(Integer id);

	List<Qualification> getQualifications();

	Qualification createQualification(Qualification qualification);

	Country getCountryById(Integer id);

	List<Country> getCountries();

	Country createCountry(Country country);

	State getStateById(Integer id);

	List<State> getStates();

	State createState(State state);

}
