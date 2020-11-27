import 'dart:async';
import 'dart:io';
import 'dart:math';
import 'package:Dart/Dart.dart';

// 变量
// 虽然 Dart 是代码类型安全的语言，但是由于其支持类型推断，
// 因此大多数变量不需要显式地指定类型：
import 'package:test/test.dart';

var name = 'Cooper';
var year = 1977;
var antennaDiameter = 3.7;
var flybyObjects = ['木星', '土星', '天王星', '海王星'];
var image = {'tags': flybyObjects, 'url': 'https://www.baidu.com/'};
const oneSecond = Duration(seconds: 1);

// 流程控制语句
void flowControl() {
  if (year >= 2001) {
    print('21 世纪');
  } else if (year >= 1901) {
    print('20 世纪');
  }

  for (var obj in flybyObjects) {
    print(obj);
  }
  for (var i = 0; i < 12; i++) {
    print('index: ' + i.toString());
  }
  while (year < 2016) {
    year += 1;
  }
}

int fibonacci(int n) {
  if (n == 0 || n == 1) return n;
  return fibonacci(n - 1) + fibonacci(n - 2);
}

// 这是一个普通的单行注释。
/// 这是一个文档注释。
/// 文档注释用于为库、类以及类的成员添加注释。
/// 像 IDE 和 dartdoc 这样的工具可以专门处理文档注释。
/* 也可以像这样使用单斜杠和星号的注释方式 */

class Spacecraft {
  String name;
  DateTime launchDate;

  // 构造函数，带有可以直接成为成员变量赋值的语法糖。
  Spacecraft(this.name, this.launchDate) {
    // 这里可以实现初始化代码
  }

  Spacecraft.build(String name) : this(name, null);

  int get launchYear => launchDate?.year; // 只读的非 final 的属性

  ///       ~/  整除
  void describe() {
    print('The spacecraft name: $name');
    if (launchDate != null) {
      var years = DateTime.now().difference(launchDate).inDays ~/ 365;
      print('Emit time: $launchYear ($years years ago)');
    } else {
      print('Not emit');
    }
  }
}

// Dart 支持单继承
class Oribiter extends Spacecraft {
  double altitude;

  Oribiter(String name, DateTime launchDate, this.altitude)
      : super(name, launchDate);
}

class Piloted {
  int astronauts = 1;

  void desctibeCrew() {
    print('The crew num: $astronauts');
  }
}

// Mixin 是一种在多个类层次结构中重用代码的方法
// Mixins are a way of reusing code in multiple class hierarchies
class PilotedCraft extends Spacecraft with Piloted {
  PilotedCraft(String name, DateTime launchDate) : super(name, launchDate);
}

// 使用 async 和 await 关键字可以让你避免回调地狱（Callback Hell）并使你的代码更具可读性
Future<void> printWithDelay(String message) async {
  await Future.delayed(oneSecond);
  print(message);
}

Future<void> printWithDelay2(String message) {
  return Future.delayed(oneSecond).then((_) {
    print(message);
  });
}

/*
 * 同步代码以及异步函数中得代码都可以使用 try 捕获异常
 */
Future<void> createDescriptions(Iterable<String> objects) async {
  for (var obj in objects) {
    try {
      var file = File('$obj.txt');
      if (await file.exists()) {
        var modified = await file.lastModified();
        print('File $obj already exist, last modify time is $modified');
        continue;
      }
      await file.create();
      await file.writeAsString('Start file description in $obj');
    } on IOException catch (e) {
      print('Can not create $e for $obj');
    }
  }
}

Stream<String> report(Spacecraft craft, Iterable<String> objects) async* {
  for (var obj in objects) {
    await Future.delayed(oneSecond);
    yield '${craft.name} fly by $obj';
  }
}

void stateError() {
  throw StateError('No astronauts.');
}

void main() {
  flowControl();
  var fib = fibonacci(5);
  print(fib);
  print('-----------');
  flybyObjects.forEach((element) {
    print(element);
  });
  print('-----------');
  flybyObjects.where((it) => it.contains('土星')).forEach((it) => print(it));
  print(calculate());

  var voyager = Spacecraft('旅行者一号', DateTime(1997, 9, 5));
  voyager.describe();

  var vo = Spacecraft.build('旅行者三号');
  vo.describe();
  print('-------------');

  printWithDelay('hello');
  printWithDelay2('world');
}
