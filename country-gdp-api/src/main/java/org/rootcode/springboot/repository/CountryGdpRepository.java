package org.rootcode.springboot.repository;

import java.util.List;

import org.rootcode.springboot.model.CountryGdp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryGdpRepository extends CrudRepository<CountryGdp, Long> {

	@Query("select c.value from CountryGdp c where c.code = :code and (c.year >= :fromYear and c.year < :toYear) order by year")
	List<String> getValuebyCodeBetweenYears(
			@Param("code") String code, 
			@Param("fromYear") Integer fromYear,
			@Param("toYear") Integer toYear
			);

}
