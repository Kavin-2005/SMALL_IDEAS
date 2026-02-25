import java.util.Scanner;

public class ShoppingProject {

    //  Item Search
    public static void searchItem(String[] items, String name) {
        int index = -1;

        for (int i = 0; i < items.length; i++) {
            if (items[i].equalsIgnoreCase(name)) {
                index = i;
                break;
            }
        }

        if (index == -1)
            System.out.println("Item not found");
        else
            System.out.println("Item found at index: " + index);
    }

    //  Average Price
    public static float calculateAveragePrice(float[] prices) {
        float sum = 0;

        for (float p : prices) {
            sum += p;
        }

        return sum / prices.length;
    }

    // Filter Below Price
    public static void filterItemsBelowPrice(String[] items, float[] prices, float threshold) {
        System.out.println("Items below $" + threshold + ":");

        for (int i = 0; i < items.length; i++) {
            if (prices[i] < threshold) {
                System.out.println(items[i]);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] items = {
                "Apple", "Banana", "Bread", "Milk", "Eggs",
                "Cheese", "Chicken", "Rice", "Pasta", "Tomato"
        };

        float[] prices = {
                0.50f, 0.30f, 2.00f, 1.50f, 2.50f,
                3.00f, 5.00f, 1.00f, 1.20f, 0.80f
        };

        //  Inventory Stock
        int[] stock = {10, 20, 15, 10, 12, 8, 5, 25, 18, 30};

        // ---- Search Test ----
        searchItem(items, "Milk");

        // ---- Average Price ----
        float avg = calculateAveragePrice(prices);
        System.out.println("Average price = $" + avg);

        // ---- Filter Items ----
        filterItemsBelowPrice(items, prices, 1.5f);

        // ---- Shopping Loop ----
        float totalBill = 0;

        while (true) {

            System.out.println("\nEnter item name (or finish): ");
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("finish"))
                break;

            int index = -1;

            for (int i = 0; i < items.length; i++) {
                if (items[i].equalsIgnoreCase(input)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Item not found");
                continue;
            }

            System.out.println("Enter quantity: ");
            int qty = sc.nextInt();
            sc.nextLine();

            // Inventory check
            if (stock[index] < qty) {
                System.out.println("Out of stock! Available: " + stock[index]);
                continue;
            }

            stock[index] -= qty;

            float cost = prices[index] * qty;
            totalBill += cost;

            System.out.println("Added. Current total = $" + totalBill);
        }

        // Discount Logic
        float finalBill = totalBill;

        if (totalBill > 100) {
            finalBill = totalBill * 0.9f;
            System.out.println("\nOriginal Bill = $" + totalBill);
            System.out.println("Discount Applied (10%)");
            System.out.println("Final Bill = $" + finalBill);
        } else {
            System.out.println("\nTotal Bill = $" + totalBill);
        }

        sc.close();
    }
}
