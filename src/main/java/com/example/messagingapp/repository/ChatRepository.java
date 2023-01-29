package com.example.messagingapp.repository;

import com.example.messagingapp.entity.ChatEntity;
import com.example.messagingapp.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRepository extends CrudRepository<ChatEntity, Long> {

    public List<ChatEntity> findAllByAdministrator(UserEntity administrator);
}
