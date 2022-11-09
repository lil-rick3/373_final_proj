# 373_final_proj
This is Jaret,Bryce, and Oliver's final project

### point

So this is for ece 373, which is object oriented design

this is a space game, right now it is very rudimentary, but that's alright

## what has been done:
The current state of the project is creating a jpanel that plays music and keeps track of the player movements. Then there is the game class, which is the 
main driver, but it gets player inputs from the jpanel. The projectile class has been created



# what needs to be done at the moment:
### Jframe addition
Currently the gui is just a jpanel, but it should be a jframe. One of the big reasons is because the Jfram will allow us to 
### thread errors
![thread example](https://i.groupme.com/915x153.png.91ba70aa7d8b4bbd95cd9609bb5631fb)

I have come up on this error a couple of times because the key lsitener will interrupt the program whenever it is pressed, this is a multithreaded proccess. This means that we need to be careful about changing things that are currently being used. In this case, the paint function is iterating through the projctiles and painting each one. But if spacebar is pressed during this proccess, then the program will go add a projectile, since spacebar is shoot. So the program needs to make sure that there is a specific time within the program where projectiles are added. In general, we need to be aware of this error moving on. I know for movement I set some booleans within the playership class to true or false depending on what buttons were pressed at the moment, and then the game class moves the ship at a certain time. 
### projectile 
The projectile needs to include a way to tell if the projectile is out of bounds, because at the moment, that doesn't exist and I don't want a memory crash/slowdown 
The projectile Class will use a static variable for the size of playing field, and then there will be a function that checks the bounds to see if the projectile needs to be destroyed. Then a boolean variable within the projectile class called willDestroy will be set to true, and during the purge part of the cycle, it will get deleted. This is all done to avoid multithreading issues.
### enemy ships extends ship
Enemy ships will have an id, xloc, yloc and then their health. the xloc and yloc will be extended from the ship parent class. Then there will be a round that is created, and each ship will get generated within the round class, and then sent to main class, along with the movement class. This class will implement the shoot projectile function from the ship class. Basically there will be a chance for a ship to shoot a projetile during each cycle, and the function will return a projectile, which gets added to the list, or null, and then there will be a check to see if the projectile is null or not.
### round class
The round class will be used to construct everything. basically it will take in a string that stores the round data, and then construct everyhting, after that it won't have a purpose. When the enemy ships list reaches a size of zero after everything has gotten destroyed, then the game will create a new round, which will create a new set of ships and movement class.
### movement class
The movement class will tell the ships how to move. For some movement classes, that will mean creating a pattern that the ships need to move on which is dependent on a specific time within the cycle, and a function will put in what frame in the cycle it is, and the id of a ship, and will then change the ships location based off of that. In another movement class, the ships will bounce around. This means that based off of their position, and velocity, they will move again. 

![This is the example formation](http://www.ocf.berkeley.edu/~pad/screenshots/calcuzap1.gif)
