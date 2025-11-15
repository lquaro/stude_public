package src;

public abstract class Person {
    private String name; private int age; private String id;
    private static int createdCount = 0;
    public Person() { this("Без имени", 18, "UNKNOWN"); }
    public Person(String name,int age,String id) { setName(name); setAge(age); setId(id); createdCount++; }
    public abstract void performDuty();
    public void greet() { System.out.printf("%s: Привет!%n", name); }
    public void greet(String to) { System.out.printf("%s: Привет, %s!%n", name, to); }
    public void greet(String to,int times) { for(int i=0;i<Math.max(1,times);i++) greet(to); }
    public String getName() { return name; }
    public void setName(String name) {
        if(name==null||name.isBlank()) throw new IllegalArgumentException("Имя не может быть пустым");
        this.name=name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        if(age<=0) throw new IllegalArgumentException("Возраст должен быть > 0");
        this.age=age;
    }
    public String getId() { return id; }
    public void setId(String id) {
        if(id==null||id.isBlank()) throw new IllegalArgumentException("ID не может быть пустым");
        this.id=id;
    }
    public static int getCreatedCount() { return createdCount; }
    public String toString() {
        return String.format("%s{name='%s', age=%d, id='%s'}", getClass().getSimpleName(), name, age, id);
    }
}