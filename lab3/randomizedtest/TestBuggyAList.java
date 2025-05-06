package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> integerAListNoResizing = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();

        integerAListNoResizing.addLast(4);
        integerAListNoResizing.addLast(5);
        integerAListNoResizing.addLast(6);

        buggyAList.addLast(4);
        buggyAList.addLast(5);
        buggyAList.addLast(6);

        assertEquals(integerAListNoResizing.removeLast(), buggyAList.removeLast());
        assertEquals(integerAListNoResizing.removeLast(), buggyAList.removeLast());
        assertEquals(integerAListNoResizing.removeLast(), buggyAList.removeLast());
    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // getLast
                if (L.size() > 0) {
                    Integer last = L.getLast();
                    System.out.println("getLast: " + last);
                }
            }else if(operationNumber == 2){
                // removeLast
                if(L.size() > 0){
                    Integer last = L.removeLast();
                    System.out.println("removeLast: " + last);
                }
            }
        }
    }

    @Test
    public void randomizedTest2(){
        AListNoResizing<Integer> A = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for(int i = 0; i < N; i ++){
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                A.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // getLast
                if (A.size() > 0) {
                    Integer aLast = A.getLast();
                    Integer bLast = B.getLast();
                }
            }else if(operationNumber == 2){
                // removeLast
                if(A.size() > 0){
                    assertEquals(A.removeLast(), B.removeLast());
                }
            }
        }
    }
}
