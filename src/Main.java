public class Main {
    /*
        Создать класс "Сотрудник" с полями: ФИО, должность, телефон, зарплата, возраст;
Конструктор класса должен заполнять эти поля при создании объекта;
Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;
Вывести при помощи методов из пункта 3 ФИО и должность.
Создать массив из 5 сотрудников. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
* Создать метод, повышающий зарплату всем сотрудникам старше 45 лет на 5000.
* Средние арифметические возраста и зарплаты
*** Продумать конструктор таким образом, чтобы при создании каждому сотруднику
присваивался личный уникальный идентификационный порядковый номер
     */

    public static void main(String[] args) {
        Employee employee = new Employee("Иван", "Петров", "Программист", 123123213, 100000, 22);
        System.out.println(String.format("ФИО: %s %s, должность: %s", employee.getName(), employee.getLastName(), employee.getPost()));

        Employee[] empls = {
                new Employee("Name1", "LastName1", "Post1", 111, 100000, 42),
                new Employee("Name2", "LastName2", "Post2", 111, 100000, 55),
                new Employee("Name3", "LastName3", "Post3", 111, 100000, 33),
                new Employee("Name4", "LastName4", "Post4", 111, 100000, 22),
                new Employee("Name5", "LastName5", "Post5", 111, 100000, 46),
        };

        for (Employee empl : empls) {
            if(empl.getAge() > 40) {
                System.out.println(String.format("ФИО: %s %s, возраст: %s", empl.getName(), empl.getLastName(), empl.getAge()));
            }
        }

        raiseSalary(empls, 45+1, 5000);

        findAverage(empls);

        for (Employee empl : empls) {
            System.out.println(String.format("ФИО: %s %s, id: %s", empl.getName(), empl.getLastName(), empl.getId()));
        }

    }
    public static void raiseSalary(Employee[] empls, int needAge, int raise) {
        for (Employee empl : empls) {
            if(empl.getAge() >= needAge) {
                empl.raiseSalary(raise);
            }
            System.out.println(String.format("ФИО: %s %s, возраст: %s, зарплата: %s", empl.getName(), empl.getLastName(), empl.getAge(), empl.getSalary()));
        }
    }
    public static void findAverage(Employee[] empls) {
        int ageBox = 0;
        int salaryBox = 0;
        for (Employee empl : empls) {
            ageBox += empl.getAge();
            salaryBox += empl.getSalary();
        }
        ageBox /= empls.length;
        salaryBox /= empls.length;

        System.out.println(String.format("Среднее арифметическое возраста: %s, Среднее арифметическое зарплаты: %s", ageBox, salaryBox));
    }

}
