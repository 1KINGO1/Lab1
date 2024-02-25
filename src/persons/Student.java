package persons;

public class Student extends Person{

    private int course = 0;

    public Student(String name, int age, int course) {
        super(name, age);
        checkCourse(course);
        this.course = course;
    }
    private void checkCourse(int course){
        if (course < 1 || course > 6) {
            throw new IllegalArgumentException("ERROR: Course is out of range (1-6)");
        }
    }

    public int getCourse(){
        return this.course;
    }

    public void changeCourse(int course){
        checkCourse(course);
        this.course = course;
    }

    @Override
    public String toString(){
        return "[Student] " + super.toString() + " in course " + this.course;
    }

}
