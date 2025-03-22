import 'package:flutter/material.dart';
import 'package:tenjin_plugin/tenjin_plugin.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: const Text('Plugin example app')),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            spacing: 20,
            children: [
              OutlinedButton(
                onPressed: () {
                  TenjinPlugin.instance.init(
                    apiKey: 'BKTTLUYVFBUES2JX9ZJBQMVIDHMIJHJC',
                  );
                },
                child: Text('Init Sdk'),
              ),
              OutlinedButton(
                onPressed: () {
                  TenjinPlugin.instance.eventWithName('register');
                },
                child: Text('Track Event'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
