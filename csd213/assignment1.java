import java.util.Scanner;
class queue {
    int tempLen;
    int maxLen;
    int q [];
    int start;
    int end;

    queue (int n) {
        this.tempLen = n+1;
        this.maxLen = n;
        this.q = new int [this.tempLen];
        this.start = 0;
        this.end = 0;
    }

    boolean isFull () {
        if (this.curLength() == maxLen) {
            return true;
        }
        else {
            return false;
        }
    }

    boolean isEmpt () {
        if (this.curLength() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    int element (int index) {
        if (index > this.curLength()) {
            System.out.println("ERROR index out of range.");
            return -1;
        }
        return (q [(index + this.start) % this.tempLen]);
    }

    int curLength () {
        int tempvar = ((this.end - this.start) % this.tempLen);
        
        if (tempvar < 0) {
            tempvar += tempLen;
        }
        
        return tempvar;
    }

    boolean enQ (int element) {
        boolean errorFlag;
        if (! this.isFull()) {
            this.q [this.end] = element;
            this.end = (this.end + 1) % this.tempLen;
            System.out.println("enQ ("+element+") success");
            errorFlag = false;
        }
        else {
            System.out.println("enQ ("+element+") failure");
            errorFlag = true;
        }
        return (errorFlag);
    }

    int deQ () {
        int deQueuedElement;
        if (! this.isEmpt()) {
            deQueuedElement = this.q [this.start];
            this.start = (this.start + 1) % this.tempLen;
            System.out.println("deQ () success, dequeued element = " + deQueuedElement);
        }
        else {
            deQueuedElement = -1;
            System.out.println("deQ () failure");
        }
        return (deQueuedElement);
    }
}

public class assignment1 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter length of queue: ");
        int lengthQ = scn.nextInt();
        queue testQueue = new queue(lengthQ);

        String helpString = "0. Help\n1. Enqueue element\n2. Dequeue element\n3. Check (start,end,curLength,maxLength,full,empty)\n4. Display queue\n9. Exit\n10. Re-initialise Queue\n";
        System.out.println(helpString);

        int inputNum;
        int inputParam;
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.print(">>> ");
            inputNum = scn.nextInt();
            switch (inputNum) {
                case 0: {
                    System.out.println(helpString);
                    break;
                }
                case 1: {
                    System.out.print("Enter enqueue element: ");
                    inputParam = scn.nextInt();
                    testQueue.enQ(inputParam);
                    System.out.println("\nPress 0 for help.");
                    break;
                }
                case 2: {
                    testQueue.deQ();
                    System.out.println("\nPress 0 for help.");
                    break;
                }
                case 3: {
                    System.out.println("Start          = " + testQueue.start);
                    System.out.println("End            = " + testQueue.end);
                    System.out.println("Current length = " + testQueue.curLength());
                    System.out.println("Maximum length = " + testQueue.maxLen);
                    System.out.println("Full           = " + testQueue.isFull());
                    System.out.println("Empty          = " + testQueue.isEmpt());
                    System.out.println("\nPress 0 for help.");
                    break;
                }
                case 4: {
                    System.out.println("Start");
                    for (int i=0; i < testQueue.curLength(); i ++){
                        System.out.println("queue[" + i + "] = " + testQueue.element(i));
                    }
                    for (int i=testQueue.curLength(); i < testQueue.maxLen; i ++){
                        System.out.println("queue[" + i + "] = NULL");
                    }
                    System.out.println("End");
                    System.out.println("\nPress 0 for help.");
                    break;

                }
                case 9: {
                    System.out.println("Exiting ...");
                    continueLoop = false;
                    break;
                }
                case 10: {
                    System.out.print("Enter length of queue: ");
                    lengthQ = scn.nextInt();
                    testQueue = new queue(lengthQ);
                    System.out.println("Re-init success, max queue length = " + testQueue.maxLen);
                    System.out.println("\nPress 0 for help.");
                }
            }
        }
        scn.close();
    }
}