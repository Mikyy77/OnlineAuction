# Michal Darovec

## Bidder Estate Auction House

## How to Run

Make sure to have Java 17 and also JavaFX 17.
The correct location for the javaFX jar files is under C:\javafx-sdk-17.0.2\lib
You have to add this file as the VM argument: --module-path "C:\javafx-sdk-17.0.2\lib" --add-modules javafx.controls,javafx.fxml

## Documentation

My project is about creating a real estate auction that works on principles of an English auction. It is being developed in Java in Intellij IDEA IDE with the help of JavaFX. I have implemented an easy version of login, with saving the data in a text document. The passwords are not hashed yet, but they will be in the final version.

The project has a home scene which allows you to log in. If the username is too long or too short, you have to change it. If you have logged in before, you have to log in with the same password. After clicking the button, you are automatically redirected to the auction scene. Here, you get a random property generated from the Storage class. The time for bidding is always 5 seconds and you are playing against bots, so you don’t bet alone. Every time a bid is placed, the timer restarts. The auction ends when the time runs up and last bid always wins. So, either you get a congratulations message or a better luck next time message.

I have decided to create a singleton class called AuctionProcess where all the data is stored and transferred to other controllers.

## Info

During the auction process, the user biddings are only shown in console so far. So to see how players and bots bid, it is neccessary to check the console - for this version.

To run the program with the user list, in the Controller class, you first have to specify the path to the file where users.txt is or you can create your own file anywhere. You just need to specify the path to get all functionalities, even though it doesn’t work completely right yet.
In this version, many functionalities aren’t perfect. Login is not done at all, most likely I will do a database connection. This text file data is definitely not safe and I’m not even hashing the passwords yet. The next thing is the bid price text field, which functionality is not yet fully implemented.

## Functionality

## Login scene

Enter username and password.
If you are logging in again, you have to type the same password
Both username and password are validated

## Auction scene

Bid button and bid price text field allow the user to bid as much as they want.
The bots are always bidding at the same time, so you have to beat them.
Every time a bid is placed, the timer restarts.
If the user wins, a congratulations message is displayed.

## Features
## Main criteria
### Polymorphism
- **Admin Buy**
    - buy method is overridden, doesn't subtract money from Admin's account
    - package users, class Admin, line 20

### Aggregation
- **User Contact and Address**
    - each user has a contact and address that are created when registering
    - package users, class User, lines 15, 16
### Inheritance
- **Properties**
    - each property has inherited methods from an abstract Class named Property
    - package properties, class Property
- **Users**
    - Bot and Admin are inherited from the User class
    - package users, classes Bot, Admin

## Other criteria
### Design patterns
-	**Observer**
    - used for notifying all bots that they can bid
    - package auctionControl, class AuctionProcess, line 171 - method notifyAllBots()
-	**Factory method**
    - used for encapsulated creation of Property objects outside the Storage class
    - package storage, class StorageFactory, is used in Storage, lines 21, 27
-	**Singleton**
    - used as control classes that can store data and communicate with other classes
    - package auctionControl, class AuctionProcess, LoginControl

### My own exceptions
- **Wrong Login Exception**
    - package exceptions, class WrongLoginException
    - used for wrong login input, in package auctionControl, class ControllerLogin, line 105

### Multithreading
- **Timer Task**
    - for creating a Timer, we use the class TimerTask with an implemented run() method, which starts a new Thread
    - package auctionControl, class AuctionProcess, line 115

### RTTI
- **Instance of List**
    - checking whether elements of a list are instance of class User
    - used in package serialization, class Serialize, line 12
 
### Lambda expressions
- **Timer Task**
    - used for Timer Task creation, directly overriding and implementing the run() method
    - package auctionControl, class AuctionProcess

### Serialization
- **Saving User Data**
    - used for knowing which user has or has not registered
    - keeping track of all users
    - package serialization, class Serialize






