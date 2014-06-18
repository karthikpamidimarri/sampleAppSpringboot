package com.sample;

/*import javax.sql.DataSource;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.boot.CommandLineRunner;
 import org.springframework.boot.SpringApplication;
 import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
 import org.springframework.context.annotation.AnnotationConfigApplicationContext;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.context.annotation.PropertySource;
 import org.springframework.context.support.AbstractApplicationContext;
 import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
 import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
 import org.springframework.orm.jpa.JpaTransactionManager;
 import org.springframework.orm.jpa.JpaVendorAdapter;
 import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
 import org.springframework.orm.jpa.vendor.Database;
 import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
 import org.springframework.transaction.PlatformTransactionManager;*/

/*@Configuration
 @EnableAutoConfiguration
 @EnableJpaRepositories("test.builder")
 @PropertySource("classpath:application.properties")
 public class AppConfiguration {

 //private static Logger logger = LoggerFactory.getLogger(BootApp.class);

 @Value("${spring.datasource.driverClassName}")
 private String databaseDriverClassName;

 @Value("${spring.datasource.url}")
 private String datasourceUrl;

 @Value("${spring.datasource.username}")
 private String databaseUsername;

 @Value("${spring.datasource.password}")
 private String databasePassword;

 @Bean
 public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
 return new PropertySourcesPlaceholderConfigurer();
 }

 @Bean
 public DataSource dataSource() {

 org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
 ds.setDriverClassName(databaseDriverClassName);
 ds.setUrl(datasourceUrl);
 ds.setUsername(databaseUsername);
 ds.setPassword(databasePassword);


 return ds;
 }

 @Bean
 public LocalContainerEntityManagerFactoryBean entityManagerFactory(
 DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
 LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
 lef.setDataSource(dataSource);
 lef.setJpaVendorAdapter(jpaVendorAdapter);
 lef.setPackagesToScan("test.builder");

 return lef;
 }

 @Bean
 public JpaVendorAdapter jpaVendorAdapter() {
 HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
 hibernateJpaVendorAdapter.setShowSql(true);
 hibernateJpaVendorAdapter.setGenerateDdl(false);
 hibernateJpaVendorAdapter.setDatabase(Database.SQL_SERVER);
 return hibernateJpaVendorAdapter;
 }

 @Bean
 public PlatformTransactionManager transactionManager() {
 return new JpaTransactionManager();
 }

 @Override
 public void run(String... args) {

 AbstractApplicationContext context = new AnnotationConfigApplicationContext(
 BootApp.class);
 BuilderRepository repository = context.getBean(BuilderRepository.class);
 Builder builder = repository.findOne(10);

 logger.info("Retrieved ", builder.toString());

 context.close();
 }

 public static void main(String[] args) {

 SpringApplication.run(AppConfiguration.class, args);

 }

 }*/

import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {

	@Value("${spring.datasource.driverClassName}")
	private String databaseDriverClassName;

	@Value("${spring.datasource.url}")
	private String datasourceUrl;

	@Value("${spring.datasource.username}")
	private String databaseUsername;

	@Value("${spring.datasource.password}")
	private String databasePassword;

	
	@Bean
	public DataSource dataSource() throws SQLException {
		org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
		ds.setDriverClassName(databaseDriverClassName);
		ds.setUrl(datasourceUrl);
		ds.setUsername(databaseUsername);
		ds.setPassword(databasePassword);

		return ds;
		
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() throws SQLException {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.sample.entity");
		factory.setDataSource(dataSource());
		factory.afterPropertiesSet();

		return factory.getObject();
	}

	@Bean
	public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	@Bean
	public PlatformTransactionManager transactionManager() throws SQLException {

		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	@Bean
	public MappingJackson2HttpMessageConverter jackson2Converter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper());
		return converter;
	}

	@Bean
	public ObjectMapper objectMapper() {
		Object objectMapper = new ObjectMapper();
		// ((ObjectMapper) objectMapper).registerModule(new Hibernate4Module());
		((ObjectMapper) objectMapper)
				.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
		// to allow serialization of "empty" POJOs (no properties to serialize)
		// (without this setting, an exception is thrown in those cases)
		((ObjectMapper) objectMapper)
				.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		// to write java.util.Date, Calendar as number (timestamp):
		((ObjectMapper) objectMapper)
				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		// DeserializationFeature for changing how JSON is read as POJOs:

		// to prevent exception when encountering unknown property:
		((ObjectMapper) objectMapper)
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// to allow coercion of JSON empty String ("") to null Object value:
		((ObjectMapper) objectMapper)
				.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		
		return (ObjectMapper) objectMapper;
	}

	

}
