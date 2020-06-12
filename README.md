# **Hangman**

A simple hangman game reimagined as the original pen and pencil game You have to guess the word by choosing letters but if you choose wrong 6 times then it&#39;s game over. In this version of Hangman, you are given the choice between characters; the original hangman and a female variant. You also get to choose your preferred style of keyboard, either the QWERTY or the ABCDEF keyboard layout.

**Functional Properties:**

- User uncovers the hidden word by guessing letters. Each round equals one point.
- User can choose between characters.
- User can choose between 2 keyboard layouts.
- User can toggle the sound effects on or off.
- App will retain User Preferences and score using shared preferences.

**UI Design:**

Many modern iterations of Hangman follow a cleaner UI with highly detailed graphical elements. However, my goal with the design of this game was to reimagine it as the original paper and pencil game it is and in order to give the user that experience, the UI had to look like it was drawn by hand. The main background used in all the activities resembles a sheet of paper and all the visual elements were drawn using a custom brush that resembles ink on paper. I used Photoshop to create all my graphic designs for this project.

| ![](RackMultipart20200612-4-i8y1re_html_9e1ee58303e97bc6.png)Home Screen | ![](RackMultipart20200612-4-i8y1re_html_1c5205105452742a.png)Settings screen | ![](RackMultipart20200612-4-i8y1re_html_37b4df9879e71741.png)Gameplay screen |
| --- | --- | --- |

**Architecture:**

The Game follows a Model-View-Controller (MVC) Architecture. Initially the game would have two modes, a classic mode and a time based mode but after interviewing some users, I decided to scrap the time based mode as the idea was not well received. However, due to the modular design of the codebase, adding an extra game mode later would not be difficult. The core logic of the game is separate from the mode classes and can be reused by multiple game modes with the addition of a few interfaces.

**Challenges:**

When developing applications for android, one of the main challenges a developer faces is to make the app support the various screen sizes. But with Hangman having custom design elements, supporting different screen sizes was slightly more challenging than I had originally anticipated. It took quite a bit of testing and creating multiple dimens.xml files to have the different views adapt to different displays. The latest version of the game supports mobile screen sizes ranging from 3.5 to 7.x inches.

**Its on the Play Store!**

Hangman was an extremely fun project to work on and I&#39;m very proud to have it published on the playstore. If you&#39;d like to try it out, you can get the game here: https://play.google.com/store/apps/details?id=com.hfad.ded.
