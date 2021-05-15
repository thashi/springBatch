package org.rootcode.springboot.data;

import org.rootcode.springboot.model.CountryGdp;
import org.rootcode.springboot.model.CountryGdpInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CountryGdpProcessor implements ItemProcessor<CountryGdpInput, CountryGdp> {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryGdpProcessor.class);

	public CountryGdp process(final CountryGdpInput countryGdpInput) throws Exception {

		CountryGdp transformedCountryGdp = new CountryGdp();

		transformedCountryGdp.setId(Long.parseLong(countryGdpInput.getId()));
		transformedCountryGdp.setName(countryGdpInput.getName());
		transformedCountryGdp.setCode(countryGdpInput.getCode());
		transformedCountryGdp.setYear(Integer.valueOf(countryGdpInput.getYear()));
		transformedCountryGdp.setValue(countryGdpInput.getValue());

		LOGGER.info("Converting ( {} ) into ( {} )", countryGdpInput, transformedCountryGdp);

		return transformedCountryGdp;

	}
}
