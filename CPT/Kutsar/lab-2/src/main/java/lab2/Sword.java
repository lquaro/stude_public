package lab2;

public class Sword extends MeleeWeapon {
    private double bladeLength; // длина клинка, см
    private boolean twoHanded;

    // Конструктор по умолчанию
    public Sword() {
        this("Простой меч", 12.0, 2.5, 1.5, 90.0, false);
    }

    // Конструктор с параметрами
    public Sword(String name, double damage, double weight,
                 double reach, double bladeLength, boolean twoHanded) {
        super(name, damage, weight, reach);
        this.bladeLength = bladeLength;
        this.twoHanded = twoHanded;
    }

    public double getBladeLength() {
        return bladeLength;
    }

    public void setBladeLength(double bladeLength) {
        if (bladeLength <= 0) throw new IllegalArgumentException("Длина клинка > 0");
        this.bladeLength = bladeLength;
    }

    public boolean isTwoHanded() {
        return twoHanded;
    }

    public void setTwoHanded(boolean twoHanded) {
        this.twoHanded = twoHanded;
    }

    // Переопределение поведения атаки
    @Override
    public void attack() {
        System.out.printf("⚔️ Размашистый удар мечом (клинок %.0f см). Урон: %.1f%n",
                bladeLength, getDamage());
    }

    // Доп. поведение
    public void sharpen() {
        setDamage(getDamage() * 1.05); // слегка увеличим урон
        System.out.println("Меч заточен. Урон слегка повышен.");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", bladeLength=%.1f, twoHanded=%s",
                bladeLength, twoHanded);
    }
}
