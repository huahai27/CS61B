package tester;
import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void randomizedTest() {
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        String message = "";

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);

            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                correct.addFirst(randVal);
                student.addFirst(randVal);
                message += "addFirst(" + randVal + ")\n";
            } else if (operationNumber == 1) {
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                student.addLast(randVal);
                message += "addLast(" + randVal + ")\n";
            } else if (operationNumber == 2) {
                if (!correct.isEmpty()) {
                    Integer c = correct.removeFirst();
                    Integer s = student.removeFirst();
                    message += "removeFirst()\n";
                    assertEquals(message, c, s); 
                }
            } else if (operationNumber == 3) {
                if (!correct.isEmpty()) {
                    Integer c = correct.removeLast();
                    Integer s = student.removeLast();
                    message += "removeLast()\n";
                    assertEquals(message, c, s);
                }
            }
        }
    }
}
