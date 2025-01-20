import java.util.*;

class Expense {
    private String description;
    private double amount;

    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Description: " + description + ", Amount: $" + String.format("%.2f", amount);
    }
}

public class ExpenseTracker {

    private static ArrayList<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Calculate Total Expenses");
            System.out.println("4. Sort Expenses by Amount");
            System.out.println("5. Search Expense by Description");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addExpense(scanner);
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    calculateTotalExpenses();
                    break;
                case 4:
                    sortExpensesByAmount();
                    break;
                case 5:
                    searchExpenseByDescription(scanner);
                    break;
                case 6:
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addExpense(Scanner scanner) {
        System.out.print("Enter expense description: ");
        String description = scanner.nextLine();

        System.out.print("Enter expense amount: ");
        double amount;
        while (true) {
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount <= 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid amount. Please enter a positive number: ");
            }
        }

        expenses.add(new Expense(description, amount));
        System.out.println("Expense added successfully!");
    }

    private static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            System.out.println("\nRecorded Expenses:");
            for (int i = 0; i < expenses.size(); i++) {
                System.out.println((i + 1) + ". " + expenses.get(i));
            }
        }
    }

    private static void calculateTotalExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded to calculate total.");
        } else {
            double total = 0;
            for (Expense expense : expenses) {
                total += expense.getAmount();
            }
            System.out.println("\nTotal Expenses: $" + String.format("%.2f", total));
        }
    }

    private static void sortExpensesByAmount() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded to sort.");
        } else {
            expenses.sort(Comparator.comparingDouble(Expense::getAmount));
            System.out.println("\nExpenses sorted by amount:");
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }
    }

    private static void searchExpenseByDescription(Scanner scanner) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded to search.");
        } else {
            System.out.print("Enter description keyword to search: ");
            String keyword = scanner.nextLine().toLowerCase();

            List<Expense> results = new ArrayList<>();
            for (Expense expense : expenses) {
                if (expense.getDescription().toLowerCase().contains(keyword)) {
                    results.add(expense);
                }
            }

            if (results.isEmpty()) {
                System.out.println("No matching expenses found.");
            } else {
                System.out.println("\nMatching Expenses:");
                for (Expense result : results) {
                    System.out.println(result);
                }
            }
        }
    }
}
