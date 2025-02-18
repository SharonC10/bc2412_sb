package com.bootcamp.demo_simple_calculator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.nio.file.OpenOption;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.bootcamp.demo_simple_calculator.Controller.impl.BcControllerImpl;
import com.bootcamp.demo_simple_calculator.Model.BcCalculatorAns;
import com.bootcamp.demo_simple_calculator.Model.BcCalculatorQuestion;
import com.bootcamp.demo_simple_calculator.Model.Operation;
import com.bootcamp.demo_simple_calculator.Service.BcCalculatorService;

@WebMvcTest (controllers = BcControllerImpl.class)
public class BcControllerTest {
  @MockBean
  private BcCalculatorService bcCalculatorService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testGetOperation() throws Exception {
    Mockito.when(bcCalculatorService.result(new BcCalculatorQuestion("1","2",Operation.SUM)))
    .thenReturn(new BcCalculatorAns(1.0, 2.0, Operation.SUM, 3.0));
    Mockito.when(bcCalculatorService.result(new BcCalculatorQuestion("1","2",Operation.SUBTRACT)))
    .thenReturn(new BcCalculatorAns(1.0, 2.0, Operation.SUBTRACT, -1.0));
    Mockito.when(bcCalculatorService.result(new BcCalculatorQuestion("1","2",Operation.MULTIPLY)))
    .thenReturn(new BcCalculatorAns(1.0, 2.0, Operation.MULTIPLY, 2.0));
    Mockito.when(bcCalculatorService.result(new BcCalculatorQuestion("1","2",Operation.DIVIDE)))
    .thenReturn(new BcCalculatorAns(1.0, 2.0, Operation.DIVIDE, 0.5));
    Mockito.when(bcCalculatorService.result(new BcCalculatorQuestion("10","3",Operation.DIVIDE)))
    .thenReturn(new BcCalculatorAns(10.0, 3.0, Operation.DIVIDE, 3.33333));

    // mockMvc.perform(get("/operation/{x}/{y}/{operation}",1,2,Operation.SUM))
    // .andExpect(jsonPath("$.value").value(3.0));
  }
  
}
