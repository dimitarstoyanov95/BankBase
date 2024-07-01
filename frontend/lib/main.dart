import 'package:flutter/material.dart';
import 'package:frontend/ui/accounts/checking-account.dart';
import 'package:frontend/ui/accounts/investment-account.dart';
import 'package:frontend/ui/accounts/savings-account.dart';
import 'package:frontend/ui/deposit/deposit.dart';
import 'package:frontend/ui/more/more.dart';
import 'package:frontend/ui/notifications/notifications.dart';
import 'package:frontend/ui/payment/pay-bills.dart';
import 'package:frontend/ui/support/support.dart';
import 'package:frontend/ui/transactions/send.dart';
import 'package:frontend/ui/loggin/loggin.dart';
import 'package:frontend/ui/loggin/sign-up.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Banking App',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      debugShowCheckedModeBanner: false,
      initialRoute: '/login', // Set the initial route to the login page
      routes: {
        '/login': (context) => LoginPage(),
        '/home': (context) => MainPage(),
        '/createAccount': (context) => CreateAccountPage(),
        '/send': (context) => SendPage(),
        '/pay': (context) => PayBillsPage(),
        '/deposit': (context) => DepositPage(),
        '/more': (context) => MorePage(),
        '/checking': (context) => CheckingAccountPage(),
        '/savings': (context) => SavingsAccountPage(),
        '/investment': (context) => InvestmentAccountPage(),
        '/notifications': (context) => NotificationsPage(),
        '/support': (context) => SupportPage(),
      },
    );
  }
}

class MainPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: PreferredSize(
        preferredSize: Size.fromHeight(120.0),
        child: AppBar(
          title: const Text('Hello User'),
          actions: [
            IconButton(
              icon: const Icon(Icons.notifications),
              onPressed: () {
                Navigator.pushNamed(context, '/notifications');
              },
            ),
            IconButton(
              icon: const Icon(Icons.support),
              onPressed: () {
                Navigator.pushNamed(context, '/support');
              },
            ),
          ],
        ),
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          _buildAccountOption(context, 'Checking Account', '/checking', '\$5,000'),
          _buildAccountOption(context, 'Savings Account', '/savings', '\$10,000'),
          _buildAccountOption(context, 'Investment Account', '/investment', '\$20,000'),
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
        selectedItemColor: Colors.black,
        unselectedItemColor: Colors.black54,
        items: const [
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Home',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.send),
            label: 'Send',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.payment),
            label: 'Pay Bills',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.account_balance),
            label: 'Deposit',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.more_horiz),
            label: 'More',
          ),
        ],
        onTap: (index) {
          switch (index) {
            case 0:
              Navigator.pushReplacementNamed(context, '/home');
              break;
            case 1:
              Navigator.pushNamed(context, '/send');
              break;
            case 2:
              Navigator.pushNamed(context, '/pay');
              break;
            case 3:
              Navigator.pushNamed(context, '/deposit');
              break;
            case 4:
              Navigator.pushNamed(context, '/more');
              break;
          }
        },
      ),
    );
  }

  Widget _buildAccountOption(BuildContext context, String accountType, String route, String balance) {
    return GestureDetector(
      onTap: () {
        Navigator.pushNamed(context, route);
      },
      child: Card(
        margin: EdgeInsets.all(8.0),
        child: ListTile(
          title: Text(accountType),
          subtitle: Text('Balance: $balance'),
          leading: Icon(Icons.account_balance_wallet),
          trailing: Icon(Icons.arrow_forward),
        ),
      ),
    );
  }
}
