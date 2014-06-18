package com.sample.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sample.entity.Country;
import com.sample.entity.Qualification;
import com.sample.entity.State;
import com.sample.service.SampleService;

@RestController
@RequestMapping("/sample")
public class SampleController {
	@Autowired
	private SampleService sampleService;
	@PersistenceContext
	EntityManager em;

	/*
	 * Web services creating for Customer Master COntroller Qualification For
	 * GetById,GetAll,Insert,Update,update
	 */

	@RequestMapping(value = "/qualification/{id}")
	public Qualification getQualificationById(@PathVariable("id") Integer id) {
		if (id != null) {
			try {
				return sampleService.getQualificationById(id);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				id = null;
			}

		}
		return null;
	}

	@RequestMapping(value = "/qualification")
	public List<Qualification> getQualifications() {
		try {
			return sampleService.getQualifications();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@RequestMapping(value = "/qualification", method = RequestMethod.POST)
	public Qualification createQualification(
			@RequestBody Qualification qualification) {
		return sampleService
				.createQualification(qualification);
	}

	/*@RequestMapping(value = "/qualification", method = RequestMethod.PUT)
	public ResponseEntity<String> updateQualification(
			@RequestBody Qualification qualification,
			@RequestParam("mode") String mode) {
		if (qualification != null && mode != null) {
			try {
				sampleService.updateQualification(
						qualification, mode);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} finally {
				qualification = null;
				mode = null;
			}
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}*/

	/*
	 * WebServices ended for Qualification
	 */
	/*
	 * Web services creating for Customer Master COntroller Country For
	 * GetById,GetAll,Insert,Update,update
	 */
	@RequestMapping(value = "/country/{id}")
	public Country getCountryById(@PathVariable("id") Integer id) {
		if (id != null) {
			try {
				return sampleService.getCountryById(id);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				id = null;
			}

		}
		return null;
	}

	@RequestMapping(value = "/country")
	public List<Country> getCountries() {
		try {
			return sampleService.getCountries();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@RequestMapping(value = "/country", method = RequestMethod.POST)
	public Country createcountry(@RequestBody Country country) {

		return sampleService.createCountry(country);
	}

	/*@RequestMapping(value = "/country", method = RequestMethod.PUT)
	public ResponseEntity<String> updateCountry(
			@RequestBody Country country,
			@RequestParam("mode") String mode) {
		if (country != null && mode != null) {
			try {
				sampleService.updateCountry(country,
						mode);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
			} finally {
				country = null;
				mode = null;
			}
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);

	}*/

	/*
	 * WebServices ended for Country
	 */
	/*
	 * Web services creating for Customer Master COntroller "State" For
	 * GetById,GetAll,Insert,Update,update
	 */
	@RequestMapping(value = "/state/{id}")
	public State getStateById(@PathVariable("id") Integer id) {
		if (id != null) {
			try {
				return sampleService.getStateById(id);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				id = null;
			}
		}
		return null;
	}

	@RequestMapping(value = "/state")
	public List<State> getStates() {
		try {
			return sampleService.getStates();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/state", method = RequestMethod.POST)
	public State createState(@RequestBody State state) {

		System.out.println("******" + state.getName()); /// comments all are i tried with Different approach
		
		/*
		 * ObjectMapper mapper = new ObjectMapper();
		 * 
		 * try {
		 * 
		 * // read from file, convert it to user class CustmastState
		 * custmastState2 = mapper.readValue(custmastState,
		 * CustmastState.class);
		 * 
		 * // display to console System.out.println(custmastState2);
		 * 
		 * } catch (JsonGenerationException e) {
		 * 
		 * e.printStackTrace();
		 * 
		 * } catch (JsonMappingException e) {
		 * 
		 * e.printStackTrace();
		 * 
		 * } catch (IOException e) {
		 * 
		 * e.printStackTrace();
		 * 
		 * }
		 */
		// MyValue value = mapper.readValue(new File("data.json"),
		// MyValue.class);

		// System.out.println("*************" + custmastState2.getName());
		/*
		 * ObjectMapper m = new ObjectMapper(); SchemaFactoryWrapper visitor =
		 * new SchemaFactoryWrapper(); try {
		 * //m.acceptJsonFormatVisitor(m.constructType(TestBody1.class),
		 * visitor);
		 * 
		 * } catch (JsonMappingException e) {
		 * 
		 * e.printStackTrace(); }
		 * com.fasterxml.jackson.module.jsonSchema.JsonSchema jsonSchema =
		 * visitor.finalSchema(); System.out.println("" +
		 * custmastState.getName());
		 */
		return sampleService.createState(state);
	}

	

}
