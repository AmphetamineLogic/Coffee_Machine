package machine;

import java.util.Arrays;
import java.util.Scanner;

public class CoffeeMachine {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CoffeeMachine6 machine = new CoffeeMachine6();
        String input;
        machine.invite();

        while (machine.state != CoffeeMachine6.State.OFF) {
            input = scanner.nextLine();
            machine.manage(input);
        }
    }
}

class CoffeeMachine6 {
    enum State {
        MAINMENU,
        MAKINGCOFFEE,
        FILLINGWATER,
        FILLINGMILK,
        FILLINGCOFFEEBEANS,
        FILLINGCUPS,
        INCASSATION,
        REPORTING,
        OFF
    }

    int water, money, milk, coffeeBeans, cups;
    State state;

    CoffeeMachine6() {
        water = 400;
        money = 550;
        milk = 540;
        coffeeBeans = 120;
        cups = 9;
        state = State.MAINMENU;
    }

    public void manage(String input) {
        switch (state) {
            case MAINMENU:
                switch (input) {
                    case "buy":
                        state = State.MAKINGCOFFEE;
                        displayCoffeeStyles();
                        break;
                    case "remaining":
                        state = State.REPORTING;
                        this.printState();
                        state = State.MAINMENU;
                        break;
                    case "take":
                        state = State.INCASSATION;
                        this.take();
                        state = State.MAINMENU;
                        break;
                    case "fill":
                        state = State.FILLINGWATER;
                        System.out.println("Write how many ml of water do you want to add:");
                        break;
                    case "exit":
                        state = State.OFF;
                        break;
                }
                break;

            case FILLINGWATER:
                addWater(input);
                state = State.FILLINGMILK;
                System.out.println("Write how many ml of milk do you want to add:");
                break;
            case FILLINGMILK:
                addMilk(input);
                state = State.FILLINGCOFFEEBEANS;
                System.out.println("Write how many grams of coffee beans do you want to add:");
                break;
            case FILLINGCOFFEEBEANS:
                addCoffeeBeans(input);
                state = State.FILLINGCUPS;
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                break;
            case FILLINGCUPS:
                addCups(input);
                state = State.MAINMENU;
                invite();
                break;
            case MAKINGCOFFEE:
                buy(input);
                break;
        }
    }

    public void printState() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffeeBeans + " of coffee beans");
        System.out.println(cups + " of cups");
        System.out.println(money + " of money");
        invite();
    }


    public void addWater(String amount) {
        water += Integer.valueOf(amount);
    }

    public void addMilk(String amount) {
        milk += Integer.valueOf(amount);
    }

    public void addCoffeeBeans(String amount) {
        coffeeBeans += Integer.valueOf(amount);
    }

    public void addCups(String amount) {
        cups += Integer.valueOf(amount);
    }

    void displayCoffeeStyles() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
    }

    public void buy(String coffeeStyle) {

        if (coffeeStyle.equals("1")) {
            if (checkResourses("1")) {
                System.out.println("I have enough resources, making you a coffee!");
                water = water - 250;
                coffeeBeans = coffeeBeans - 16;
                cups--;
                money = money + 4;
            }
        }

        if (coffeeStyle.equals("2")) {
            if (checkResourses("2")) {
                System.out.println("I have enough resources, making you a coffee!");
                water = water - 350;
                milk -= 75;
                coffeeBeans -= 20;
                cups--;
                money += 7;
            }
        }

        if (coffeeStyle.equals("3")) {
            if (checkResourses("3")) {
                System.out.println("I have enough resources, making you a coffee!");
                water -= 200;
                milk -= 100;
                coffeeBeans -= 12;
                cups--;
                money += 6;
            }
        }
        if (coffeeStyle.equals("back")) {
            return;
        }
        state = State.MAINMENU;
        invite();
    }

    public boolean checkResourses(String coffeeStyle) {
        boolean enough = false;
        switch (coffeeStyle) {
            case "1":
                if (water < 250) {
                    System.out.println("Sorry, not enough water!");
                }
                else if (milk < 75) {
                    System.out.println("Sorry, not enough milk!");
                }
                else if (coffeeBeans < 20) {
                    System.out.println("Sorry, not enough coffee beans!");
                }
                else if (cups < 1) {
                    System.out.println("Sorry, not enough disposable cups!");
                }
                else enough = true;
                break;
            case "2":
                if (water < 350) {
                    System.out.println("Sorry, not enough water!");
                }
                else if (coffeeBeans < 16) {
                    System.out.println("Sorry, not enough coffee beans!");
                }
                else if (cups < 1) {
                    System.out.println("Sorry, not enough disposable cups!");
                }
                else enough = true;
                break;
            case "3":
                if (water < 200) {
                    System.out.println("Sorry, not enough water!");
                }
                else if (milk < 100) {
                    System.out.println("Sorry, not enough milk!");
                }
                else if (coffeeBeans < 12) {
                    System.out.println("Sorry, not enough coffee beans!");
                }
                else if (cups < 1) {
                    System.out.println("Sorry, not enough disposable cups!");
                }
                else enough = true;
                break;
        }
        return enough;
    }

    public void take() {
        System.out.printf("I gave you $%d\n", money);
        money = 0;
        invite();
    }

    public void invite() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }
}
