import java.util.ArrayList;
import java.util.Scanner;

class Crypto {
    String name;
    double quantity;
    double buyPrice;
    double currentPrice;

    Crypto(String name, double quantity, double buyPrice, double currentPrice) {
        this.name = name;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
        this.currentPrice = currentPrice;
    }

    double investedAmount() {
        return quantity * buyPrice;
    }

    double currentValue() {
        return quantity * currentPrice;
    }

    double profitLoss() {
        return currentValue() - investedAmount();
    }
}

public class CryptoPortfolioTracker {

    static ArrayList<Crypto> portfolio = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n====================================");
            System.out.println("       CRYPTO PORTFOLIO TRACKER");
            System.out.println("====================================");
            System.out.println("1. Add Cryptocurrency");
            System.out.println("2. View Portfolio");
            System.out.println("3. Update Current Price");
            System.out.println("4. Portfolio Summary");
            System.out.println("5. Exit");
            System.out.println("====================================");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addCrypto();
                    break;

                case 2:
                    viewPortfolio();
                    break;

                case 3:
                    updatePrice();
                    break;

                case 4:
                    showSummary();
                    break;

                case 5:
                    System.out.println("\nThank you for using Crypto Portfolio Tracker!");
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }

        } while (choice != 5);

        sc.close();
    }

    // Add cryptocurrency
    static void addCrypto() {

        System.out.println("\n--- Add Cryptocurrency ---");

        System.out.print("Enter cryptocurrency name: ");
        String name = sc.nextLine();

        System.out.print("Enter quantity: ");
        double quantity = sc.nextDouble();

        System.out.print("Enter purchase price: ");
        double buyPrice = sc.nextDouble();

        System.out.print("Enter current price: ");
        double currentPrice = sc.nextDouble();

        Crypto crypto = new Crypto(
                name,
                quantity,
                buyPrice,
                currentPrice
        );

        portfolio.add(crypto);

        System.out.println("\nCryptocurrency added successfully!");
    }

    // Display portfolio
    static void viewPortfolio() {

        if (portfolio.isEmpty()) {
            System.out.println("\nPortfolio is empty.");
            return;
        }

        System.out.println("\n================ PORTFOLIO ================");

        System.out.printf(
                "%-15s %-10s %-15s %-15s %-15s%n",
                "Crypto",
                "Quantity",
                "Buy Price",
                "Current Price",
                "Current Value"
        );

        System.out.println(
                "-----------------------------------------------------------------------"
        );

        for (Crypto crypto : portfolio) {

            System.out.printf(
                    "%-15s %-10.4f %-15.2f %-15.2f %-15.2f%n",
                    crypto.name,
                    crypto.quantity,
                    crypto.buyPrice,
                    crypto.currentPrice,
                    crypto.currentValue()
            );
        }

        System.out.println(
                "======================================================================="
        );
    }

    // Update current price
    static void updatePrice() {

        if (portfolio.isEmpty()) {
            System.out.println("\nPortfolio is empty.");
            return;
        }

        System.out.print("\nEnter cryptocurrency name: ");
        String name = sc.nextLine();

        boolean found = false;

        for (Crypto crypto : portfolio) {

            if (crypto.name.equalsIgnoreCase(name)) {

                System.out.print("Enter new current price: ");
                double newPrice = sc.nextDouble();

                crypto.currentPrice = newPrice;

                System.out.println("\nPrice updated successfully!");

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\nCryptocurrency not found.");
        }
    }

    // Portfolio summary
    static void showSummary() {

        if (portfolio.isEmpty()) {
            System.out.println("\nPortfolio is empty.");
            return;
        }

        double totalInvested = 0;
        double totalCurrentValue = 0;

        for (Crypto crypto : portfolio) {

            totalInvested += crypto.investedAmount();
            totalCurrentValue += crypto.currentValue();
        }

        double profitLoss = totalCurrentValue - totalInvested;

        System.out.println("\n========== PORTFOLIO SUMMARY ==========");

        System.out.printf(
                "Total Invested Amount : %.2f%n",
                totalInvested
        );

        System.out.printf(
                "Current Portfolio Value: %.2f%n",
                totalCurrentValue
        );

        System.out.printf(
                "Profit / Loss          : %.2f%n",
                profitLoss
        );

        if (profitLoss > 0) {
            System.out.println("Status                  : Profit");
        } else if (profitLoss < 0) {
            System.out.println("Status                  : Loss");
        } else {
            System.out.println("Status                  : No Profit / No Loss");
        }

        System.out.println("========================================");
    }
}
