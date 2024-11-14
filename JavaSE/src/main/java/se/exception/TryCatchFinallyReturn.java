package se.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TryCatchFinallyReturn {

    public static void main(String[] args) {
        try {
            throw new RuntimeException("try catch");
        } catch (Exception e) {
            throw e;
        } finally {
            throw new RuntimeException("final");
        }
    }


}
