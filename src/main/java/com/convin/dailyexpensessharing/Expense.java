package com.convin.dailyexpensessharing;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private double amount;
    private String paidBy;
    private String splitType;

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public String getSplitType() {
        return splitType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public void setSplitType(String splitType) {
        this.splitType = splitType;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Split> splits;

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    @Data
    @Entity
    public static class Split{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long userId;
        private double amount;

        public Long getId() {
            return id;
        }

        public Long getUserId() {
            return userId;
        }

        public double getAmount() {
            return amount;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }
}
