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

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                correct.addFirst(randVal);
                student.addFirst(randVal);
                System.out.println("addFirst(" + randVal + ")");
                assertEquals("size()", correct.size(), student.size());
            } else if (operationNumber == 1) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                student.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
                assertEquals("size()", correct.size(), student.size());
            } else if (operationNumber == 2) {
                // removeFirst
                if (correct.size() > 0 && student.size() > 0) {
                    assertEquals("removeFirst()", correct.removeFirst(), student.removeFirst());
                    assertEquals("size()", correct.size(), student.size());
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (correct.size() > 0 && student.size() > 0) {
                    assertEquals("removeLast()", correct.removeLast(), student.removeLast());
                    assertEquals("size()", correct.size(), student.size());
                }
            }
        }
    }
}
