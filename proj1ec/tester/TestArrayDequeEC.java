package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

/**
 * @author Practice
 * @project proj1ec
 */
public class TestArrayDequeEC {
    @Test
    public void addFirstTest(){
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 50000; i ++){
            Integer number = StdRandom.uniform(0, 20);
            sad.addFirst(number);
            ads.addFirst(number);
            stringBuilder.append("addFirst("+number+")\n");
            assertEquals(stringBuilder.toString(),sad.size(), ads.size());
        }
    }

    @Test
    public void addLastTest(){
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 50000; i ++){
            Integer number = StdRandom.uniform(0, 20);
            sad.addLast(number);
            ads.addLast(number);
            stringBuilder.append("addLast("+number+")\n");
            assertEquals(stringBuilder.toString(),sad.size(), ads.size());
        }
    }

    @Test
    public void addAndRemoveTest(){
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 50000; i ++){
            int option = StdRandom.uniform(0, 5);
            if(option == 0){
                // addFirst
                int number = StdRandom.uniform(0, 20);
                sad.addFirst(number);
                ads.addFirst(number);
                stringBuilder.append("addFirst(" + number + ")\n");
                assertEquals(stringBuilder.toString(), sad.size(), ads.size());
            }else if(option == 1){
                // addLast
                int number = StdRandom.uniform(0,20);
                sad.addLast(number);
                ads.addLast(number);
                stringBuilder.append("addLast(" + number + ")\n");
                assertEquals(stringBuilder.toString(), sad.size(), ads.size());
            }else if(option == 2){
                // removeFirst
                if(sad.size() > 0){
                    stringBuilder.append("removeFirst()\n");
                    assertEquals(stringBuilder.toString(), sad.removeFirst(), ads.removeFirst());
                }
            }else if(option == 3){
                // removeLast
                if(sad.size() > 0){
                    stringBuilder.append("removeLast()\n");
                    assertEquals(stringBuilder.toString(), sad.removeLast(), ads.removeLast());
                }
            }else if(option == 4) {
                // get
                if (sad.size() > 0) {
                    int index = StdRandom.uniform(0, sad.size());
                    stringBuilder.append("get(" + index + ")\n");
                    assertEquals(stringBuilder.toString(), sad.get(index), ads.get(index));
                }
            }
        }
    }
}
