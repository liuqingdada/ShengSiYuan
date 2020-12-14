/// step1. Create a visible widget
/// ```dart
/// Text('Hello World'),
/// ```
///
/// step2. Add the visible widget to the layout widget
/// 所有布局小部件均具有以下任一功能：
/// A child property: 如果只有一个孩子, 例如 [Center] [Container]
/// A children propert: 如果有多个孩子, 例如 [Row] [Column] [ListView] [Stack]
/// ```dart
/// Center(
///   child: Text('Hello World'),
/// ),
/// ```
///
/// step3. Add the layout widget to the page
/// 1) For a Material app, you can use a Scaffold widget;
/// ```dart
/// class MyApp extends StatelessWidget {
///   @override
///   Widget build(BuildContext context) {
///     return MaterialApp(
///       title: 'Flutter layout demo',
///       home: Scaffold(
///         appBar: AppBar(
///           title: Text('Flutter layout demo'),
///         ),
///         body: Center(
///           child: Text('Hello World'),
///         ),
///       ),
///     );
///   }
/// }
/// ```
/// 2) For a non-Material app
/// ```dart
/// class MyApp extends StatelessWidget {
///   @override
///   Widget build(BuildContext context) {
///     return Container(
///       decoration: BoxDecoration(color: Colors.white),
///       child: Center(
///         child: Text(
///           'Hello World',
///           textDirection: TextDirection.ltr,
///           style: TextStyle(
///             fontSize: 32,
///             color: Colors.black87,
///           ),
///         ),
///       ),
///     );
///   }
/// }
/// ```
///
///
///
///
///
///
///
///
///
///
///
///
///