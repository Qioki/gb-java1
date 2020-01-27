public class Employee {

    private String name;
    private String lastName;
    private String post;
    private int phone;
    private int salary;
    private int age;
    private int id;

    private static int counter = 0;

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPost() {
        return post;
    }

    public int getPhone() {
        return phone;
    }

    public int getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public Employee(String name, String lastName, String post, int phone, int salary, int age) {
        this.name = name;
        this.lastName = lastName;
        this.post = post;
        this.phone = phone;
        this.salary = salary;
        this.age = age;

        id = counter++;
    }

    public void raiseSalary(int raise) {
        salary += raise;
    }
}