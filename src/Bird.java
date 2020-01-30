public class Bird extends Animal {

    public Bird() {
        super("Птица",5,0,.2f, true);
    }

    @Override
    public void swim(int length) {
        System.out.println("Птица не умеет плавать");
    }
}
