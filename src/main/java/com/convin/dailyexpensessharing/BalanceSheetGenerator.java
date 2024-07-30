package com.convin.dailyexpensessharing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BalanceSheetGenerator {
    @Autowired
    private UserRepository userRepository;

    public String generateBalanceSheet(List<Expense> expenses) {
        Map<String, Double> balances = new HashMap<>();

        for (Expense expense : expenses) {
            String paidBy = expense.getPaidBy();
            balances.put(paidBy, balances.getOrDefault(paidBy, 0.0) + expense.getAmount());

            for (Expense.Split split : expense.getSplits()) {
                String userId = split.getUserId().toString();
                balances.put(userId, balances.getOrDefault(userId, 0.0) - split.getAmount());
            }
        }

        StringBuilder balanceSheet = new StringBuilder();
        for (Map.Entry<String, Double> entry : balances.entrySet()) {
            User user = userRepository.findById(Long.parseLong(entry.getKey())).orElse(null);
            if (user != null) {
                balanceSheet.append(user.getName()).append(": ").append(entry.getValue()).append("\n");
            }
        }

        return balanceSheet.toString();
    }
}
