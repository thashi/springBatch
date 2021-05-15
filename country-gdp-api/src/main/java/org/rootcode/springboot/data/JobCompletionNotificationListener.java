package org.rootcode.springboot.data;

import org.rootcode.springboot.model.CountryGdp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOGGER.info("Job Completed");

			String query = "SELECT id,name,code,year,value from countryGdp";
			
//			entityManager.createQuery(query, Object[].class).getResultList()
//			.stream()
//			.map(c -> new CountryGdp((long) c[0], (String) c[1], (String) c[2], (Integer) c[3], (String) c[4]))
//			.forEach(cGdp -> LOGGER.info("Found < {} in the database>", cGdp));

			jdbcTemplate
					.query(query,
							(rs, row) -> new CountryGdp(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)))
					.forEach(countryGdp -> LOGGER.info("Found < {} in the database>", countryGdp));
		}
	}
}
