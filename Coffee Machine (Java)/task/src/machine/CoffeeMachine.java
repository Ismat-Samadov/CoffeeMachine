package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int coffeeBeans;
    private int disposableCups;
    private int money;

    public CoffeeMachine() {
        this.water = 400;
        this.milk = 540;
        this.coffeeBeans = 120;
        this.disposableCups = 9;
        this.money = 550;
    }

    public void processInput(String input) {
        switch (input) {
            case "remaining":
                displayCoffeeMachineStatus();
                break;
            case "buy":
                buyCoffee();
                break;
            case "fill":
                fillSupplies();
                break;
            case "take":
                takeMoney();
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid action!");
                break;
        }
    }

    public void displayCoffeeMachineStatus() {
        System.out.println("\nThe coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffeeBeans + " g of coffee beans");
        System.out.println(disposableCups + " disposable cups");
        System.out.println("$" + money + " of money\n");
    }

    public void buyCoffee() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        String choice = scanner.next();

        if (choice.equals("back")) {
            return;
        }

        int waterNeeded, milkNeeded, coffeeBeansNeeded, cost;

        switch (choice) {
            case "1": // Espresso
                waterNeeded = 250;
                milkNeeded = 0;
                coffeeBeansNeeded = 16;
                cost = 4;
                break;
            case "2": // Latte
                waterNeeded = 350;
                milkNeeded = 75;
                coffeeBeansNeeded = 20;
                cost = 7;
                break;
            case "3": // Cappuccino
                waterNeeded = 200;
                milkNeeded = 100;
                coffeeBeansNeeded = 12;
                cost = 6;
                break;
            default:
                System.out.println("Invalid coffee choice!");
                return;
        }

        if (water >= waterNeeded && milk >= milkNeeded && coffeeBeans >= coffeeBeansNeeded && disposableCups >= 1) {
            System.out.println("I have enough resources, making you a coffee!");
            water -= waterNeeded;
            milk -= milkNeeded;
            coffeeBeans -= coffeeBeansNeeded;
            money += cost;
            disposableCups--;
        } else {
            String missingResource = (water < waterNeeded) ? "water" :
                    (milk < milkNeeded) ? "milk" :
                            (coffeeBeans < coffeeBeansNeeded) ? "coffee beans" :
                                    "disposable cups";
            System.out.println("Sorry, not enough " + missingResource + "!");
        }
    }

    public void fillSupplies() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write how many ml of water you want to add: ");
        int addedWater = scanner.nextInt();
        System.out.print("Write how many ml of milk you want to add: ");
        int addedMilk = scanner.nextInt();
        System.out.print("Write how many grams of coffee beans you want to add: ");
        int addedCoffeeBeans = scanner.nextInt();
        System.out.print("Write how many disposable cups you want to add: ");
        int addedCups = scanner.nextInt();

        water += addedWater;
        milk += addedMilk;
        coffeeBeans += addedCoffeeBeans;
        disposableCups += addedCups;
    }

    public void takeMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Write action (buy, fill, take, remaining, exit): ");
            String input = scanner.next();
            coffeeMachine.processInput(input);
        }
    }
}
