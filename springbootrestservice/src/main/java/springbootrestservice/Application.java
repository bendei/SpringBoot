package springbootrestservice;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

@EnableJpaRepositories
@SpringBootApplication
public class Application {

	  public static void main(String[] args) {
		  SpringApplication.run(Application.class, args);
	    }
	  
	  @Bean 
	  public DataSource getDataSource() { 
	      DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create(); 
	      dataSourceBuilder.url("jdbc:h2:mem:testdb");
	      dataSourceBuilder.driverClassName("org.h2.Driver");
	      dataSourceBuilder.username("sa"); 
	      dataSourceBuilder.password("");	   
	      return dataSourceBuilder.build(); 
	  }
	    
	  
  
	  
}
