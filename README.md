# Music_Application 

This Music Application was made for the OOP 2023/2024 project assignment.

<p align="center">
  <a href="#Author">Author</a> •
  <a href="#Description">Description</a> •
  <a href="#Usage_in_detail">Usage_in_detail</a> •
  <a href="#Database">Database</a> •
  <a href="#OOP_and_Java_specifics">OOP and Java specifics</a> •
  <a href="#Visuals">Visuals</a> •
  <a href="#Further_developments">Further developments</a>
</p>


## Author 
Tofan George | 2nd Year Computer Engineering | Technical University of Cluj-Napoca

## Description

The application allows users to search and view details for songs, artsts and albums, add songs to a liked section and review songs, while also seeing other users' reviews. <br>
There is also another type of account supported, artist, that has the option to edit their bio, that users can see. <br>
The application was fully built with `java`, having the user interface built with `Java Swing` and the database connected using `JDBC`. The design pattern followed is `MVC`. <br>
All the data about the users and songs are stored in a `Postgres SQL Database`, the CRUD opperations being done through the Postgres JDBC driver. 


## Usage_in_detail

At the start of the application, the log in view is opened. There, Any type of user can log in or an account of either type can be created (accounts with the same username are not allowed).
From the main page, an user can view their liked songs, edit their nickname, or look for new music, albums or artists. 
In the find view, the user can search for specific results by entering text in the search box and/or apply filters by year and genere and sort the results in any desired way.
By selectecting any result, the user can see details about it and for songs specifically can add and edit reviews.
An artist can edit their bio that the users see.


## Database:

The database implements the `generalization and specialization` principle for the two types of accounts.
There are `CRUD` operations on most of the tables, such as creating an account, deleting a song from the "liked" section of an user and updating the bio of an artist.
The database diagram:
![image](https://github.com/codruj/Music-Player-Project/blob/master/readmepoze/database.png)

## OOP_and_Java_specifics

Classes such as "Artist" and "Album" are declared in the model package to mirror entities from the database.
Inheritance is used for extending the standard JPanel to a transparent one and for having less code in the two types of song views (reviewed and unreviewed).
Overloading is used for having different methods of filling the results of a search based on the type of data retrieved from the database.


# Visuals

Here are some screenshots from the usage of the application:

- `Sign In Page`
  
![image](https://github.com/codruj/Music-Player-Project/blob/master/readmepoze/1SignIn.png)

- `Main Page` //write a review, see artist merch or listen to an artist, album or song
  
![image](https://github.com/codruj/Music-Player-Project/blob/master/readmepoze/2mainpage.png)

- `Artist Page` //you can select what album you want to listen to
  
![image](https://github.com/codruj/Music-Player-Project/blob/master/readmepoze/3artistpage.png)

- `Album Page` //you can select what song you want to listen to
  
![image](https://github.com/codruj/Music-Player-Project/blob/master/readmepoze/4albumpage.png)

- `Song Page` //you can play or stop the song
  
![image](https://github.com/codruj/Music-Player-Project/blob/master/readmepoze/5songpage.png)

- `Admin Page` //can add genres for an artist + see all reviews
  
![image](https://github.com/codruj/Music-Player-Project/blob/master/readmepoze/6adminmainpage.png)

- `See Reviews Page` //only seen by the admin
  
![image](https://github.com/codruj/Music-Player-Project/blob/master/readmepoze/7seereviewspage.png)

# Further_developments

- A more polished front-end;
- Users being able to access each other's liked songs;
- The artist account being able to add albums and songs directly from the application.
