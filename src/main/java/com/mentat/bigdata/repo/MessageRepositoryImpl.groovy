package com.mentat.bigdata.repo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraOperations

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select


class MessageRepositoryImpl implements MessageRepositoryCustom {
	@Autowired
	private CassandraOperations cassandraOps
	
	@Override
	public int getTotalUnread(int userId) {
		Select s = QueryBuilder.select('total_unread').from("user_data")
		s.where(QueryBuilder.eq("user_id", userId))
		def result = cassandraOps.queryForObject(s, Long.class)
		
		return result ? result as int: 0
	}

}
