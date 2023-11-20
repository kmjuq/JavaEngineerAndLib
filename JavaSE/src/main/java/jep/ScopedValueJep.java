package jep;

public class ScopedValueJep {

    public static void main(String[] args) {
        ScopedValue<Object> objectScopedValue = ScopedValue.newInstance();
        objectScopedValue.isBound();
    }

}
