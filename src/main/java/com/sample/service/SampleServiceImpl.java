package com.sample.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.entity.Country;
import com.sample.entity.Qualification;
import com.sample.entity.State;
import com.sample.repository.CountryRepository;
import com.sample.repository.QualificationRepository;
import com.sample.repository.StateRepository;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	public CountryRepository countryRepository;
	@Autowired
	public StateRepository stateRepository;
	@Autowired
	public QualificationRepository qualificationRepository;

	public Qualification getQualificationById(Integer id) {

		return qualificationRepository.findOne(id);
	}

	public List<Qualification> getQualifications() {
		return qualificationRepository.findAll();
	}

	public Qualification createQualification(Qualification qualification) {

		return qualificationRepository.saveAndFlush(qualification);
	}

	public Country getCountryById(Integer id) {

		return countryRepository.findOne(id);
	}

	public List<Country> getCountries() {

		return countryRepository.findAll();
	}

	public Country createCountry(Country country) {

		return countryRepository.saveAndFlush(country);
	}

	public State getStateById(Integer id) {

		return stateRepository.findOne(id);
	}

	public List<State> getStates() {

		return stateRepository.findAll();
	}

	public State createState(State state) {

		return stateRepository.saveAndFlush(state);
	}

	

}
