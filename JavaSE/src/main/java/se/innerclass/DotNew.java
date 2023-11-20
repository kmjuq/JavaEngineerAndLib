package se.innerclass;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/9/25 00:51
 */
public class DotNew {

    public class InnerClass {
    }

    public static void main(String[] args) {
        final DotNew dotNew = new DotNew();
        final InnerClass ic = dotNew.new InnerClass();
    }

}
