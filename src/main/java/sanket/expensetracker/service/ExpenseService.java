package sanket.expensetracker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sanket.expensetracker.dto.ExpenseRequest;
import sanket.expensetracker.dto.ExpenseResponse;
import sanket.expensetracker.dto.ExpenseSummaryResponse;
import sanket.expensetracker.model.Expense;
import sanket.expensetracker.repository.ExpenseRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    // Get all expenses
    public List<ExpenseResponse> findAllExpenses() {
        return expenseRepository.findAllExpenses()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // Get expenses by category
    public List<ExpenseResponse> findExpensesByCategory(String category) {
        return expenseRepository.findAllExpenses()
                .stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                .map(this::toResponse)
                .toList();
    }

    // Add expense
    public ExpenseResponse addExpense(ExpenseRequest request) {

        Expense expense = Expense.builder()
                .title(request.getTitle())
                .amount(request.getAmount())
                .category(request.getCategory())
                .date(request.getDate())
                .build();

        Expense savedExpense = expenseRepository.addExpense(expense);

        return toResponse(savedExpense);
    }

    // Get summary
    public ExpenseSummaryResponse getSummary(String category) {

        BigDecimal total;

        if (category == null || category.isBlank()) {
            total = calculateTotalExpenses();
        } else {
            total = calculateTotalExpensesByCategory(category);
        }

        return ExpenseSummaryResponse.builder()
                .category(category)
                .totalExpense(total)
                .build();
    }

    // Calculate total expenses
    private BigDecimal calculateTotalExpenses() {

        return expenseRepository.findAllExpenses()
                .stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Calculate total expenses by category
    private BigDecimal calculateTotalExpensesByCategory(String category) {

        return expenseRepository.findAllExpenses()
                .stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Delete expense
    public void deleteExpense(UUID id) {

        if (!expenseRepository.deleteExpense(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Expense not found with id: " + id);
        }
    }

    // Convert Entity -> DTO
    private ExpenseResponse toResponse(Expense expense) {

        return ExpenseResponse.builder()
                .id(expense.getId())
                .title(expense.getTitle())
                .amount(expense.getAmount())
                .category(expense.getCategory())
                .date(expense.getDate())
                .build();
    }
}