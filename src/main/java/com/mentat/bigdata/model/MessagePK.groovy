package com.mentat.bigdata.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import org.apache.cassandra.utils.UUIDGen
import org.springframework.cassandra.core.PrimaryKeyType
import org.springframework.data.cassandra.mapping.PrimaryKeyClass
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn

@PrimaryKeyClass
@ToString(includeNames=true)
@EqualsAndHashCode
class MessagePK implements Serializable {

	@PrimaryKeyColumn(name = 'user_id', ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	int userId
	
	@PrimaryKeyColumn(name = 'id', ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	UUID id = UUIDGen.timeUUID
}
