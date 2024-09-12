public class l2q2 {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        counter c1 = new counter();
        System.out.println("Created c1");
        counter.showCount();
        counter c2 = new counter();
        System.out.println("Created c2");
        counter.showCount();
        counter c3 = new counter();
        System.out.println("Created c3");
        counter.showCount();
    }
}

class counter {
    static int count = 0;

    static void showCount () {
        System.out.println("number = " + count);
    }
    counter () {
        count ++;
    }
}