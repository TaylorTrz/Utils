package utils.mock;

import java.util.Arrays;

public class NewTest {
    public static void main(String[] args) {
        String nonEmpty1 = ",a,b,";
        System.out.println(Arrays.toString(nonEmpty1.split(",")));
        String nonEmpty2 = " ,a,b, ";
        System.out.println(Arrays.toString(nonEmpty2.split(",")));
        String nonEmpty3 = " ,,a,b, ";
        System.out.println(Arrays.toString(nonEmpty3.split(",")));
        String empty = "";
        System.out.println(Arrays.toString(empty.split(",")));
    }
//    public class A {
//        public String getMessage() {
//            Cps cps = new Cps;
//            return cps.getValue();
//        }
//    }
//
//    @RunWith(PowerMockitoRunner.class)
//    @PrepareForTest(Cps.class, A.class)
//    public class ATest {
//        @Test
//        public void getMessageTest() {
//            Cps cps = PowerMockito.mock(Cps.class);
//            PowerMockito.whenNew(Cps.class).withNoArguments().thenReturn(cps);
//            when(cps.getValue()).thenReturn("test");
//        }
//    }

}
