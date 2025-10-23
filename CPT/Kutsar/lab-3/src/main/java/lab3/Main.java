package lab3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final OrderTable orders = new OrderTable();

    public static void main(String[] args) {
        seed();
        while (true) {
            System.out.println("\n=== ЛР-3: Хэш-таблица заказов ===");
            System.out.println("1) Показать количество заказов");
            System.out.println("2) Вставить новый заказ");
            System.out.println("3) Найти заказ по номеру");
            System.out.println("4) Удалить заказ по номеру");
            System.out.println("5) Изменить статус заказа");
            System.out.println("6) Создать примерный заказ (быстрый)");
            System.out.println("0) Выход");
            System.out.print("Выбор: ");
            String cmd = in.nextLine().trim();
            switch (cmd) {
                case "1":
                    System.out.printf("Всего заказов: %d. Пусто? %s%n", orders.size(), orders.isEmpty());
                    break;
                case "2":
                    insertByInput();
                    break;
                case "3":
                    System.out.print("Номер заказа: ");
                    int num3 = Integer.parseInt(in.nextLine());
                    Order o3 = orders.find(num3);
                    System.out.println(o3 == null ? "Не найден." : o3);
                    break;
                case "4":
                    System.out.print("Номер заказа: ");
                    int num4 = Integer.parseInt(in.nextLine());
                    Order removed = orders.delete(num4);
                    System.out.println(removed == null ? "Не найден, не удалён." : ("Удалён: " + removed));
                    break;
                case "5":
                    System.out.print("Номер заказа: ");
                    int num5 = Integer.parseInt(in.nextLine());
                    System.out.print("Новый статус (NEW,PROCESSING,SHIPPED,DELIVERED,CANCELED): ");
                    OrderStatus st = OrderStatus.valueOf(in.nextLine().trim().toUpperCase());
                    boolean ok = orders.changeStatus(num5, st);
                    System.out.println(ok ? "Статус обновлён." : "Заказ не найден.");
                    break;
                case "6":
                    quickDemoOrder();
                    break;
                case "0":
                    System.out.println("Пока!");
                    return;
                default:
                    System.out.println("Неизвестная команда.");
            }
        }
    }

    private static void seed() {
        List<Item> items1 = List.of(new Item("Книга", 2, 12.5), new Item("Лампа", 1, 25.0));
        orders.insert(new Order(1001, LocalDate.now().minusDays(1), items1, OrderStatus.NEW));
        List<Item> items2 = List.of(new Item("Клавиатура", 1, 45.0), new Item("Мышь", 1, 18.0));
        orders.insert(new Order(1002, LocalDate.now(), items2, OrderStatus.PROCESSING));
    }

    private static void insertByInput() {
        try {
            System.out.print("Номер заказа (целое > 0): ");
            int number = Integer.parseInt(in.nextLine());
            System.out.print("Дата (YYYY-MM-DD): ");
            String dateStr = in.nextLine().trim();
            var date = LocalDate.parse(dateStr);

            List<Item> items = new ArrayList<>();
            while (true) {
                System.out.print("Товар (имя или пусто для окончания): ");
                String name = in.nextLine().trim();
                if (name.isEmpty()) break;
                System.out.print("Кол-во: ");
                int qty = Integer.parseInt(in.nextLine());
                System.out.print("Цена: ");
                double price = Double.parseDouble(in.nextLine());
                items.add(new Item(name, qty, price));
            }
            Order order = new Order(number, date, items, OrderStatus.NEW);
            orders.insert(order);
            System.out.println("Добавлен: " + order);
        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }

    private static void quickDemoOrder() {
        int number = 1000 + (int)(Math.random()*9000);
        List<Item> items = List.of(new Item("USB кабель", 2, 4.99), new Item("Чехол", 1, 9.99));
        Order order = new Order(number, LocalDate.now(), items, OrderStatus.NEW);
        orders.insert(order);
        System.out.println("Добавлен пример: " + order);
    }
}
