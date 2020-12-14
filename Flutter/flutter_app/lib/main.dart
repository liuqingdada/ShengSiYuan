import 'package:flutter/material.dart';
import 'package:english_words/english_words.dart';
import 'package:flutter_app/Counter.dart';
import 'package:flutter_app/ShoppingList.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Startup Name Generator',
      theme: ThemeData(
        primaryColor: Colors.purple,
      ),
      home: RandomWords(),
    );
  }
}

class RandomWords extends StatefulWidget {
  @override
  RandomWordsState createState() {
    return RandomWordsState();
  }
}

class RandomWordsState extends State<RandomWords> {
  final suggestions = <WordPair>[];
  final saved = Set<WordPair>();
  final biggerFont = TextStyle(fontSize: 18.0);

  final List<Choice> choices = <Choice>[
    Choice(checked: false, title: 'Counter', icon: Icons.add),
    Choice(checked: false, title: 'Shopping', icon: Icons.shopping_cart),
    Choice(checked: false, title: 'test2', icon: Icons.shop),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Startup Name Generator'),
        actions: [
          IconButton(icon: Icon(Icons.list), onPressed: pushSaved),
          PopupMenuButton<Choice>(
            offset: const Offset(0, 1000),
            onSelected: onMenuSelected,
            itemBuilder: (BuildContext context) => choices.map((e) {
              return CheckedPopupMenuItem<Choice>(
                value: e,
                checked: e.checked,
                child: Text(e.title),
              );
            }).toList(),
          ),
        ],
      ),
      body: buildSuggestions(),
    );
  }

  void pushSaved() {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) {
          final tiles = saved.map((e) => ListTile(
                title: Text(e.asPascalCase, style: biggerFont),
              ));
          final divided = ListTile.divideTiles(
            context: context,
            tiles: tiles,
          ).toList();
          return Scaffold(
            appBar: AppBar(
              title: Text('Saved Suggestions'),
            ),
            body: ListView(
              children: divided,
            ),
          );
        },
      ),
    );
  }

  void onMenuSelected(Choice choice) {
    setState(() {
      choices.forEach((it) => {it.checked = false});
      choice.checked = true;
    });
    switch (choice.title) {
      case 'Counter':
        Navigator.of(context).push(
          MaterialPageRoute(builder: (context) {
            return Counter();
          }),
        );
        break;
      case 'Shopping':
        Navigator.of(context).push(
          MaterialPageRoute(
              builder: (context) => ShoppingList(
                    products: [
                      Product(name: 'Eggs'),
                      Product(name: 'Flour'),
                      Product(name: 'Chocolate chips'),
                    ],
                  )),
        );
        break;
      default:
        break;
    }
  }

  Widget buildSuggestions() {
    return ListView.builder(
      padding: EdgeInsets.all(16.0),
      itemBuilder: (context, i) {
        if (i.isOdd) return Divider();
        final index = i ~/ 2;
        if (index >= suggestions.length) {
          suggestions.addAll(generateWordPairs().take(10));
        }
        return buildRow(suggestions[index]);
      },
    );
  }

  Widget buildRow(WordPair pair) {
    final alreadySaved = saved.contains(pair);
    return ListTile(
      title: Text(
        pair.asPascalCase,
        style: biggerFont,
      ),
      trailing: Icon(alreadySaved ? Icons.favorite : Icons.favorite_border,
          color: alreadySaved ? Colors.red : null),
      onTap: () {
        setState(() {
          if (alreadySaved) {
            saved.remove(pair);
          } else {
            saved.add(pair);
          }
        });
      },
    );
  }
}

class Choice {
  bool checked;
  final String title;
  final IconData icon;

  Choice({this.checked, this.title, this.icon});
}
