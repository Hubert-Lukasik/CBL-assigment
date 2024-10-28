# CBL-assigment
CBL assignment - game development
Lars Kuppen (Student ID: 2146053)
Hubert Lukasik (Student ID: 2146541)
Project Group 138

SelfDefence game is a tower-defence-like game.
In this game you will have to defend yourself from bigger and bigger waves of opponents.  The game is splitted into “plan” and “defend” phases. During the “plan” phase you are allowed to build new turrets. Enemies will appear only during the “defend” phase. Surviving enemies waves will grant you in-game currency, which can be used to buy defences during the “plan” phase. Surviving at least three attacks and having enough currency will allow you to end the game, but there will not be a limited amount of waves you may fight. 
The more attacks you survive, the higher your score will be when finishing the game. 

To run the game, follow the instruction from HOW_TO_RUN.txt file.

Controls:
The player will be controllable by the following keys with their functions:
W - Player moves up
A - Player moves left
S - Player moves down
D - Player moves right
J - Player attack's in the direction it is currently facing

During the plan phase (indicated by the map being daytime) you can build turrets in the shop below the map.
To build a turret, click on the icon with a turret if you have sufficient currency (20) you can place your turret by clicking anywhere (unless there is already something there) on the map.
After surviving 3 rounds another button with a tank should appear, if you have sufficient currency (100) you can click this one and the ending of the game will play, giving you a score.

During the nighttime you will have to fight the opponents, you won't have to think about your turrets because they will funcion on their own. 
To fight the opponents yourself, stand close to them, face their direction and press J.
Note that you are still allowed to move while attacking, just at a slower pace. 
Opponents will also target your turrets and destroy them so don't rely on them to much!

Quick tips:
-Place turrets mostly in the middle of the map
-Walk backwards as soon as you hit an opponent to get out of range as quick as possible
-Invest in turrets and don't save everything up for the ending, you get increasingly more currency after you survive more rounds


Topics we learnt about:
1. Version control using Git
- we used Git to keep track of our project
- we introduced new features on separate branches (we have 19 brunches in our repository)
- we tried following good practices when adding comments to commits
- we learnt how to resolve conflicts between branches when doing pull requests
- we learnt how to collaborate on bigger programs using GitHub
- we learn basics of Git commands

Resources:
a) Git Tutorial from Stanford University [access date: 7.10.2024]:
  https://web.stanford.edu/class/cs124/lec/git_tutorial.pdf

b) Good practices from freecodecamp.org [access date: 7.10.2024]:
https://www.freecodecamp.org/news/git-best-practices-commits-and-code-reviews/

How we  found them:
searched “git” with site:edu and site:org filters


2. Test driven-development
- we implemented unit tests using JUnit
- we wrote unit tests before writing actual code, according to classical style of TDD
- we tested code after merging branches to check whether the new features work along the old ones
- we learnt to think what can go wrong with new code  

Resources:
a) JUnit tutorial from California State University, Long Beach [access date: 9.10.2024]:
https://home.csulb.edu/~pnguyen/cecs277/lecnotes/Unit%20Testing%20with%20JUnit.pdf

b) General introduction to TDD by GeeksForGeeks [access date: 9.10.2024]:
https://www.geeksforgeeks.org/test-driven-development-tdd/

searched “test-driven-development” with site:edu and site:org filters
