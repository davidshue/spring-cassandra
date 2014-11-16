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
import com.mentat.bigdata.model.Message
import com.mentat.bigdata.model.MessagePK
import com.mentat.bigdata.repo.MessageRepository

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
@ActiveProfiles("bigdata")
class BigDataTest {

	static final String MESSAGE_TABLE = "message"
	
	@Autowired
	private Session session
	
	@Autowired
	MessageRepository repo

	@Autowired
	private CassandraOperations cassandraOps



	@Test
	void testTimeUUID() {
		UUID id = UUIDGen.timeUUID
		println 'time: ' + new Date(UUIDGen.getAdjustedTimestamp(id))
		
		List<Message> messages = repo.findAll()

		println 'findOne ' + repo.findOne(new MessagePK(userId: 123, id: id))
		
		println 'totalUnread ' + repo.getTotalUnread(123)
	}

	@Test
	void testInsert() {
		int userId = 123
		UUID id = UUIDGen.timeUUID
		MessagePK pk = new MessagePK(userId: userId, id: id)
		Message msg = new Message(pk:pk,
			creatorId: 200,
			//creator: [first_name: 'david', last_name: 'smith'],
			body: 'test message',
			lastReadDate: new Date()
			)
		
		repo.save(msg)
	}

}
