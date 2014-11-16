package com.mentat.bigdata

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean
import org.springframework.data.cassandra.config.SchemaAction
import org.springframework.data.cassandra.convert.CassandraConverter
import org.springframework.data.cassandra.convert.MappingCassandraConverter
import org.springframework.data.cassandra.core.CassandraOperations
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext
import org.springframework.data.cassandra.mapping.CassandraMappingContext
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.Session


@Configuration
@EnableCassandraRepositories(basePackages = ["com.mentat.bigdata.repo" ])
class AppConfig {
	@Bean
	PropertyPlaceholderConfigurer configProperties() {
		PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer(
			location: new ClassPathResource('bigdata.properties')
		)
	}
	
	@Bean
	CassandraClusterFactoryBean cluster(@Value('${cassandra.contactpoints}') String contactPoints,
			@Value('${cassandra.port}') String cassandraPort) {

		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints(contactPoints);
		cluster.setPort(Integer.parseInt(cassandraPort));

		return cluster;
	}

	@Bean
	public CassandraMappingContext mappingContext() {
		return new BasicCassandraMappingContext();
	}

	@Bean
	public CassandraConverter converter() {
		return new MappingCassandraConverter(mappingContext());
	}

	@Bean
	@Autowired
	public CassandraSessionFactoryBean session(Cluster cluster,
			@Value('${cassandra.keyspace}') String keyspace) throws Exception {

		CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
		session.setCluster(cluster);
		session.setKeyspaceName(keyspace);
		session.setConverter(converter());
		session.setSchemaAction(SchemaAction.NONE);

		return session;
	}

	@Bean
	@Autowired
	public CassandraOperations cassandraTemplate(Session session) throws Exception {
		return new CassandraTemplate(session);
	}
}
