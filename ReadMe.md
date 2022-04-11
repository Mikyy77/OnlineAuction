# Michal Darovec

## Bidder Estate Auction House

## Documentation

My project is about creating a real estate auction that works on principles of an English auction. It is being developed in Java in Intellij IDEA IDE with the help of JavaFX. I have implemented an easy version of login, with saving the data in a text document. The passwords are not hashed yet, but they will be in the final version.

The project has a home scene which allows you to log in. If the username is too long or too short, you have to change it. If you have logged in before, you have to log in with the same password. After clicking the button, you are automatically redirected to the auction scene. Here, you get a random property generated from the Storage class. The time for bidding is always 5 seconds and you are playing against bots, so you don’t bet alone. Every time a bid is placed, the timer restarts. The auction ends when the time runs up and last bid always wins. So, either you get a congratulations message or a better luck next time message.

I have decided to create a singleton class called AuctionProcess where all the data is stored and transferred to other controllers.

## Info

To run the program with the user list, you first have to specify the path to the file where users.txt is or you can create your own file anywhere. You just need to specify the path to get all functionalities, even though it doesn’t work completely right yet.
In this version, many functionalities aren’t perfect. Login is not done at all, most likely I will do a database connection. This text file data is definitely not safe and I’m not even hashing the passwords yet. The next thing is the bid price text field, which functionality is not yet fully implemented.

## Functionality

## Login scene

Enter username and password.
If you are logging in again, you have to type the same password
Both username and password are validated

## Auction scene

Bid button and bid price text field allow the user to bid as much as they want.
The bots are always bidding at the same time, so you have to beat them.
Every time a bid is placed, the timer restarts
If the user wins, a congratulations message is displayed

## Features

Login scene 60%
Auction scene 60%
Results scene 60%
Users and bots 70%
Storage 90%
Timer

## Important code

Bot class, line 5 and line 14 – examples of inheritance and polymorphism
AuctionProcess, line 15 - aggregation
Property – abstract class for many types of properties
AuctionProcess – singleton design pattern




