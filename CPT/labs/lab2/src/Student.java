package src;

public class Student extends UniversityMember {
    private String major; private double gpa; private int year;
    public Student() { this("Иван Петров",19,"S-1001","МГУ","ivan.petrov@example.com",2024,"Информатика",4.2,2); }
    public Student(String name,int age,String id,String university,String email,int startYear,String major,double gpa,int year) {
        super(name,age,id,university,email,startYear); setMajor(major); setGpa(gpa); setYear(year); }
    public String getMajor() { return major; }
    public void setMajor(String major) {
        if(major==null||major.isBlank()) throw new IllegalArgumentException("Специальность пустая");
        this.major=major;
    }
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) {
        if(gpa<0||gpa>5) throw new IllegalArgumentException("GPA 0..5");
        this.gpa=gpa;
    }
    public int getYear() { return year; }
    public void setYear(int year) {
        if(year<=0) throw new IllegalArgumentException("Курс > 0");
        this.year=year;
    }
    public void performDuty() { System.out.printf("Студент %s учится по специальности '%s'.%n", getName(), major); }
    public void study(int hours) { System.out.printf("%s учится %d ч.%n", getName(), Math.max(1,hours)); }
    public String toString() { return super.toString()+String.format(", major='%s', gpa=%.1f, year=%d", major, gpa, year); }
}