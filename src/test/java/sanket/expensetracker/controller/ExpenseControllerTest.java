package sanket.expensetracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sanket.expensetracker.dto.ExpenseRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ExpenseRequest getExpense() {
        return ExpenseRequest.builder()
                .title("Pizza")
                .amount(new BigDecimal("500"))
                .category("Food")
                .date(LocalDate.now())
                .build();
    }

    @Test
    void addExpenseTest() throws Exception {

        mockMvc.perform(post("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getExpense())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title", is("Pizza")));
    }

    @Test
    void getAllExpensesTest() throws Exception {

        mockMvc.perform(get("/api/expenses"))
                .andExpect(status().isOk());
    }

    @Test
    void getExpensesByCategoryTest() throws Exception {

        mockMvc.perform(get("/api/expenses")
                        .param("category", "Food"))
                .andExpect(status().isOk());
    }

    @Test
    void getTotalExpenseTest() throws Exception {

        mockMvc.perform(get("/api/expenses/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalExpense").exists());
    }

    @Test
    void getCategoryTotalExpenseTest() throws Exception {

        mockMvc.perform(get("/api/expenses/summary")
                        .param("category", "Food"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.category", is("Food")));
    }

    @Test
    void deleteExpenseTest() throws Exception {

        String response = mockMvc.perform(post("/api/expenses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getExpense())))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String id = objectMapper.readTree(response).get("id").asText();

        mockMvc.perform(delete("/api/expenses/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteInvalidExpenseTest() throws Exception
    {

        mockMvc.perform(delete("/api/expenses/" + UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }
}