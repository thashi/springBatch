package org.rootcode.springboot.data;

import javax.sql.DataSource;

import org.rootcode.springboot.model.CountryGdp;
import org.rootcode.springboot.model.CountryGdpInput;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Value("data/*gdp.csv")
	private String fileInput;

	@Bean
	public FlatFileItemReader<CountryGdpInput> reader() {
		return new FlatFileItemReaderBuilder<CountryGdpInput>().name("CountryGdpReader")
				.resource(new ClassPathResource(fileInput)).delimited()
				.names(new String[] {"id", "name", "code", "year", "value" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<CountryGdpInput>() {
					{
						setTargetType(CountryGdpInput.class);
					}
				}).build();
	}

	@Bean
	public CountryGdpProcessor processor() {
		return new CountryGdpProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<CountryGdp> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<CountryGdp>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO countryGdp (id, name, code, year, value) VALUES (:id, :name, :code, :year, :value)")
				.dataSource(dataSource).build();
	}

	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1)
				.end().build();
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<CountryGdp> writer) {
		return stepBuilderFactory.get("step1").<CountryGdpInput, CountryGdp>chunk(10).reader(reader()).processor(processor())
				.writer(writer).build();
	}
}
