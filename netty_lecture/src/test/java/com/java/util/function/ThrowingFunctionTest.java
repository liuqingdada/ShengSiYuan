package com.java.util.function;

import java.io.IOException;
import java.util.Optional;

public class ThrowingFunctionTest {

    private void throwE() throws IOException {
        Optional.of(42)
                .map(UncheckedFunction.unchecked(ThrowingFunctionTest::throwException));
    }

    private static String throwException(Integer i) throws IOException {
        throw new IOException("whoopsie.");
    }

    public static void main(String[] args) {
        ThrowingFunctionTest test = new ThrowingFunctionTest();
        try {
            test.throwE();
        } catch (IOException e) {
            System.out.println("ojbk");
        }
    }
}
