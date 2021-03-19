# 5straight

### Latest Changes

Check out the project to see the full backlog  of features and the current features being worked on.

For the next version I will be working on:
 - Fixing the bug that sometimes does not detect a winning condition
 - Adding a dialog that displays the bots chosen move when playing a bot
 - Adding a tutorial mode using Swing Tool tips

### Motivation
I chose to build this project to improve my Java skills, practice building a User interface and practice using Artifical intelligence Algorithms. The motivation for choosing the game 5-Straight was simply because it was unique. I am not aware of any implementations of this game, which would force me to build it from scratch.

### Technology
This is built with Java, Java core API's and Swing.

### Project Overview
The official game rules can be found here: http://5straight.net/play.html

 - To start the game, checkout the latest version branch
 - run `java -jar out/artifacts/5straight/5straight.jar`

### Repository Overview

Beginning with Version 3, I am using Gitflow to develop this project.
 - Main contains the latest stable version
 - Devel contains the latest unstable version

Historical Branches (Not maintained)
 - version-1
 - version-2
 - version-3

### Project Structure

package game
 - Contains the game board, card deck, and game classes

package player
 - Contains Abstract and concrete classes used to build AI players

package ui
 - Contains ui components

package gui
 - Combines all of the components into a Swing application

As of version-3, JavaDoc has been added to the project.
From src/main/java directory, run `javadoc -d <path to docs directory> game gui ui player`
