package se.innerclass;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/9/25 00:53
 */
public class DotThis {

    public void f() {
        System.out.println("DotThis.f()");
    }

    private class Inner {

        public DotThis outer() {
            return DotThis.this;
        }

    }

    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        Inner inner = dt.inner();
        inner.outer().f();
    }

}
