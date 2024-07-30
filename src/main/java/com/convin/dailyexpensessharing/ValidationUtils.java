package com.convin.dailyexpensessharing;

import com.convin.dailyexpensessharing.Expense;

public class ValidationUtils {
    public static boolean validateExpense(Expense expense){
        return switch (expense.getSplitType()) {
            case "exact" -> validateExactSplit(expense);
            case "percentage" -> validatePercentageSplit(expense);
            case "equal" -> validateEqualSplit(expense);
            default -> false;
        };
    }
    private static boolean validateExactSplit(Expense expense) {
        double total = expense.getSplits().stream().mapToDouble(Expense.Split::getAmount).sum();
        return total == expense.getAmount();
    }

    private static boolean validatePercentageSplit(Expense expense) {
        double totalPercentage = expense.getSplits().stream().mapToDouble(Expense.Split::getAmount).sum();
        return totalPercentage == 100.0;
    }

    private static boolean validateEqualSplit(Expense expense) {
        int numParticipants = expense.getSplits().size();
        double expectedAmount = expense.getAmount() / numParticipants;
        return expense.getSplits().stream().allMatch(split -> split.getAmount() == expectedAmount);
    }
}
