public class Cat extends Animal {

    public Cat() {
        super("Кот",200,0,2f, false);
    }

    @Override
    public void swim(int length) {
        System.out.println("Кот не умеет плавать");
    }
}
