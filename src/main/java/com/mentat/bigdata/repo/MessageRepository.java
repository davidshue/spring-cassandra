package com.mentat.bigdata.repo;

import org.springframework.data.repository.Repository;

import com.mentat.bigdata.model.Message;
import com.mentat.bigdata.model.MessagePK;

public interface MessageRepository extends Repository<Message, MessagePK>, MessageRepositoryCustom {
	Message findOne(MessagePK id);
	
	Message save(Message entity);
	
	boolean exists(MessagePK id);
	
	void delete(MessagePK id);
}
