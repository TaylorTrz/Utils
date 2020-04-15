package iteratorTime20190730;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectListTest {
    private ObjectList list;

    @Before
    public void setUp() throws Exception {
        list = new ObjectList();
    }

    @Test
    public void add() {
        list.add(new String("a"));
        System.out.println(list.toString());
        System.out.println(list.get(0));
        System.out.println(list.toString());
        System.out.println(list.get(1));
        System.out.println(list.toString());
        list.remove("a");
        System.out.println(list.toString());
    }
}
