package sanket.expensetracker.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sanket.expensetracker.dto.ExpenseRequest;
import sanket.expensetracker.dto.ExpenseResponse;
import sanket.expensetracker.dto.ExpenseSummaryResponse;
import sanket.expensetracker.service.ExpenseService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    // Add Expense
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExpenseResponse addExpense(@Valid @RequestBody ExpenseRequest request) {
        return expenseService.addExpense(request);
    }

    // View All Expenses / Filter by Category
    @GetMapping
    public List<ExpenseResponse> getExpenses(
            @RequestParam(required = false) String category) {

        if (category == null || category.isBlank()) {
            return expenseService.findAllExpenses();
        }

        return expenseService.findExpensesByCategory(category);
    }

    // Calculate Total Expenses
    @GetMapping("/summary")
    public ExpenseSummaryResponse getSummary(
            @RequestParam(required = false) String category) {

        if (category == null || category.isBlank()) {
            return expenseService.getSummary(null);
        }

        return expenseService.getSummary(category);
    }

    // Delete Expense
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpense(@PathVariable UUID id) {
        expenseService.deleteExpense(id);
    }
}