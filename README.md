# Music_Application 

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

At the start of the application, the log in view is opened. There, Any type of user can log in or an account of either type can be created (accounts with the same username are not allowed).<br>
From the main page, an user can view their liked songs, edit their nickname, or look for new music, albums or artists. <br>
In the find view, the user can search for specific results by entering text in the search box and/or apply filters by year and genere and sort the results in any desired way.<br>
By selectecting any result, the user can see details about it and for songs specifically can add and edit reviews.<br>
An artist can edit their bio that the users see.


## Database

The database implements the `generalization and specialization` principle for the two types of accounts.<br>
There are `CRUD` operations on most of the tables, such as creating an account, deleting a song from the "liked" section of an user and updating the bio of an artist.<br>
The database diagram:<br>
![image](https://github.com/GeorgeTof/Music_project/blob/main/readmePhotos/DB.png)

## OOP_and_Java_specifics

 - Classes such as "Artist" and "Album" are declared in the model package to mirror entities from the database.
 - Inheritance is used for extending the standard JPanel to a transparent one and for having less code in the two types of song views (reviewed and unreviewed).
 - Overloading is used for having different methods of filling the results of a search based on the type of data retrieved from the database.


# Visuals

Here are some screenshots from the usage of the application:<br>

- `Sign In Page`
  
![image](https://github.com/GeorgeTof/Music_project/blob/main/readmePhotos/logIn.png)

- `Register artist account` 
  
![image](https://github.com/GeorgeTof/Music_project/blob/main/readmePhotos/register.png)

- `Search page` 
  
![image](https://github.com/GeorgeTof/Music_project/blob/main/readmePhotos/search.png)

- `Song page` 
  
![image](https://github.com/GeorgeTof/Music_project/blob/main/readmePhotos/song.png)

- `Album page`
  
![image](https://github.com/GeorgeTof/Music_project/blob/main/readmePhotos/album.png)

- `Artist page` - specific to the artist account that can edit their bio
  
![image](https://github.com/GeorgeTof/Music_project/blob/main/readmePhotos/artist.png)

- `liked songs` 
  
![image](https://github.com/GeorgeTof/Music_project/blob/main/readmePhotos/likedSongs.png)

# Further_developments

- A more polished front-end;
- Users being able to access each other's liked songs;
- The artist account being able to add albums and songs directly from the application.
