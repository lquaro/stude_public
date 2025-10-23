package lab2;

public class MagicWand extends RangedWeapon {
    private String element; // например: fire/ice/arcane
    private double mana;    // текущая мана

    public MagicWand() {
        this("Палочка ученика", 14.0, 0.4, 50.0, "arcane", 50.0);
    }

    public MagicWand(String name, double damage, double weight, double range,
                     String element, double mana) {
        super(name, damage, weight, range);
        this.element = element == null ? "arcane" : element;
        this.mana = Math.max(0, mana);
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        if (element == null || element.isBlank())
            throw new IllegalArgumentException("Элемент не может быть пустым");
        this.element = element;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = Math.max(0, mana);
    }

    @Override
    public void attack() {
        double cost = 5.0;
        if (mana < cost) {
            System.out.println("✨ Недостаточно маны для заклинания.");
            return;
        }
        mana -= cost;
        System.out.printf("✨ Заклинание (%s). Урон: %.1f. Осталось маны: %.1f%n",
                element, getDamage(), mana);
    }

    public void recharge(double amount) {
        if (amount <= 0) return;
        mana += amount;
        System.out.printf("Восполнено маны: +%.1f. Теперь: %.1f%n", amount, mana);
    }

    // Свой вариант ДПС: учитываем стоимость маны на «каст»
    @Override
    public double dps(double castsPerSecond) {
        double costPerCast = 5.0;
        double maxCastsByMana = mana / costPerCast; // сколько «потянем» прямо сейчас
        double effectiveCasts = Math.min(castsPerSecond, maxCastsByMana);
        return getDamage() * effectiveCasts;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", element='%s', mana=%.1f",
                element, mana);
    }
}
