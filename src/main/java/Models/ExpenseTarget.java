package Models;

public class ExpenseTarget {
    public int expenseTargetId;
    public String name;
    public ExpenseTarget(int expenseTargetId, String name) {
        this.expenseTargetId = expenseTargetId;
        this.name = name;
    }
    public int getExpenseTargetId() {
        return expenseTargetId;
    }
    public void setExpenseTargetId(int expenseTargetId) {
        this.expenseTargetId = expenseTargetId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
