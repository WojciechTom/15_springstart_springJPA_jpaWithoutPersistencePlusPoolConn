package pl.javastart.config;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class JpaConfig {

	
	@Bean
	public LocalContainerEntityManagerFactoryBean createEMF(JpaVendorAdapter adapter, DataSource ds) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(ds);		
        emf.setPersistenceUnitName("spring-jpa-pu");
        emf.setJpaVendorAdapter(adapter);
        emf.setPackagesToScan("pl.javastart.model");
		return emf;
	}
	
	
	@Bean
	public JpaVendorAdapter createAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		return adapter;	
	}
	
	@Bean
	public DataSource createDS() {
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/library1?useSSL=false&serverTimezone=UTC");
		ds.setUsername("root");
		ds.setPassword("admin");
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setInitialSize(6);
        return ds;
	}

	
}
