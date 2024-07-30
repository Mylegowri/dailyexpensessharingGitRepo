package com.convin.dailyexpensessharing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private BalanceSheetGenerator balanceSheetGenerator;
    @PostMapping("/add")
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense){
        if(!ValidationUtils.validateExpense(expense)){
            return ResponseEntity.badRequest().build();
        }
        Expense createdExpense=expenseService.addExpense(expense);
        return ResponseEntity.ok(createdExpense);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expense>> getExpensesByUserId(@PathVariable Long userId) {
        List<Expense> expenses = expenseService.getExpensesByUserId(userId);
        return ResponseEntity.ok(expenses);
    }
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }
    @GetMapping("/balance-sheet")
    public ResponseEntity<String> getBalanceSheet() {
        List<Expense> expenses = expenseService.getAllExpenses();
        String balanceSheet = balanceSheetGenerator.generateBalanceSheet(expenses);
        return ResponseEntity.ok(balanceSheet);
    }
}
