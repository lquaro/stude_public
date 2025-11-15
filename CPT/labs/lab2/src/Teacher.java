package src;

public class Teacher extends UniversityMember {
    private String department; private String title; private double salary;
    public Teacher() { this("Сергей Смирнов",45,"T-5001","МГУ","sergey.smirnov@example.com",2010,"МатМех","Профессор",150000); }
    public Teacher(String name,int age,String id,String university,String email,int startYear,String department,String title,double salary) {
        super(name,age,id,university,email,startYear); setDepartment(department); setTitle(title); setSalary(salary); }
    public String getDepartment() { return department; }
    public void setDepartment(String department) {
        if(department==null||department.isBlank()) throw new IllegalArgumentException("Кафедра пустая");
        this.department=department;
    }
    public String getTitle() { return title; }
    public void setTitle(String title) {
        if(title==null||title.isBlank()) throw new IllegalArgumentException("Должность пустая");
        this.title=title;
    }
    public double getSalary() { return salary; }
    public void setSalary(double salary) {
        if(salary<0) throw new IllegalArgumentException("Зарплата >= 0");
        this.salary=salary;
    }
    public void performDuty() { System.out.printf("Преподаватель %s ведёт занятия на кафедре %s.%n", getName(), department); }
    public void teach(String course) { System.out.printf("%s читает курс: %s.%n", getName(), course); }
    public String toString() { return super.toString()+String.format(", department='%s', title='%s', salary=%.2f", department, title, salary); }
}