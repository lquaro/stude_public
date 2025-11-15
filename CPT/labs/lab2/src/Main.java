package src;
import java.util.*;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final List<src.Person> roster = new ArrayList<>();
    public static void main(String[] args){
        seed();
        while(true) {
            System.out.println("1. Показать счётчик и список людей");
            System.out.println("2. Добавить Студента");
            System.out.println("3. Добавить Преподавателя");
            System.out.println("4. Добавить Ассистента");
            System.out.println("5. Все здороваются с именем");
            System.out.println("6. Показать обязанности");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");
            String cmd = in.nextLine().trim();
            switch(cmd) {
                case "1":
                    System.out.printf("Всего создано людей: %d%n", Person.getCreatedCount());
                    for (int i = 0; i < roster.size(); i++)
                        System.out.printf("%d) %s%n", i + 1, roster.get(i));
                    break;
                case "2": addStudent(); break;
                case "3": addTeacher(); break;
                case "4": addTA(); break;
                case "5": System.out.print("Имя адресата: "); greetAll(in.nextLine().trim()); break;
                case "6": dutiesAll(); break;
                case "0": System.out.println("Пока!"); return;
                default: System.out.println("Неизвестная команда.");
            }
        }
    }
    private static void seed() {
        roster.add(new Student()); roster.add(new Teacher()); roster.add(new TeachingAssistant());
        roster.add(new Student("Павел Иванов",20,"S-1020","СПбГУ","pavel.ivanov@example.com",2023,"Прикладная математика",4.6,3));
        roster.add(new Teacher("Марина Кузнецова",38,"T-5012","СПбГУ","marina.kuz@example.com",2015,"Информатика","Доцент",120000));
        roster.add(new TeachingAssistant("Никита Орлов",23,"TA-3010","СПбГУ","nikita.orlov@example.com",2021,"Программирование на Java","Марина Кузнецова",35000));
    }
    private static void showRoster(){ if(roster.isEmpty()){ System.out.println("Список пуст."); return; } for(int i=0;i<roster.size();i++) System.out.printf("%d) %s%n", i+1, roster.get(i)); }
    private static void greetAll(String whom){ if(roster.isEmpty()){ System.out.println("Список пуст."); return; } for(Person p: roster) p.greet(whom); System.out.println("-- Перегрузка: последний здоровается 3 раза --"); roster.get(roster.size()-1).greet(whom,3); }
    private static void dutiesAll(){ if(roster.isEmpty()){ System.out.println("Список пуст."); return; } for(Person p: roster) p.performDuty(); }
    private static void addStudent(){ try{ System.out.print("Имя: "); String name=in.nextLine(); System.out.print("Возраст: "); int age=Integer.parseInt(in.nextLine()); System.out.print("ID: "); String id=in.nextLine(); System.out.print("Университет: "); String uni=in.nextLine(); System.out.print("Email: "); String email=in.nextLine(); System.out.print("Год начала: "); int start=Integer.parseInt(in.nextLine()); System.out.print("Специальность: "); String major=in.nextLine(); System.out.print("GPA (0..5): "); double gpa=Double.parseDouble(in.nextLine()); System.out.print("Курс: "); int year=Integer.parseInt(in.nextLine()); Student s=new Student(name,age,id,uni,email,start,major,gpa,year); roster.add(s); System.out.println("Студент добавлен: "+s);} catch(Exception e){ System.out.println("Ошибка ввода: "+e.getMessage()); } }
    private static void addTeacher(){ try{ System.out.print("Имя: "); String name=in.nextLine(); System.out.print("Возраст: "); int age=Integer.parseInt(in.nextLine()); System.out.print("ID: "); String id=in.nextLine(); System.out.print("Университет: "); String uni=in.nextLine(); System.out.print("Email: "); String email=in.nextLine(); System.out.print("Год начала: "); int start=Integer.parseInt(in.nextLine()); System.out.print("Кафедра: "); String dep=in.nextLine(); System.out.print("Должность: "); String title=in.nextLine(); System.out.print("Зарплата: "); double salary=Double.parseDouble(in.nextLine()); Teacher t=new Teacher(name,age,id,uni,email,start,dep,title,salary); roster.add(t); System.out.println("Преподаватель добавлен: "+t);} catch(Exception e){ System.out.println("Ошибка ввода: "+e.getMessage()); } }
    private static void addTA(){ try{ System.out.print("Имя: "); String name=in.nextLine(); System.out.print("Возраст: "); int age=Integer.parseInt(in.nextLine()); System.out.print("ID: "); String id=in.nextLine(); System.out.print("Университет: "); String uni=in.nextLine(); System.out.print("Email: "); String email=in.nextLine(); System.out.print("Год начала: "); int start=Integer.parseInt(in.nextLine()); System.out.print("Курс (закреплённый): "); String course=in.nextLine(); System.out.print("Руководитель: "); String sup=in.nextLine(); System.out.print("Стипендия: "); double stipend=Double.parseDouble(in.nextLine()); TeachingAssistant ta=new TeachingAssistant(name,age,id,uni,email,start,course,sup,stipend); roster.add(ta); System.out.println("Ассистент добавлен: "+ta);} catch(Exception e){ System.out.println("Ошибка ввода: "+e.getMessage()); } }
}