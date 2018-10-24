package com.java.util.function;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

public class UncheckedTest {
    private InputStream is = new FileInputStream(
            "/Users/liuqing/work/ShengSiYuan/jdk8/JDK8_lesson.txt");
    private byte[] bytes = new byte[4096];

    public UncheckedTest() throws FileNotFoundException {}

    private void readFile() throws IOException {
        int read = is.read(bytes);
        System.out.println("read: " + read);
    }

//    private void Consumer() throws IOException {
//        Consumer<String> consumer = str -> {
//            readFile();
//            System.out.println(str);
//        };
//        consumer.accept("MMP, 抛不出去异常啊");
//    }

    @Test
    public void UncheckedBiConsumer() {
        BiConsumer<String, String> biConsumer = UncheckedBiConsumer.unchecked(
                (a, b) -> {
                    System.out.println(a + b);
                    readFile();
                });
        biConsumer.accept("a", "b");
    }

    @Test
    public void UncheckedBiFunction() {
        BiFunction<String, String, String> biFunction = UncheckedBiFunction.unchecked(
                (a, b) -> {
                    readFile();
                    return a + b;
                });
        String apply = biFunction.apply("a", "b");
        System.out.println(apply);
    }

    @Test
    public void UncheckedBinaryOperator() {
        BinaryOperator<String> binaryOperator = UncheckedBinaryOperator.unchecked((a, b) -> {
            readFile();
            return a + b;
        });
        String apply = binaryOperator.apply("a", "b");
        System.out.println(apply);
    }

    @Test
    public void UncheckedBiPredicate() {
        BiPredicate<String, String> biPredicate = UncheckedBiPredicate.unchecked(
                (a, b) -> {
                    readFile();
                    return a.equals(b);
                });
        boolean test = biPredicate.test("a", "b");
        System.out.println(test);
    }

    @Test
    public void UncheckedBooleanSupplier() {
        BooleanSupplier supplier = UncheckedBooleanSupplier.unchecked(() -> {
            readFile();
            return true;
        });
        System.out.println(supplier.getAsBoolean());
    }

    @Test
    public void UncheckedConsumer() {
        Consumer<String> consumer = UncheckedConsumer.unchecked((a) -> {
            System.out.println(a);
            readFile();
        });
        consumer.accept("a");
    }

    @Test
    public void UncheckedDoubleBinaryOperator() {
        DoubleBinaryOperator operator = UncheckedDoubleBinaryOperator.unchecked((l, r) -> {
            readFile();
            return l + r;
        });
        System.out.println(operator.applyAsDouble(1L, 2L));
    }

    @Test
    public void UncheckedDoubleConsumer() {
        DoubleConsumer consumer = UncheckedDoubleConsumer.unchecked(value -> {
            System.out.println(value);
            readFile();
        });
        consumer.accept(1.0D);
    }

    @Test
    public void UncheckedDoubleFunction() {
        DoubleFunction<Double> function = UncheckedDoubleFunction.unchecked(value -> {
            readFile();
            return value;
        });
        System.out.println(function.apply(1.0D));
    }

    @Test
    public void UncheckedDoublePredicate() {
        DoublePredicate predicate = UncheckedDoublePredicate.unchecked((value) -> {
            readFile();
            return value > 0.1D;
        });
        System.out.println(predicate.test(1.0D));
    }

    @Test
    public void UncheckedDoubleSupplier() {
        DoubleSupplier supplier = UncheckedDoubleSupplier.unchecked(() -> {
            readFile();
            return 1.0D;
        });
        System.out.println(supplier.getAsDouble());
    }

    @Test
    public void UncheckedDoubleToIntFunction() {
        DoubleToIntFunction function = UncheckedDoubleToIntFunction.unchecked(value -> {
            readFile();
            return (int) value;
        });
        System.out.println(function.applyAsInt(1.0D));
    }

    @Test
    public void UncheckedDoubleToLongFunction() {
        DoubleToLongFunction function = UncheckedDoubleToLongFunction.unchecked(value -> {
            readFile();
            return (long) value;
        });
        System.out.println(function.applyAsLong(1.0D));
    }

    @Test
    public void UncheckedDoubleUnaryOperator() {
        DoubleUnaryOperator operator = UncheckedDoubleUnaryOperator.unchecked(operand -> {
            readFile();
            return operand + 1.2D;
        });
        System.out.println(operator.applyAsDouble(1.0D));
    }

    @Test
    public void UncheckedFunction() {
        Function<String, String> function = UncheckedFunction.unchecked(t -> {
            readFile();
            return t + "b";
        });
        System.out.println(function.apply("a"));
    }

    @Test
    public void UncheckedIntBinaryOperator() {
        IntBinaryOperator operator = UncheckedIntBinaryOperator.unchecked((left, right) -> {
            readFile();
            return left + right;
        });
        System.out.println(operator.applyAsInt(1, 2));
    }

    @Test
    public void UncheckedIntConsumer() {
        IntConsumer consumer = UncheckedIntConsumer.unchecked(value -> {
            readFile();
            System.out.println(value);
        });
        consumer.accept(1);
    }

    @Test
    public void UncheckedIntFunction() {
        IntFunction<Integer> function = UncheckedIntFunction.unchecked(value -> {
            System.out.println(value);
            return value;
        });
        System.out.println(function.apply(1));
    }

    @Test
    public void UncheckedIntPredicate() {
        IntPredicate predicate = UncheckedIntPredicate.unchecked(value -> {
            readFile();
            return value > 0;
        });
        System.out.println(predicate.test(1));
    }

    @Test
    public void UncheckedIntSupplier() {
        IntSupplier supplier = UncheckedIntSupplier.unchecked(() -> {
            readFile();
            return 1;
        });
        System.out.println(supplier.getAsInt());
    }

    @Test
    public void UncheckedIntToDoubleFunction() {
        IntToDoubleFunction function = UncheckedIntToDoubleFunction.unchecked(value -> {
            readFile();
            return value;
        });
        System.out.println(function.applyAsDouble(1));
    }

    @Test
    public void UncheckedIntToLongFunction() {
        IntToLongFunction function = UncheckedIntToLongFunction.unchecked(value -> {
            readFile();
            return value;
        });
        System.out.println(function.applyAsLong(1));
    }

    @Test
    public void UncheckedIntUnaryOperator() {
        IntUnaryOperator operator = UncheckedIntUnaryOperator.unchecked(operand -> {
            readFile();
            return operand;
        });
        System.out.println(operator.applyAsInt(1));
    }

    @Test
    public void UncheckedLongBinaryOperator() {
        LongBinaryOperator operator = UncheckedLongBinaryOperator.unchecked((left, right) -> {
            readFile();
            return left + right;
        });
        System.out.println(operator.applyAsLong(1, 2));
    }

    @Test
    public void UncheckedLongConsumer() {
        LongConsumer consumer = UncheckedLongConsumer.unchecked(value -> {
            readFile();
            System.out.println(value);
        });
        consumer.accept(1);
    }

    @Test
    public void UncheckedLongFunction() {
        LongFunction<Long> function = UncheckedLongFunction.unchecked(value -> {
            readFile();
            return value;
        });
        System.out.println(function.apply(1));
    }

    @Test
    public void UncheckedLongPredicate() {
        LongPredicate predicate = UncheckedLongPredicate.unchecked(value -> {
            readFile();
            return value > 0.2;
        });
        System.out.println(predicate.test(1L));
    }

    @Test
    public void UncheckedLongSupplier() {
        LongSupplier supplier = UncheckedLongSupplier.unchecked(() -> {
            readFile();
            return 1L;
        });
        System.out.println(supplier.getAsLong());
    }

    @Test
    public void UncheckedLongToDoubleFunction() {
        LongToDoubleFunction function = UncheckedLongToDoubleFunction.unchecked(value -> {
            readFile();
            return value;
        });
        System.out.println(function.applyAsDouble(1L));
    }

    @Test
    public void UncheckedLongToIntFunction() {
        LongToIntFunction function = UncheckedLongToIntFunction.unchecked(value -> {
            readFile();
            return (int) value;
        });
        System.out.println(function.applyAsInt(1L));
    }

    @Test
    public void UncheckedLongUnaryOperator() {
        LongUnaryOperator operator = UncheckedLongUnaryOperator.unchecked(operand -> {
            readFile();
            return operand;
        });
        System.out.println(operator.applyAsLong(1L));
    }

    @Test
    public void UncheckedObjDoubleConsumer() {
        ObjDoubleConsumer<String> consumer = UncheckedObjDoubleConsumer.unchecked((t, value) -> {
            readFile();
            System.out.println(t + value);
        });
        consumer.accept("a", 1.0D);
    }

    @Test
    public void UncheckedObjIntConsumer() {
        ObjIntConsumer<String> consumer = UncheckedObjIntConsumer.unchecked((t, value) -> {
            readFile();
            System.out.println(t + value);
        });
        consumer.accept("a", 1);
    }

    @Test
    public void UncheckedObjLongConsumer() {
        ObjLongConsumer<String> consumer = UncheckedObjLongConsumer.unchecked((t, value) -> {
            readFile();
            System.out.println(t + value);
        });
        consumer.accept("a", 1L);
    }

    @Test
    public void UncheckedPredicate() {
        Predicate<Integer> predicate = UncheckedPredicate.unchecked(t -> {
            readFile();
            return t > 0;
        });
        System.out.println(predicate.test(1));
    }

    @Test
    public void UncheckedSupplier() {
        Supplier<String> supplier = UncheckedSupplier.unchecked(() -> {
            readFile();
            return "A";
        });
        System.out.println(supplier.get());
    }

    @Test
    public void UncheckedToDoubleBiFunction() {
        ToDoubleBiFunction<Integer, Integer> function = UncheckedToDoubleBiFunction.unchecked(
                (t, u) -> {
                    readFile();
                    return t + u;
                });
        System.out.println(function.applyAsDouble(1, 2));
    }

    @Test
    public void UncheckedToDoubleFunction() {
        ToDoubleFunction<Integer> function = UncheckedToDoubleFunction.unchecked(value -> {
            readFile();
            return value;
        });
        System.out.println(function.applyAsDouble(1));
    }

    @Test
    public void UncheckedToIntBiFunction() {
        ToIntBiFunction<Double, Double> function = UncheckedToIntBiFunction.unchecked((t, u) -> {
            readFile();
            return (int) (t + u);
        });
        System.out.println(function.applyAsInt(1.1D, 1.1D));
    }

    @Test
    public void UncheckedToIntFunction() {
        ToIntFunction<String> function = UncheckedToIntFunction.unchecked(value -> {
            readFile();
            return Integer.parseInt(value);
        });
        System.out.println(function.applyAsInt("1"));
    }

    @Test
    public void UncheckedToLongBiFunction() {
        ToLongBiFunction<Integer, Integer> function = UncheckedToLongBiFunction.unchecked(
                (t, u) -> {
                    readFile();
                    return t + u;
                });
        System.out.println(function.applyAsLong(1, 2));
    }

    @Test
    public void UncheckedToLongFunction() {
        ToLongFunction<Integer> function = UncheckedToLongFunction.unchecked(value -> {
            readFile();
            return value;
        });
        System.out.println(function.applyAsLong(1));
    }

    @Test
    public void UncheckedUnaryOperator() {
        UnaryOperator<String> operator = UncheckedUnaryOperator.unchecked((t) -> {
            readFile();
            return t;
        });
        System.out.println(operator.apply("a"));
    }

}
