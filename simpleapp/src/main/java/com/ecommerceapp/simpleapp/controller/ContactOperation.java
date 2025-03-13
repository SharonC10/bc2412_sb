package com.ecommerceapp.simpleapp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import com.ecommerceapp.simpleapp.entity.MessageEntity;

public interface ContactOperation {

  @PostMapping("/send/message")
  String sendMessage(MessageEntity message, Model model);
}
