package lab2;

/**
 * Базовый абстрактный класс Оружие.
 * Демонстрирует: абстракцию, инкапсуляцию, наследование,
 * статическую переменную (счетчик созданных объектов),
 * перегрузку методов (attack()).
 */
public abstract class Weapon {
    // Инкапсуляция: приватные поля
    private String name;
    private double damage; // базовый урон за удар/выстрел/заклинание
    private double weight; // масса, кг

    // Статический счётчик созданных экземпляров (всех оружий)
    private static int createdCount = 0;

    // Конструктор по умолчанию
    public Weapon() {
        this("Безымянное оружие", 1.0, 1.0);
    }

    // Конструктор с параметрами
    public Weapon(String name, double damage, double weight) {
        this.name = name;
        this.damage = damage;
        this.weight = weight;
        createdCount++;
    }

    // Абстрактный метод: конкретные классы обязаны переопределить
    public abstract void attack();

    // Перегрузка (overload) — та же операция с параметрами
    public void attack(String target) {
        System.out.printf("%s атакует цель: %s. ", name, target);
        attack(); // делегируем конкретной реализации
    }

    public void attack(String target, int times) {
        System.out.printf("%s атакует цель %s %d раз(а).%n", name, target, times);
        for (int i = 0; i < times; i++) {
            attack();
        }
    }

    // Полиморфный «подсчёт ДПС»: может быть переопределён
    public double dps(double actionsPerSecond) {
        return damage * actionsPerSecond;
    }

    // Геттеры/сеттеры (инкапсуляция)
    public String getName() {
        return name;
    }

    public double getDamage() {
        return damage;
    }

    public double getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(double damage) {
        if (damage <= 0) throw new IllegalArgumentException("Урон должен быть > 0");
        this.damage = damage;
    }

    public void setWeight(double weight) {
        if (weight <= 0) throw new IllegalArgumentException("Вес должен быть > 0");
        this.weight = weight;
    }

    public static int getCreatedCount() {
        return createdCount;
    }

    // Переопределение toString (override)
    @Override
    public String toString() {
        return String.format("%s{name='%s', damage=%.2f, weight=%.2f}",
                getClass().getSimpleName(), name, damage, weight);
    }
}
