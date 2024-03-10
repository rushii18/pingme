package com.pingme.message.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pingme.message.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
