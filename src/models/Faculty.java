package models;

public class Faculty {

    static final private int FACULTY_MIN_LENGTH = 3;
    static private String[] usedNames = new String[0];

    private String name = "Unknown";
    private Department[] departments = new Department[0];

    public Faculty(String name) {

        if (name.length() < FACULTY_MIN_LENGTH) {
            throw new IllegalArgumentException("ERROR: Name is too short (min 3 symbols)");
        }

        if (isNameUsed(name)) {
            throw new IllegalArgumentException("ERROR: Name is already used");
        }

        this.name = name;

        addUsedName(name);
    }

    private boolean isNameUsed(String name) {
        for (String usedName : usedNames) {
            if (usedName.toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    private void addUsedName(String name) {
        String[] newUsedNames = new String[usedNames.length + 1];
        for (int i = 0; i < usedNames.length; i++) {
            newUsedNames[i] = usedNames[i];
        }
        newUsedNames[usedNames.length] = name;
        usedNames = newUsedNames;
    }
    private void changeUsedName(String oldName, String newName) {
        for (int i = 0; i < usedNames.length; i++) {
            if (usedNames[i].toLowerCase().equals(oldName.toLowerCase())) {
                usedNames[i] = newName;
                return;
            }
        }
    }

    public String getName() {
        return name;
    }
    public void changeName(String name) {
        if (name.length() < FACULTY_MIN_LENGTH) {
            throw new IllegalArgumentException("ERROR: Name is too short (min 3 symbols)");
        }

        if (isNameUsed(name)) {
            throw new IllegalArgumentException("ERROR:Name is already used");
        }

        changeUsedName(this.name, name);

        this.name = name;
    }

    public Department[] getDepartments() {
        return departments;
    }

    public void createDepartment(String name) throws IllegalArgumentException {

        Department newDepartment = new Department(name);

        Department[] newArr = new Department[departments.length + 1];
        for (int i = 0; i < departments.length; i++) {
            newArr[i] = departments[i];
        }
        newArr[departments.length] = newDepartment;
        departments = newArr;
    }

    public void removeDepartment(Department department) {
        Department[] newArr = new Department[departments.length - 1];
        int j = 0;
        for (int i = 0; i < departments.length; i++) {
            if (departments[i] != department) {
                newArr[j] = departments[i];
                j++;
            }
        }
        departments = newArr;
    }

    public Department findDepartmentByName(String name) {
        for (Department department : departments) {
            if (department.getName().toLowerCase().contains(name.toLowerCase())) {
                return department;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Faculty: " + name;
    }
}
