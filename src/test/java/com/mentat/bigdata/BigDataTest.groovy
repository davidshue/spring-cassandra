package com.mentat.bigdata;

import static org.junit.Assert.*

import org.apache.cassandra.utils.UUIDGen
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraOperations
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import com.datastax.driver.core.Session

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
@ActiveProfiles("bigdata")
class BigDataTest {

	static final String MESSAGE_TABLE = "message"
	
	@Autowired
	private Session session

	@Autowired
	private CassandraOperations cassandraOps



	@Test
	void testTimeUUID() {
		UUID id = UUIDGen.timeUUID
		println 'time: ' + new Date(UUIDGen.getAdjustedTimestamp(id))
	}


}
