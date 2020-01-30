import java.util.Random;

abstract class Animal {

    private String name;
    private int maxRunLength;
    private int maxSwimLength;
    private float maxJumpHeight;
    private boolean ending;

    private Random random = new Random();

    public Animal(String name, int maxRunLength, int maxSwimLength, float maxJumpHeight, boolean ending) {
        this.name = name;
        this.maxRunLength = (int) (maxRunLength * (random.nextFloat() + 4.5f) / 5);
        this.maxSwimLength = (int) (maxSwimLength * (random.nextFloat() + 4.5f) / 5);
        this.maxJumpHeight = maxJumpHeight * (random.nextFloat() + 4.5f) / 5;
        this.ending = ending;
    }

    void run(int length) {
        sayResult(length <= maxRunLength, "пробежал");
    }
    void swim(int length) {
        sayResult(length <= maxSwimLength, "переплыл");
    }
    void jump(float height) {
        sayResult(height <= maxJumpHeight, "перепрыгнул");
    }
    void sayResult(boolean isSuccess, String sportAct) {
        System.out.println(name + (!isSuccess ? " не ": " ") + sportAct + (ending ? "а": ""));
    }
}
