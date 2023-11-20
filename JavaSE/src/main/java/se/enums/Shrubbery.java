package se.enums;

import java.util.Arrays;
import java.util.EnumSet;

public enum Shrubbery {

    GROUND("11", "ceshi11"),
    CRAWLING("222", "ceshi22"),
    HANGING("333", "ceshi33");

    String code;
    String desc;

    Shrubbery(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static void main(String[] args) {
        final EnumSet<Shrubbery> shrubberies = EnumSet.noneOf(Shrubbery.class);
        System.out.println(GROUND.getDeclaringClass());
        System.out.println(GROUND.ordinal());
        System.out.println(GROUND.name());
        System.out.println(GROUND.getCode());
        System.out.println(GROUND.getDesc());
        System.out.println(shrubberies);
        System.out.println(Arrays.toString(Shrubbery.values()));
    }
}
