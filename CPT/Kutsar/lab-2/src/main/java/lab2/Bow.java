package lab2;

public class Bow extends RangedWeapon {
    private double drawWeight; // сила натяжения (кгс)
    private int arrows;        // кол-во стрел

    public Bow() {
        this("Охотничий лук", 9.0, 1.2, 80.0, 18.0, 10);
    }

    public Bow(String name, double damage, double weight, double range,
               double drawWeight, int arrows) {
        super(name, damage, weight, range);
        this.drawWeight = drawWeight;
        this.arrows = Math.max(0, arrows);
    }

    public double getDrawWeight() {
        return drawWeight;
    }

    public void setDrawWeight(double drawWeight) {
        if (drawWeight <= 0) throw new IllegalArgumentException("Сила натяжения > 0");
        this.drawWeight = drawWeight;
    }

    public int getArrows() {
        return arrows;
    }

    public void setArrows(int arrows) {
        this.arrows = Math.max(0, arrows);
    }

    @Override
    public void attack() {
        if (arrows <= 0) {
            System.out.println("🏹 Стрел нет! Выстрел невозможен.");
            return;
        }
        arrows--;
        System.out.printf("🏹 Выстрел из лука (сила натяжения %.1f кгс). Урон: %.1f. Осталось стрел: %d%n",
                drawWeight, getDamage(), arrows);
    }

    public void reload(int add) {
        if (add <= 0) return;
        arrows += add;
        System.out.printf("Пополнено стрел: +%d. Всего: %d%n", add, arrows);
    }

    // Покажем полиморфизм — лук «наказывает» ДПС уменьшением из-за перезарядки
    @Override
    public double dps(double shotsPerSecond) {
        // простая модель: каждые 10 выстрелов теряем 1 «шот» на перезарядку
        double penalty = Math.floor(shotsPerSecond / 10.0);
        return getDamage() * Math.max(0, shotsPerSecond - penalty);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", drawWeight=%.1f, arrows=%d",
                drawWeight, arrows);
    }
}
