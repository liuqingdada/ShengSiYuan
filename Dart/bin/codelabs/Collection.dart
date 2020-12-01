// firstWhere(): to find the first element that satisfies certain conditions;
// 可配合 orElse

// singleWhere(): only one element of the Iterable to satisfy the predicate.
// If more than one or no element in the Iterable satisfies the predicate
// condition, then the method throws a StateError exception.

// singleWhere() steps through the whole Iterable until the last element,
// which can cause problems if the Iterable is infinite or
// contains a large collection of elements.

// Using any() and every()
// any(): Returns true if at least one element satisfies the condition.
// every(): Returns true if all elements satisfy the condition.

// You can also use any() to verify that
// no element of an Iterable satisfies a certain condition.

/// Filtering:
// where(): The output of where() is another Iterable; If no element satisfies
// the predicate in where(), then the method returns an empty Iterable.
// Unlike singleWhere() or firstWhere(),
// where() doesn’t throw a StateError exception.

// takeWhile(): 返回 Iterable
// take 满足条件的元素, 一旦元素(不包含)不满足就停止

// skipWhile(): 返回 Iterable
// 跳过满足条件的元素, 从不满足条件的元素开始(包含) take 到最后

/// Mapping:
// map(): 返回 Iterable
// 输入一个参数(每一个元素), 返回一个值(通常是对每个元素进行转换)

void main() {
  Iterable<int> iterable = [1, 2, 3];
  print(iterable.elementAt(1));
}
