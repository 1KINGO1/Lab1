package persons;

public class Person {

    private String name = "Unknown";
    private int age = 0;

    public Person(String name, int age){
        checkName(name);
        checkAge(age);
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    private void checkName(String name){
        if (name.length() < 3) {
            throw new IllegalArgumentException("ERROR: Name is too short (min 3 symbols)");
        }
    }
    private void checkAge(int age){
        if (age < 16 || age > 100) {
            throw new IllegalArgumentException("ERROR: Age is out of range (16-100)");
        }
    }

    public void changeName(String name){
        checkName(name);
        this.name = name;
    }

    public void changeAge(int age){
        checkAge(age);
        this.age = age;
    }

    @Override
    public String toString(){
        return this.name + " (" + this.age + ")";
    }

}
