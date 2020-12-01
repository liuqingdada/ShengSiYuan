import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.*;

import com.java.lang.*;
import com.java.util.*;
import com.java.util.function.*;

public class UncheckedTest {
    private InputStream is = new FileInputStream("JDK8_lesson.txt");
    private byte[] bytes = new byte[4096];

    public UncheckedTest() throws FileNotFoundException {}

    private void readFile() throws IOException {
        int read = is.read(bytes);
        System.out.println("read: " + read);
    }

    public void UncheckedBiConsumer() {
        BiConsumer<String, String> biConsumer = UncheckedBiConsumer.unchecked(
                (a, b) -> {
                    System.out.println(a + b);
                    readFile();
                });
        biConsumer.accept("a", "b");
    }

    public void UncheckedBiFunction() {
        BiFunction<String, String, String> biFunction = UncheckedBiFunction.unchecked(
                (a, b) -> {
                    readFile();
                    return a + b;
                });
        String apply = biFunction.apply("a", "b");
        System.out.println(apply);
    }

    public void UncheckedBinaryOperator() {
        BinaryOperator<String> binaryOperator = UncheckedBinaryOperator.unchecked((a, b) -> {
            readFile();
            return a + b;
        });
        String apply = binaryOperator.apply("a", "b");
        System.out.println(apply);
    }

    public void UncheckedBiPredicate() {
        BiPredicate<String, String> biPredicate = UncheckedBiPredicate.unchecked(
                (a, b) -> {
                    readFile();
                    return a.equals(b);
                });
        boolean test = biPredicate.test("a", "b");
        System.out.println(test);
    }

    public void UncheckedBooleanSupplier() {
        BooleanSupplier supplier = UncheckedBooleanSupplier.unchecked(() -> {
            readFile();
            return true;
        });
        System.out.println(supplier.getAsBoolean());
    }

    public void UncheckedConsumer() {
        Consumer<String> consumer = UncheckedConsumer.unchecked((a) -> {
            System.out.println(a);
            readFile();
        });
        consumer.accept("a");
    }

    public void UncheckedDoubleBinaryOperator() {
        DoubleBinaryOperator operator = UncheckedDoubleBinaryOperator.unchecked((l, r) -> {
            readFile();
            return l + r;
        });
        System.out.println(operator.applyAsDouble(1L, 2L));
    }

    public void UncheckedDoubleConsumer() {
        DoubleConsumer consumer = UncheckedDoubleConsumer.unchecked(value -> {
            System.out.println(value);
            readFile();
        });
        consumer.accept(1.0D);
    }

    public void UncheckedDoubleFunction() {
        DoubleFunction<Double> function = UncheckedDoubleFunction.unchecked(value -> {
            readFile();
            return value;
        });
        System.out.println(function.apply(1.0D));
    }

    public void UncheckedDoublePredicate() {
        DoublePredicate predicate = UncheckedDoublePredicate.unchecked((value) -> {
            readFile();
            return value > 0.1D;
        });
        System.out.println(predicate.test(1.0D));
    }

    public void UncheckedDoubleSupplier() {
        DoubleSupplier supplier = UncheckedDoubleSupplier.unchecked(() -> {
            readFile();
            return 1.0D;
        });
        System.out.println(supplier.getAsDouble());
    }

    public void UncheckedDoubleToIntFunction() {
        DoubleToIntFunction function = UncheckedDoubleToIntFunction.unchecked(value -> {
            readFile();
            return (int) value;
        });
        System.out.println(function.applyAsInt(1.0D));
    }

    public void UncheckedDoubleToLongFunction() {
        DoubleToLongFunction function = UncheckedDoubleToLongFunction.unchecked(value -> {
            readFile();
            return (long) value;
        });
        System.out.println(function.applyAsLong(1.0D));
    }

    public void UncheckedDoubleUnaryOperator() {
        DoubleUnaryOperator operator = UncheckedDoubleUnaryOperator.unchecked(operand -> {
            readFile();
            return operand + 1.2D;
        });
        System.out.println(operator.applyAsDouble(1.0D));
    }

    public void UncheckedFunction() {
        Function<String, String> function = UncheckedFunction.unchecked(t -> {
            readFile();
            return t + "b";
        });
        System.out.println(function.apply("a"));
    }

    public void UncheckedIntBinaryOperator() {
        IntBinaryOperator operator = UncheckedIntBinaryOperator.unchecked((left, right) -> {
            readFile();
            return left + right;
        });
        System.out.println(operator.applyAsInt(1, 2));
    }

    public void UncheckedIntConsumer() {
        IntConsumer consumer = UncheckedIntConsumer.unchecked(value -> {
            readFile();
            System.out.println(value);
        });
        consumer.accept(1);
    }

    public void UncheckedIntFunction() {
        IntFunction<Integer> function = UncheckedIntFunction.unchecked(value -> {
            System.out.println(value);
            return value;
        });
        System.out.println(function.apply(1));
    }

    public void UncheckedIntPredicate() {
        IntPredicate predicate = UncheckedIntPredicate.unchecked(value -> {
            readFile();
            return value > 0;
        });
        System.out.println(predicate.test(1));
    }

    public void UncheckedIntSupplier() {
        IntSupplier supplier = UncheckedIntSupplier.unchecked(() -> {
            readFile();
            return 1;
        });
        System.out.println(supplier.getAsInt());
    }

    public void UncheckedIntToDoubleFunction() {
        IntToDoubleFunction function = UncheckedIntToDoubleFunction.unchecked(value -> {
            readFile();
            return value;
        });
        System.out.println(function.applyAsDouble(1));
    }

    public void UncheckedIntToLongFunction() {
        IntToLongFunction function = UncheckedIntToLongFunction.unchecked(value -> {
            readFile();
            return value;
        });
        System.out.println(function.applyAsLong(1));
    }

    public void UncheckedIntUnaryOperator() {
        IntUnaryOperator operator = UncheckedIntUnaryOperator.unchecked(operand -> {
            readFile();
            return operand;
        });
        System.out.println(operator.applyAsInt(1));
    }

    public void UncheckedLongBinaryOperator() {
        LongBinaryOperator operator = UncheckedLongBinaryOperator.unchecked((left, right) -> {
            readFile();
            return left + right;
        });
        System.out.println(operator.applyAsLong(1, 2));
    }

    public void UncheckedLongConsumer() {
        LongConsumer consumer = UncheckedLongConsumer.unchecked(value -> {
            readFile();
            System.out.println(value);
        });
        consumer.accept(1);
    }

    public void UncheckedLongFunction() {
        LongFunction<Long> function = UncheckedLongFunction.unchecked(value -> {
            readFile();
            return value;
        });
        System.out.println(function.apply(1));
    }

    public void UncheckedLongPredicate() {
        LongPredicate predicate = UncheckedLongPredicate.unchecked(value -> {
            readFile();
            return value > 0.2;
        });
        System.out.println(predicate.test(1L));
    }

    public void UncheckedLongSupplier() {
        LongSupplier supplier = UncheckedLongSupplier.unchecked(() -> {
            readFile();
            return 1L;
        });
        System.out.println(supplier.getAsLong());
    }

    public void UncheckedLongToDoubleFunction() {
        LongToDoubleFunction function = UncheckedLongToDoubleFunction.unchecked(value -> {
            readFile();
            return value;
        });
        System.out.println(function.applyAsDouble(1L));
    }

    public void UncheckedLongToIntFunction() {
        LongToIntFunction function = UncheckedLongToIntFunction.unchecked(value -> {
            readFile();
            return (int) value;
        });
        System.out.println(function.applyAsInt(1L));
    }

    public void UncheckedLongUnaryOperator() {
        LongUnaryOperator operator = UncheckedLongUnaryOperator.unchecked(operand -> {
            readFile();
            return operand;
        });
        System.out.println(operator.applyAsLong(1L));
    }

    public void UncheckedObjDoubleConsumer() {
        ObjDoubleConsumer<String> consumer = UncheckedObjDoubleConsumer.unchecked((t, value) -> {
            readFile();
            System.out.println(t + value);
        });
        consumer.accept("a", 1.0D);
    }

    public void UncheckedObjIntConsumer() {
        ObjIntConsumer<String> consumer = UncheckedObjIntConsumer.unchecked((t, value) -> {
            readFile();
            System.out.println(t + value);
        });
        consumer.accept("a", 1);
    }

    public void UncheckedObjLongConsumer() {
        ObjLongConsumer<String> consumer = UncheckedObjLongConsumer.unchecked((t, value) -> {
            readFile();
            System.out.println(t + value);
        });
        consumer.accept("a", 1L);
    }

    public void UncheckedPredicate() {
        Predicate<Integer> predicate = UncheckedPredicate.unchecked(t -> {
            readFile();
            return t > 0;
        });
        System.out.println(predicate.test(1));
    }

    public void UncheckedSupplier() {
        Supplier<String> supplier = UncheckedSupplier.unchecked(() -> {
            readFile();
            return "A";
        });
        System.out.println(supplier.get());
    }

    public void UncheckedToDoubleBiFunction() {
        ToDoubleBiFunction<Integer, Integer> function = UncheckedToDoubleBiFunction.unchecked(
                (t, u) -> {
                    readFile();
                    return t + u;
                });
        System.out.println(function.applyAsDouble(1, 2));
    }

    public void UncheckedToDoubleFunction() {
        ToDoubleFunction<Integer> function = UncheckedToDoubleFunction.unchecked(value -> {
            readFile();
            return value;
        });
        System.out.println(function.applyAsDouble(1));
    }

    public void UncheckedToIntBiFunction() {
        ToIntBiFunction<Double, Double> function = UncheckedToIntBiFunction.unchecked((t, u) -> {
            readFile();
            return (int) (t + u);
        });
        System.out.println(function.applyAsInt(1.1D, 1.1D));
    }

    public void UncheckedToIntFunction() {
        ToIntFunction<String> function = UncheckedToIntFunction.unchecked(value -> {
            readFile();
            return Integer.parseInt(value);
        });
        System.out.println(function.applyAsInt("1"));
    }

    public void UncheckedToLongBiFunction() {
        ToLongBiFunction<Integer, Integer> function = UncheckedToLongBiFunction.unchecked(
                (t, u) -> {
                    readFile();
                    return t + u;
                });
        System.out.println(function.applyAsLong(1, 2));
    }

    public void UncheckedToLongFunction() {
        ToLongFunction<Integer> function = UncheckedToLongFunction.unchecked(value -> {
            readFile();
            return value;
        });
        System.out.println(function.applyAsLong(1));
    }

    public void UncheckedUnaryOperator() {
        UnaryOperator<String> operator = UncheckedUnaryOperator.unchecked((t) -> {
            readFile();
            return t;
        });
        System.out.println(operator.apply("a"));
    }

}
