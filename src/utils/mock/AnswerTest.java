package utils.mock;

//public class A {
//    private int input;
//
//    public void setInput(int input) {
//        this.input = input;
//    }
//
//    public int getInput() {
//        return input;
//    }
//
//}
//
//public class B {
//    public String outputParam(A a) {
//        switch(a.getInput()) {
//            case 1: a.setInput(2); return "param1";
//            case 2: a.setInput(3); return "param2";
//            default: return "param0";
//        }
//    }
//    public void print() {
//        A a = new A();
//        a.setInput(2);
//        System.out.println(a.getInput());
//        System.out.println(outputParam(a));
//        System.out.println(a.getInput());
//    }
//}
//
//
//public class BTest {
//    @InjectMocks
//    private B b;
//
//    @Test
//    public void testParam() {
//        B b = Mockito.spy(B.class);
//
//        Answer<String> answer = (invocation) -> {
//            Object[] args = invocation.getArguments();
//            String result = "answer0";
//            if (null == args[0]) {
//                return result;
//            }
//            if (args[0] instanceof A) {
//                A a = (A) args[0];
//                a.setInput(20);
//                return "answer1";
//            }
//            return result;
//        };
//
//        PowerMockito.doAnswer(answer).when(b).outputParam(any(A.class));
//        b.print();
//    }
//
//    @Test
//    public void testNew() throws Exception {
//        A a = PowerMockito.mock(A.class);
//        PowerMockito.whenNew(A.class).withNoArguments().thenReturn(a);
//        PowerMockito.when(a.getInput()).thenReturn(1);
//        b.print();
//    }
//}