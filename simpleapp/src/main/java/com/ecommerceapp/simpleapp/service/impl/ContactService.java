package com.ecommerceapp.simpleapp.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerceapp.simpleapp.entity.MessageEntity;
import com.ecommerceapp.simpleapp.repository.ContactRepo;
@Service
public class ContactService {


  @Autowired
  private ContactRepo contactRepo;

  public List<MessageEntity> getAllMessage() {
    return contactRepo.findAll();
  }

  public MessageEntity getMessageById(Long id) {
    return contactRepo.findById(id).orElseThrow(
        () -> new RuntimeException("Message with id " + id + " not found"));
  }

  public void createMessage(MessageEntity Message) {
    contactRepo.save(Message);
  }

  public void updateMessage(MessageEntity Message) {
    contactRepo.findById(Message.getId())
        .orElseThrow(() -> new RuntimeException(
            "Message with id " + Message.getId() + " not found"));
    contactRepo.save(Message);
  }

  public void deleteMessage(Long id) {
    contactRepo.findById(id).orElseThrow(
        () -> new RuntimeException("Message with id " + id + " not found"));
    contactRepo.deleteById(id);
  }


}
