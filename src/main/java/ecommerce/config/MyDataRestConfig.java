package ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import ecommerce.entity.Country;
import ecommerce.entity.Product;
import ecommerce.entity.ProductCategory;
import ecommerce.entity.State;


@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

	private EntityManager entityManager; 
	
	public MyDataRestConfig(EntityManager theEntityManager) {
		this.entityManager=theEntityManager;
	}
	
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
	
	HttpMethod[] theUnsupportedActions= {HttpMethod.PUT,HttpMethod.POST,HttpMethod.DELETE};
	
	
	disableHttpMethods(Product.class,config,theUnsupportedActions);
	
	
	disableHttpMethods(ProductCategory.class,config, theUnsupportedActions);
	disableHttpMethods(Country.class,config, theUnsupportedActions);
	disableHttpMethods(State.class,config, theUnsupportedActions);
	
	exposeIds(config);
	
	}


	private void disableHttpMethods( Class theClass,RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
		config.getExposureConfiguration()
		.forDomainType(theClass)
		.withItemExposure((metadata,httpMethods)->httpMethods.disable(theUnsupportedActions))
		.withCollectionExposure((metadata,httpMethods) -> httpMethods.disable(theUnsupportedActions));
	}


	private void exposeIds(RepositoryRestConfiguration config) {
		
		Set<EntityType<?>> entities=entityManager.getMetamodel().getEntities();
		
		List<Class> entityClasses=new ArrayList<Class>();
		
		for(EntityType tempEntityType:entities) {
			entityClasses.add(tempEntityType.getJavaType());
		
		}
		Class[] domainTypes=entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
	}

	
	
}
