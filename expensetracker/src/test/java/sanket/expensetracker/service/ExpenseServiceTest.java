package sanket.expensetracker.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import sanket.expensetracker.dto.ExpenseRequest;
import sanket.expensetracker.dto.ExpenseResponse;
import sanket.expensetracker.dto.ExpenseSummaryResponse;
import sanket.expensetracker.model.Expense;
import sanket.expensetracker.repository.ExpenseRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    void addExpenseTest() {

        ExpenseRequest request = ExpenseRequest.builder()
                .title("Pizza")
                .amount(new BigDecimal("500"))
                .category("Food")
                .date(LocalDate.now())
                .build();

        Expense expense = Expense.builder()
                .id(UUID.randomUUID())
                .title("Pizza")
                .amount(new BigDecimal("500"))
                .category("Food")
                .date(LocalDate.now())
                .build();

        when(expenseRepository.addExpense(any(Expense.class))).thenReturn(expense);

        ExpenseResponse response = expenseService.addExpense(request);

        assertEquals("Pizza", response.getTitle());
        assertEquals("Food", response.getCategory());

        verify(expenseRepository, times(1)).addExpense(any(Expense.class));
    }

    @Test
    void findAllExpensesTest() {

        List<Expense> expenses = List.of(
                Expense.builder()
                        .id(UUID.randomUUID())
                        .title("Pizza")
                        .amount(new BigDecimal("500"))
                        .category("Food")
                        .date(LocalDate.now())
                        .build()
        );

        when(expenseRepository.findAllExpenses()).thenReturn(expenses);

        List<ExpenseResponse> response = expenseService.findAllExpenses();

        assertEquals(1, response.size());
        assertEquals("Pizza", response.get(0).getTitle());
    }

    @Test
    void findExpensesByCategoryTest() {

        List<Expense> expenses = List.of(
                Expense.builder()
                        .id(UUID.randomUUID())
                        .title("Pizza")
                        .amount(new BigDecimal("500"))
                        .category("Food")
                        .date(LocalDate.now())
                        .build(),

                Expense.builder()
                        .id(UUID.randomUUID())
                        .title("Bus")
                        .amount(new BigDecimal("300"))
                        .category("Transport")
                        .date(LocalDate.now())
                        .build()
        );

        when(expenseRepository.findAllExpenses()).thenReturn(expenses);

        List<ExpenseResponse> response = expenseService.findExpensesByCategory("Food");

        assertEquals(1, response.size());
        assertEquals("Food", response.get(0).getCategory());
    }

    @Test
    void getSummaryTest() {

        List<Expense> expenses = List.of(
                Expense.builder()
                        .title("Pizza")
                        .amount(new BigDecimal("500"))
                        .category("Food")
                        .date(LocalDate.now())
                        .build(),

                Expense.builder()
                        .title("Burger")
                        .amount(new BigDecimal("300"))
                        .category("Food")
                        .date(LocalDate.now())
                        .build()
        );

        when(expenseRepository.findAllExpenses()).thenReturn(expenses);

        ExpenseSummaryResponse response = expenseService.getSummary(null);

        assertEquals(new BigDecimal("800"), response.getTotalExpense());
    }

    @Test
    void getSummaryByCategoryTest() {

        List<Expense> expenses = List.of(
                Expense.builder()
                        .title("Pizza")
                        .amount(new BigDecimal("500"))
                        .category("Food")
                        .date(LocalDate.now())
                        .build(),

                Expense.builder()
                        .title("Bus")
                        .amount(new BigDecimal("300"))
                        .category("Transport")
                        .date(LocalDate.now())
                        .build()
        );

        when(expenseRepository.findAllExpenses()).thenReturn(expenses);

        ExpenseSummaryResponse response = expenseService.getSummary("Food");

        assertEquals(new BigDecimal("500"), response.getTotalExpense());
        assertEquals("Food", response.getCategory());
    }

    @Test
    void deleteExpenseTest() {

        UUID id = UUID.randomUUID();

        when(expenseRepository.deleteExpense(id)).thenReturn(true);

        assertDoesNotThrow(() -> expenseService.deleteExpense(id));

        verify(expenseRepository, times(1)).deleteExpense(id);
    }

    @Test
    void deleteExpenseNotFoundTest() {

        UUID id = UUID.randomUUID();

        when(expenseRepository.deleteExpense(id)).thenReturn(false);

        assertThrows(ResponseStatusException.class,
                () -> expenseService.deleteExpense(id));
    }
}