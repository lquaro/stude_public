package lab2;

/**
 * Промежуточный абстрактный класс для ближнего боя.
 * Дает второй уровень наследования: Weapon -> MeleeWeapon -> Sword.
 */
public abstract class MeleeWeapon extends Weapon {
    private double reach; // радиус поражения (м), для примера

    public MeleeWeapon() {
        this(1.2); // по умолчанию
    }

    public MeleeWeapon(double reach) {
        super();
        this.reach = reach;
    }

    public MeleeWeapon(String name, double damage, double weight, double reach) {
        super(name, damage, weight);
        this.reach = reach;
    }

    public double getReach() {
        return reach;
    }

    public void setReach(double reach) {
        if (reach <= 0) throw new IllegalArgumentException("Дальность должна быть > 0");
        this.reach = reach;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", reach=%.2f", reach);
    }
}
