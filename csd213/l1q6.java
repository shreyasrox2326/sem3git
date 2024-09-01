public class l1q6 {
    public static void main(String[] args) {

        long millisecs = System.currentTimeMillis();
        long secs = (millisecs / 1000) % 60;
        long mins = ((millisecs / (60 * 1000))) % 60;
        long hour24 = ((millisecs / (1000 * 60 * 24))) % 24;

        System.out.println("The time at GMT is " + hour24 + ":" + mins + ":" + secs);
    }
}
