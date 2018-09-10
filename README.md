# RiddleMe 

## Description
A simple trivia app built for the purposes of testing your Justin Bieber/Computer 
History knowledge. There is accompanying music from the Star Wars movies depending 
on if you pick Jedi or Vader as your side. 

## Technical
The application implements:
- `SharedPreferences` in order to keep up with the user's preferences. Since these save locally on the device, this persists preferences across sessions. SharedPreferences also allow music to be muted/unmuted.
- `SQLite` queries to fetch from the created question and answer database.
- `BackgroundSoundService` in order to have persistent music playing across all screens, depending on which avatar was chosen.
- `Animation` for letting the user know about achieving a high score.
- Splash screen upon opening the application.
- High score saved on the device, as well as the ability to reset it.


## Application

| ![](/app/media/splash.png) | ![](/app/media/first.png) |
|:---:|:---:|
| The opening splash screen | The main login screen |

| ![](/app/media/entered-info.png) | ![](/app/media/home.png) |
|:---:|:---:|
| The user enters information | The home screen |

| ![](/app/media/quiz.gif) | ![](app/media/settings.gif) |
|:---:|:---:|
| Correct, Incorrect, and High Score | The settings screen|

| ![](/app/media/jedi.png) | ![](app/media/vader.png) |
|:---:|:---:|
| The app remembers upon re-login | Name and avatar preferences saved|

## Design
I attempted adherence to Material Design principles with respect to layouts, colors, as well as positive and negative space. This is seen in the custom-made layouts, as well as the dynamic aspects of getting a question correct or incorrect. 

In addition, the design for the application was created using PlantUML, located under the [UML](/app/UML) directory.

## Miscellaneous
This application has been optimized to run on a Nexus 6 running Nougat API level 25.