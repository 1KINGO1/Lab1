package persons;

public class Teacher extends Person {

    public Teacher(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString(){
        return "[Teacher] " + super.toString();
    }

}
