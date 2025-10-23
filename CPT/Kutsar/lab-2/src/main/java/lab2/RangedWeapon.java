package lab2;

/**
 * Промежуточный абстрактный класс для дальнего боя.
 * Дает второй уровень наследования: Weapon -> RangedWeapon -> Bow/MagicWand.
 */
public abstract class RangedWeapon extends Weapon {
    private double range; // максимальная дальность (м)

    public RangedWeapon() {
        this(30.0);
    }

    public RangedWeapon(double range) {
        super();
        this.range = range;
    }

    public RangedWeapon(String name, double damage, double weight, double range) {
        super(name, damage, weight);
        this.range = range;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        if (range <= 0) throw new IllegalArgumentException("Дальность должна быть > 0");
        this.range = range;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", range=%.2f", range);
    }
}
