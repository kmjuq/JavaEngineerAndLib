//package jep;
//
//public class PatternMatchingJep {
//
//    static String formatter(Object o) {
//        String formatted = "unknown";
//        if (o instanceof Integer i) {
//            formatted = String.format("int %d", i);
//        } else if (o instanceof Long l) {
//            formatted = String.format("long %d", l);
//        } else if (o instanceof Double d) {
//            formatted = String.format("double %f", d);
//        } else if (o instanceof String s) {
//            formatted = String.format("String %s", s);
//        }
//        return formatted;
//    }
//
//    static String formatterPatternSwitch(Object o) {
//        return switch (o) {
//            case Integer i -> String.format("int %d", i);
//            case Long l -> String.format("long %d", l);
//            case Double d -> String.format("double %f", d);
//            case String s -> String.format("String %s", s);
//            default -> o.toString();
//        };
//    }
//
//    static void testFooBar(String s) {
//        switch (s) {
//            case null -> System.out.println("Oops");
//            case "Foo", "Bar" -> System.out.println("Great");
//            default -> System.out.println("Ok");
//        }
//    }
//
//    static void testStringOrNull(Object o) {
//        switch (o) {
//            case null, String s -> System.out.println("String: " + s);
//            default -> System.out.println("Something else");
//        }
//    }
//
//    static void testTriangle(Shape s) {
//        switch (s) {
//            case null -> {
//                break;
//            }
//            case Triangle t
//                    when t.calculateArea() > 100 -> System.out.println("Large triangle");
//            case Triangle t -> System.out.println("Small triangle");
//            default -> System.out.println("Non-triangle");
//        }
//    }
//
//    int eval(Expr n) {
//        return switch (n) {
//            case IntExpr(int i) -> i;
//            case NegExpr(Expr n) -> -eval(n);
//            case AddExpr(Expr left,Expr right) -> eval(left) + eval(right);
//            case MulExpr(Expr left,Expr right) -> eval(left) * eval(right);
//            default -> throw new IllegalStateException();
//        };
//    }
//
//    static class Expr {
//    }
//
//    static class IntExpr extends Expr {
//        public IntExpr(int i) {
//        }
//    }
//
//    static class NegExpr extends Expr {
//        public NegExpr(Expr n) {
//        }
//    }
//
//    static class AddExpr extends Expr {
//        public AddExpr(Expr left, Expr right) {
//        }
//    }
//
//    static class MulExpr extends Expr {
//        public MulExpr(Expr left, Expr right) {
//        }
//    }
//
//    static class Shape {
//    }
//
//    static class Rectangle extends Shape {
//    }
//
//    static class Triangle extends Shape {
//        int calculateArea() {
//            return 0;
//        }
//    }
//
//}
