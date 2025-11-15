package src;

public class TeachingAssistant extends UniversityMember {
    private String assignedCourse; private String supervisor; private double stipend;
    public TeachingAssistant() {
        this("Анна Соколова",22,"TA-3001","МГУ","anna.sоколова@example.com",2022,"Алгоритмы","Сергей Смирнов",30000);
    }
    public TeachingAssistant(String name,int age,String id,String university,String email,int startYear,String assignedCourse,String supervisor,double stipend) {
        super(name,age,id,university,email,startYear); setAssignedCourse(assignedCourse); setSupervisor(supervisor); setStipend(stipend);
    }
    public String getAssignedCourse() { return assignedCourse; }
    public void setAssignedCourse(String assignedCourse) {
        if(assignedCourse==null||assignedCourse.isBlank()) throw new IllegalArgumentException("Курс пустой");
        this.assignedCourse=assignedCourse;
    }
    public String getSupervisor() { return supervisor; }
    public void setSupervisor(String supervisor) {
        if(supervisor==null||supervisor.isBlank()) throw new IllegalArgumentException("Руководитель пустой");
        this.supervisor=supervisor;
    }
    public double getStipend() { return stipend; }
    public void setStipend(double stipend) {
        if(stipend<0) throw new IllegalArgumentException("Стипендия >= 0");
        this.stipend=stipend;
    }
    @Override public void performDuty() { System.out.printf("Ассистент %s проводит практики по курсу «%s».%n", getName(), assignedCourse); }
    public void helpStudents(int group) { System.out.printf("%s помогает группе %d.%n", getName(), group); }
    public String toString() { return super.toString()+String.format(", course='%s', supervisor='%s', stipend=%.2f", assignedCourse, supervisor, stipend); }
}