package src;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final OrderTable orders = new OrderTable();

    public static void main(String[] args) {
        seed();
        while (true) {
            System.out.println("1. Показать количество заказов");
            System.out.println("2. Вставить новый заказ (быстрый пример)");
            System.out.println("3. Найти по номеру");
            System.out.println("4. Удалить по номеру");
            System.out.println("5. Изменить статус");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");
            String cmd = in.nextLine().trim();
            try {
                switch (cmd) {
                    case "1":
                        System.out.printf("Всего заказов: %d.%n", orders.size(), orders.isEmpty());
                        for (Order o : orders.all()) {
                            System.out.println(" - " + o);
                        }
                        break;
                    case "2":
                        quickInsert();
                        break;
                    case "3":
                        System.out.print("Номер: ");
                        int f = Integer.parseInt(in.nextLine());
                        System.out.println(orders.find(f));
                        break;
                    case "4":
                        System.out.print("Номер: ");
                        int d = Integer.parseInt(in.nextLine());
                        System.out.println("Удалён: " + orders.delete(d));
                        break;
                    case "5":
                        System.out.print("Номер: ");
                        int n = Integer.parseInt(in.nextLine());
                        System.out.print("Статус (NEW, PROCESSING, SHIPPED, DELIVERED, CANCELED или номер 1-5): ");
                        String input = in.nextLine().trim();
                        OrderStatus st = null;
                        try {
                            int index = Integer.parseInt(input) - 1;
                            st = OrderStatus.values()[index];
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                            try {
                                st = OrderStatus.valueOf(input.toUpperCase());
                            } catch (IllegalArgumentException ex) {
                                System.out.println("Неверный статус");
                            }
                        }
                        if (st != null) {
                            System.out.println(orders.changeStatus(n, st) ? "ОК" : "Не найден");
                        }
                        break;
                    case "0": return;
                    default: System.out.println("Неизвестная команда.");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
    private static void seed() {
        orders.insert(new Order(5001, LocalDate.now().minusDays(2),
                List.of(new OrderItem("Книга", 2, 12.5), new OrderItem("Лампа", 1, 25.0)),
                OrderStatus.NEW));
        orders.insert(new Order(5002, LocalDate.now().minusDays(1),
                List.of(new OrderItem("Клавиатура", 1, 45.0), new OrderItem("Мышь", 1, 18.0)),
                OrderStatus.PROCESSING));
        orders.insert(new Order(5003, LocalDate.now(),
                List.of(new OrderItem("SSD 1TB", 1, 99.0)),
                OrderStatus.SHIPPED));
    }
    private static void quickInsert() {
        int num = 5500 + (int)(Math.random()*400);
        Order o = new Order(num, LocalDate.now(),
                List.of(new OrderItem("USB кабель", 2, 4.99),
                        new OrderItem("Чехол", 1, 9.99)),
                OrderStatus.NEW);
        orders.insert(o);
        System.out.println("Добавлен: " + o);
    }
}