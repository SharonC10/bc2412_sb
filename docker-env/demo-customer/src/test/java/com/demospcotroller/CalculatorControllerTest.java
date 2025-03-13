package com.demospcotroller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.demospcotroller.controller.impl.Calculator;
import com.demospcotroller.serivce.CalculatorService;

@WebMvcTest (controllers = Calculator.class)
public class CalculatorControllerTest {
  @MockBean
  private CalculatorService calculatorService;

  @Autowired
  private MockMvc mockMvc;

  //! This Unit Test case is to test 2 things:
  //1. jsonPath("$.value") -> the response must be an object
  //2. the logic is to test the result of sum() minus the result of subtract()
  @Test
  void calculateTest()throws Exception{
    Mockito.when(calculatorService.sum(1, 2)).thenReturn(100);
    Mockito.when(calculatorService.subtract(1, 2)).thenReturn(200);
    //test
    mockMvc.perform(get("/calculator/{x}/{y}", 1,2))//
    .andExpect(jsonPath("$.value").value(-100));
  }
}
