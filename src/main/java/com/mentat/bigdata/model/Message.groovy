package com.mentat.bigdata.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import org.apache.cassandra.utils.UUIDGen
import org.springframework.data.cassandra.mapping.Column
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table

@ToString(includeNames=true)
@Table
@EqualsAndHashCode
class Message {
	@PrimaryKey
	MessagePK pk
	
	@Column('creator_id')
	int creatorId
	
	@Column('creator')
	def creator
	
	String body
	
	@Column('last_read_date')
	Date lastReadDate
	
	Date getCreateDate()
	{
		// Need to use UUIDGen.getAdjustedTimestamp() to get the right date and timezone!
		new Date(UUIDGen.getAdjustedTimestamp(pk.id))
	}
}