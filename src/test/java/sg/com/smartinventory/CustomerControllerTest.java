package sg.com.smartinventory;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import sg.com.smartinventory.entities.Customer;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @DisplayName("Create customer")
        @Test
        public void createCustomerTest() throws Exception {
                // Step 1: Create a Customer object
                Customer newCustomer = Customer.builder().firstName("Jackie").lastName("Chan").country("Hong Kong")
                                .address("123 HK St")
                                .postalCode(654321).mobileNumber(87654321).email("jackie.chan@example.com")
                                .reviewId(110).build();

                // Step 2: Convert the Java object to JSON using ObjectMapper.
                String newCustomerAsJSON = objectMapper.writeValueAsString(newCustomer);

                // Step 3: Build the request.
                RequestBuilder request = MockMvcRequestBuilders.post("/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newCustomerAsJSON);

                // Step 4: Perform the request and get the response and assert.
                mockMvc.perform(request).andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.firstName").value("Jackie"))
                                .andExpect(jsonPath("$.lastName").value("Chan"));
        }
}