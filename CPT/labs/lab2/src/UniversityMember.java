package src;

public abstract class UniversityMember extends Person {
    private String university; private String email; private int startYear;
    public UniversityMember() { this("Мой Университет","user@example.com",2023); }
    public UniversityMember(String university,String email,int startYear) { super(); this.university=university; setEmail(email); setStartYear(startYear); }
    public UniversityMember(String name,int age,String id,String university,String email,int startYear) {
        super(name,age,id);
        this.university=university;
        setEmail(email);
        setStartYear(startYear);
    }
    public String getUniversity() { return university; }
    public void setUniversity(String university) { this.university=university; }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        if(email==null||!email.contains("@")) throw new IllegalArgumentException("Некорректный email");
        this.email=email;
    }
    public int getStartYear() { return startYear; }
    public void setStartYear(int startYear) {
        if(startYear<1900) throw new IllegalArgumentException("Год неверный");
        this.startYear=startYear;
    }
    public String toString() { return super.toString()+String.format(", university='%s', email='%s', startYear=%d", university, email, startYear); }
}