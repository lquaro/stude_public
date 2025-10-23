package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Консольная демка:
 * - создаем разные виды оружия
 * - показываем статический счетчик
 * - показываем полиморфизм (список Weapon, вызов attack()/dps())
 * - демонстрируем ввод/вывод пользователя
 */
public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final List<Weapon> arsenal = new ArrayList<>();

    public static void main(String[] args) {
        seed(); // стартовые объекты

        while (true) {
            System.out.println("\n=== ЛР-2: ООП. Иерархия «Оружие» ===");
            System.out.println("1) Показать арсенал");
            System.out.println("2) Добавить Меч");
            System.out.println("3) Добавить Лук");
            System.out.println("4) Добавить Волшебную палочку");
            System.out.println("5) Атаковать всем по цели");
            System.out.println("6) Показать DPS (по 2 действия/сек)");
            System.out.println("7) Показать общий счетчик созданных объектов");
            System.out.println("0) Выход");
            System.out.print("Выбор: ");
            String cmd = in.nextLine().trim();

            switch (cmd) {
                case "1":
                    showArsenal();
                    break;
                case "2":
                    addSwordByInput();
                    break;
                case "3":
                    addBowByInput();
                    break;
                case "4":
                    addWandByInput();
                    break;
                case "5":
                    System.out.print("Цель: ");
                    String target = in.nextLine().trim();
                    attackAll(target);
                    break;
                case "6":
                    showDpsAll(2.0);
                    break;
                case "7":
                    System.out.printf("Всего создано оружия: %d%n", Weapon.getCreatedCount());
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
        arsenal.add(new Sword()); // по умолчанию
        arsenal.add(new Bow());   // по умолчанию
        arsenal.add(new MagicWand()); // по умолчанию

        // Пара кастомных экземпляров
        arsenal.add(new Sword("Клеймор", 18, 3.5, 1.8, 110, true));
        arsenal.add(new Bow("Длинный лук", 11, 1.3, 120, 22, 6));
        arsenal.add(new MagicWand("Огненная палочка", 16, 0.5, 60, "fire", 30));
    }

    private static void showArsenal() {
        if (arsenal.isEmpty()) {
            System.out.println("Арсенал пуст.");
            return;
        }
        for (int i = 0; i < arsenal.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, arsenal.get(i));
        }
    }

    private static void attackAll(String target) {
        if (arsenal.isEmpty()) {
            System.out.println("Арсенал пуст.");
            return;
        }
        // Полиморфизм: у всех Weapon есть attack(String)
        for (Weapon w : arsenal) {
            w.attack(target);
        }
        // Показ перегрузки: атакуем последним трижды
        System.out.println("-- Перегрузка: последним оружием атакуем 3 раза --");
        arsenal.get(arsenal.size() - 1).attack(target, 3);
    }

    private static void showDpsAll(double aps) {
        if (arsenal.isEmpty()) {
            System.out.println("Арсенал пуст.");
            return;
        }
        System.out.printf("DPS при %.1f действий/сек:%n", aps);
        for (Weapon w : arsenal) {
            System.out.printf(" - %-25s : %.2f%n", w.getName(), w.dps(aps));
        }
    }

    private static void addSwordByInput() {
        try {
            System.out.print("Название: ");
            String name = in.nextLine();
            System.out.print("Урон: ");
            double dmg = Double.parseDouble(in.nextLine());
            System.out.print("Вес (кг): ");
            double weight = Double.parseDouble(in.nextLine());
            System.out.print("Дальность (м): ");
            double reach = Double.parseDouble(in.nextLine());
            System.out.print("Длина клинка (см): ");
            double blade = Double.parseDouble(in.nextLine());
            System.out.print("Двуручный? (true/false): ");
            boolean twoH = Boolean.parseBoolean(in.nextLine());

            Sword s = new Sword(name, dmg, weight, reach, blade, twoH);
            arsenal.add(s);
            System.out.println("Меч добавлен: " + s);
        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }

    private static void addBowByInput() {
        try {
            System.out.print("Название: ");
            String name = in.nextLine();
            System.out.print("Урон: ");
            double dmg = Double.parseDouble(in.nextLine());
            System.out.print("Вес (кг): ");
            double weight = Double.parseDouble(in.nextLine());
            System.out.print("Дальность (м): ");
            double range = Double.parseDouble(in.nextLine());
            System.out.print("Сила натяжения (кгс): ");
            double draw = Double.parseDouble(in.nextLine());
            System.out.print("Стрел (шт): ");
            int arrows = Integer.parseInt(in.nextLine());

            Bow b = new Bow(name, dmg, weight, range, draw, arrows);
            arsenal.add(b);
            System.out.println("Лук добавлен: " + b);
        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }

    private static void addWandByInput() {
        try {
            System.out.print("Название: ");
            String name = in.nextLine();
            System.out.print("Урон: ");
            double dmg = Double.parseDouble(in.nextLine());
            System.out.print("Вес (кг): ");
            double weight = Double.parseDouble(in.nextLine());
            System.out.print("Дальность (м): ");
            double range = Double.parseDouble(in.nextLine());
            System.out.print("Стихия (fire/ice/arcane/...): ");
            String element = in.nextLine();
            System.out.print("Мана: ");
            double mana = Double.parseDouble(in.nextLine());

            MagicWand w = new MagicWand(name, dmg, weight, range, element, mana);
            arsenal.add(w);
            System.out.println("Палочка добавлена: " + w);
        } catch (Exception e) {
            System.out.println("Ошибка ввода: " + e.getMessage());
        }
    }
}
