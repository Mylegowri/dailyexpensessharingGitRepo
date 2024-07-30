package com.convin.dailyexpensessharing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    public Expense addExpense(Expense expense){
        return expenseRepository.save(expense);
    }

    public List<Expense> getExpensesByUserId(Long userId) {
        // This should be implemented to filter by user ID
        return expenseRepository.findAll();

    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
}
