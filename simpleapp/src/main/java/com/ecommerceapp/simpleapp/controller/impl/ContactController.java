package com.ecommerceapp.simpleapp.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerceapp.simpleapp.controller.ContactOperation;
import com.ecommerceapp.simpleapp.entity.MessageEntity;
import com.ecommerceapp.simpleapp.service.impl.ContactService;

@Controller
public class ContactController implements ContactOperation{
  @Autowired
  private ContactService contactService;

  public String sendMessage(MessageEntity message, Model model) {
    contactService.createMessage(message);
    model.addAttribute("confirmation",
        "Your message has been successfully sent!!");

    return "ContactUs";
  }


}
