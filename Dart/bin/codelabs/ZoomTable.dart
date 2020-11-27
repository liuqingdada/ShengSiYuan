import 'dart:io';

// 其中一个是 ??= 赋值运算符，仅当该变量为空值时才为其赋值
void nullAssignment() {
  int a; // The initial value of any object is null.
  a ??= 3;
  print(3);

  a ??= 5;
  print(a);
}

// 另外一个避空运算符是 ??，如果该运算符左边的表达式返回的是空值，则会计算并返回右边的表达式
void nullAware() {
  int a;
  print(1 ?? 3);
  print(a ?? 12);
}

// 条件属性访问: 和kotlin一样用 ?.

// 集合字面量
void collectionLiterals() {
  final aList = ['one', 'two', 'three'];
  final aSet = {'one', 'two', 'three'};
  final aMap = {'one': 1, 'two': 2, 'three': 3};
}

// 要对同一对象执行一系列操作，请使用级联（..）   myObject..someMethod()
// 表达式的结果却不是该方法返回值，而是是 myObject 对象的引用

// Getters and setters
class MyClass {
  int _aProperty = 0;
  final List<int> _list = [];

  int get aProperty => _aProperty;

  set aProperty(int value) {
    _aProperty = value;
  }

  int get count {
    return _list.length;
  }
}

// 可选位置参数
int sumUpToFive(int a, [int b = 2, int c, int d, int e]) {
  var sum = a;
  if (b != null) sum += b;
  if (c != null) sum += c;
  if (d != null) sum += d;
  if (e != null) sum += e;
  return sum;
}

// 可选命名参数
void printName(String firstName, String lastName, {String suffix = ''}) {
  print('$firstName $lastName ${suffix}');
}

// 一个方法不能同时使用可选位置参数和可选命名参数

// 与 Java 相比，Dart 的所有异常都是 unchecked exception。
// 方法不会声明它们可能抛出的异常，你也不需要捕获任何异常
void testTry() {
  try {
    //breedMoreLlamas();
  } on IOException {
    // A specific exception
    //buyMoreLlamas();
  } on Exception catch (e) {
    // Anything else that is an exception
    print('Unknown exception: $e');
  } catch (e) {
    // No specified type, handles all
    print('Something really unknown: $e');
  }

  // try 关键字作用与其他大多数语言一样。使用 on 关键字按类型过滤特定异常，
  // 而 catch 关键字则能够获取捕捉到的异常对象的引用。
  // 如果你无法完全处理该异常，请使用 rethrow 关键字再次抛出异常
  try {
    // breedMoreLlamas();
  } catch (e) {
    print('I was just trying to breed llamas!.');
    rethrow;
  }

  // 要执行一段无论是否抛出异常都会执行的代码，请使用 finally：
  try {
    //breedMoreLlamas();
  } catch (e) {
    //… handle exception ...
  } finally {
    // Always clean up, even if an exception is thrown.
    // cleanLlamaStalls();
  }
}

class MyColor {
  int red;
  int green;
  int blue;

  MyColor([this.red = 0, this.green = 0, this.blue = 0]);

  MyColor.black() {
    red = 0;
    green = 0;
    blue = 0;
  }

  MyColor.black2() : this(0, 0, 0);

  MyColor.black3() : this.black();
}

class MyColor2 {
  int red;
  int green;
  int blue;

  MyColor2({this.red = 0, this.green = 0, this.blue = 0});
}

class FirstTwoLetters {
  final String letterOne;
  final String letterTwo;

  // Create a constructor with an initializer list here:
  FirstTwoLetters(String word)
      : assert(word.length >= 2),
        letterOne = word.substring(0, 1),
        letterTwo = word[1] {
    print('$letterOne, $letterTwo');
  }
}

// Const 构造方法
// 如果你的类生成的对象永远都不会更改，则可以让这些对象成为编译时常量。
// 为此，请定义 const 构造方法并确保所有实例变量都是 final 的
class ImmutablePoint {
  const ImmutablePoint(this.x, this.y);

  final int x;
  final int y;

  static const ImmutablePoint origin = ImmutablePoint(0, 0);
}

void main() {
  nullAssignment();
  nullAware();
  printName('Poshmeister', 'Moneybuckets', suffix: 'IV');

  MyColor(1, 2);
  MyColor2(green: 2, blue: 3);
  FirstTwoLetters('AB');
}
