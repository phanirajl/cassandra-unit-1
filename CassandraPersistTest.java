package br.com.marley.statemachine;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TesteService.class})
@TestExecutionListeners({ CassandraUnitDependencyInjectionTestExecutionListener.class,
	DependencyInjectionTestExecutionListener.class })
@CassandraDataSet(value = { "bootstrap.cql" }, keyspace = "keyspaceToCreate")
@EmbeddedCassandra(timeout = 60000)
@EnableAutoConfiguration
@PropertySource("application.properties")
@EnableCassandraRepositories(basePackageClasses = TesteRepository.class)
public class CassandraPersistTest {

	@Autowired
	private TesteService rp;

	@Test
	public void put() throws Exception {
		// Arrange
		Teste t = Teste.builder().id(UUID.randomUUID())
				.text("Teste")
				.fase(FaseEvento.EVENTO)
				.date(Date.from(LocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant())).build();
		rp.save(t);
	}

	@Test
	public void get() throws Exception {
		// Arrange
		UUID id = UUID.randomUUID();
		Teste t1 = Teste.builder().id(id)
				.text("Teste")
				.fase(FaseEvento.EVENTO)
				.date(OffsetDateTime.now().toLocalDate()).build();
		rp.save(t1);

		Optional<Teste> t2 = rp.get(id);
	}
}

