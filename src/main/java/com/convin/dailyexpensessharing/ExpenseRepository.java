package com.convin.dailyexpensessharing;

import com.convin.dailyexpensessharing.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
