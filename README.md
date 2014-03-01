ZapposChallenge-SuggestCombination-JAVAapp
==========================================

This is the challenge question for zappos software engineering intern for summer 2014. it suggest users combinations of items they can buy within given budget and for specific number of items.

Description:
=========================================
This is a java application which lets the user search for items from the zappos website, using some keywords. On the basis of the search the user then can specify their budget and the number of items they want to purchase, and the application will give them the possible combinations of specified number of products whose total cost sums up to their specified budget.

Instructions to use the application screen by screen:
=====================================================
1)	If you are running the application on an IDE, don’t forget to import the libraries
2)	The application will have a search text field and a button which facilitates the user to search any item on zappos.
3)	Once you enter a search term, the application will return a set of objects matching that keyword.
4)	If you have a specific budget in your mind and want to buy specific number of items within that budget, the             application will then have two text fields to take those values from you. 
5)	Once you specify a proper number in both of these fields, it will compute the possible combinations for you and give     you suggestions of what to buy.
6)	These suggestions will be less than or equal to your specified budget.
7)	These suggestions will be on the console instead of the application and you will have to look there for it.
8)	The user cannot input more than 9999 number of items to buy and more than $99999.99 as their budget as the size of      the textbox is 4 and 8 respectively

Note to the reader: - I wanted to use screenshots here, but the API keys were exhausted and I was no longer able to make API calls and hence no longer could get the application running.


File name and their short descriptions:
=========================================
1)	Zappos.java :
-	Contains the main method
-	Makes API calls to get the searched objects
-	Makes product searches based on the search results
-	Stores the products
-	Has constants defined required for the URL

2)	ZapposFrame.java
-	Creates the GUI
-	Have various actionListeners that calls various functions required for the application to run.
-	Displays the items received from the search


3)	ZapposItem.java
-	The class which has definition of the zappos items that are received from the search responses
-	It has all the details of those items 
-	It has setter and getter methods which is used to set and retrieve the attributes of an item object.

4)	ZapposSearchResponses.java
-	Takes care of the responses received from Zappos.com
-	Handles the responses and converts them JSON to string so that the application can read the data
-	These responses are used to search for particular products.

5)	ZapposSearchProductResponses.java
-	From the search responses, the products can be searched
-	The responses received from product search is handled by this class
-	The responses converted to string from JSON are handled by this class.

6)	suggestMe.java
-	It has the logic to implement the suggestion of combination functionality to the users based on the number of items they want to buy within a given budget.
-	It computes the various combinations and displays them on the console.

7)	ReportApplication.doc
-	Contains the report of the application
-	Contains the documentation of existing bugs
-	Contains the documentation of what can be improved in future implementation of application
-	Outlines the performance of the application, not in detail

Submitted by :- 
Rohan D. Shah,
Student of MS in Computer Science,
Utah State University
Summer 2014 software engineering intern candidate
