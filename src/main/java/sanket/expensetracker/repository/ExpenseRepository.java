package sanket.expensetracker.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import sanket.expensetracker.model.Expense;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ExpenseRepository {

    @Value("${expense.storage.file}")
    private String filePath;

    private final ObjectMapper objectMapper;

    public ExpenseRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Expense> findAllExpenses() {
        try {
            File file = new File(filePath);

            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }

            return objectMapper.readValue(file, new TypeReference<List<Expense>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error reading expenses file.", e);
        }
    }

    public Expense addExpense(Expense expense) {
        List<Expense> expenses = findAllExpenses();

        if (expense.getId() == null) {
            expense.setId(UUID.randomUUID());
        }

        expenses.add(expense);
        saveAllExpenses(expenses);

        return expense;
    }

    public void saveAllExpenses(List<Expense> expenses) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(filePath), expenses);
        } catch (IOException e) {
            throw new RuntimeException("Error saving expenses.", e);
        }
    }

    public Optional<Expense> findExpenseById(UUID id) {
        return findAllExpenses()
                .stream()
                .filter(expense -> expense.getId().equals(id))
                .findFirst();
    }

    public boolean deleteExpense(UUID id) {
        List<Expense> expenses = findAllExpenses();

        boolean removed = expenses.removeIf(expense -> expense.getId().equals(id));

        if (removed) {
            saveAllExpenses(expenses);
        }

        return removed;
    }
}