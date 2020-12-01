// 概览

// import 的唯一参数是用于指定代码库的 URI，对于 Dart 内置的库，使用 dart:xxxxxx
import 'dart:html';
// 而对于其它的库，你可以使用一个文件系统路径或者以 package:xxxxxx 的形式。
// package:xxxxxx 指定的库通过包管理器（比如 pub 工具）来提供
import 'package:test/test.dart';
// 如果你导入的两个代码库有冲突的标识符，你可以为其中一个指定前缀
import 'package:test/test.dart' as lib2;
// URI 代表统一资源标识符。
// URL（统一资源定位符）是一种常见的URI。

//
// Dart 没有类似于 Java 那样的 public、protected 和 private 成员访问限定符。
// 如果一个标识符以下划线 (_) 开头则表示该标识符在库内是私有的

// 如果一个对象的引用不局限于单一的类型，可以根据设计指南将其指定为
// Object 或 dynamic 类型

// 本文遵循 风格建议指南 中的建议，通过 var 声明局部变量而非使用指定的类型

// 默认值
// 在 Dart 中，未初始化的变量拥有一个默认的初始化值：null。
// 即便数字也是如此，因为在 Dart 中一切皆为对象，数字也不例外

// assert() 的调用将会在生产环境的代码中被忽略掉。
// 在开发过程中，assert(condition) 将会在 条件判断 为 false 时抛出一个异常

// Final 和 Const
// 如果你不想更改一个变量，可以使用关键字 final 或者 const 修饰变量，
// 这两个关键字可以替代 var 关键字或者加在一个具体的类型前。
// 一个 final 变量只可以被赋值一次；
// 一个 const 变量是一个编译时常量（const 变量同时也是 final 的）。
// 顶层的 final 变量或者类的 final 变量在其第一次使用的时候被初始化。

// 使用关键字 const 修饰变量表示该变量为 编译时常量。如果使用 const 修饰类中的变量，
// 则必须加上 static 关键字，即 static const（注意：顺序不能颠倒（译者注））。
// 在声明 const 变量时可以直接为其赋值，也可以使用其它的 const 变量为其赋值
//
//
//
//
var s1 = '可以拼接'
    '字符串'
    '即便它们不在同一行。';
var s2 = '''
你可以像这样创建多行字符串。
''';
var s3 = """这也是一个多行字符串。""";
var s4 = r'在 raw 字符串中，转义字符串 \n 会直接输出 “\n” 而不是转义为换行。';

// 扩展操作符（...）和 null-aware 扩展操作符（...?）
// 类似 Kotlin 的 *list
var list = [1, 2, 3];
var list2 = [0, ...list];
var list3;
var list4 = [0, ...?list3];

// {} 默认是 Map 类型。如果忘记在 {} 上注释类型或赋值到一个未声明类型的变量上，
// 那么 Dart 会创建一个类型为 Map<dynamic, dynamic> 的对象。

// 如果函数体内只包含一个表达式，你可以使用简写语法：
bool isNoble(int atomicNumber) => s1 != null;

// 所有的函数都有返回值。没有显示返回语句的函数最后一行默认为执行 return null;
foo() {}

class A {
  // 除非你重写 noSuchMethod，否则调用一个不存在的成员会导致 NoSuchMethodError。
  @override
  void noSuchMethod(Invocation invocation) {
    print('你尝试使用一个不存在的成员: ${invocation.memberName}');
  }
}

var names = <String>['小芸', '小芳', '小民'];
var uniqueNames = <String>{'小芸', '小芳', '小民'};
var pages = <String, String>{
  'index.html': '主页',
  'robots.txt': '网页机器人提示',
  'humans.txt': '我们是人类，不是机器'
};

var nameSet = Set<String>.from(names);

var views = Map<int, String>();
var names2 = List<String>();

// 与 Java 不同的是，Java 中的泛型是类型 擦除 的，这意味着泛型类型会在运行时被移除。
// 在 Java 中你可以判断对象是否为 List 但不可以判断对象是否为 List<String>

// 通过实现类的 call() 方法，允许使用类似函数调用的方式来使用该类的实例。
class WannabeFunction {
  String call(String a, String b, String c) => '$a $b $c!';
}

var wf = WannabeFunction();
var out = wf('Hi', 'there,', 'gang');
