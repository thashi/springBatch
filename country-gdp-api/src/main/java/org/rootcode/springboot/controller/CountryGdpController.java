package org.rootcode.springboot.controller;

import java.util.List;

import org.rootcode.springboot.model.CountryGdp;
import org.rootcode.springboot.repository.CountryGdpRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CountryGdpController {

	private CountryGdpRepository countryGdpRepository;

	public CountryGdpController(CountryGdpRepository countryGdpRepository) {
		this.countryGdpRepository = countryGdpRepository;
	}

	@GetMapping("/growth")
	public Iterable<CountryGdp> getAll() {
		return this.countryGdpRepository.findAll();
	}

	@RequestMapping(path = "/growth/{code}/{fromYear}/{toYear}", method = RequestMethod.GET)
	public List<String> getGrowth(@PathVariable String code, @PathVariable Integer fromYear,
			@PathVariable Integer toYear) {
		return this.countryGdpRepository.getValuebyCodeBetweenYears(code, fromYear, toYear);
	}
}
