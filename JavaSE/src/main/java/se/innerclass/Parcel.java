package se.innerclass;

/**
 * <p>
 *
 * </p>
 *
 * @author mengjian.ke@hand-china.com 2020/9/24 01:18
 */
public class Parcel {

    private class Contents {
        private final int i = 11;

        public int value() {
            return i;
        }
    }

    private class Destination {
        private final String dest;

        public Destination(String whereTo) {
            this.dest = whereTo;
        }

        String readDest() {
            return dest;
        }
    }

    public Destination to(String s) {
        return new Destination(s);
    }

    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        Destination d = new Destination(dest);
        System.out.println(d.readDest());
    }

    public static void main(String[] args) {
        Parcel p = new Parcel();
        p.ship("Tasmania");
        Destination d = p.to("Tasmania");
        Contents c = p.contents();
    }

}
