Win4Generator
A Win 4 Lotto Number Generator implemented in JavaFX, along with Selenium for website parsing. 

The central idea behind this application is to provide randomly generated lotto numbers that have not been used in the past, under the assumption that previous lotto numbers are not used. From a pure probability standpoint, this idea is mathematically incorrect, but makes for an interesting idea nonetheless.

Since the page for finding the previous winning Win 4 numbers only really works by interfacing with a web UI (need to interact with a web app), I used Selenium's PhantomJSDriver to virtually browse and parse the webpage for the winning numbers, which is then saved in the "data" directory as a .txt file. 

<br>
<div align="center"><img src="https://i.imgur.com/bU1quru.png"/></div><br>
Image of the Win4Generator GUI
<br>
<br>

## Features

### Win 4 Number Generation: 
Generates 4 numbers, excluding numbers that have won in the past.

### Updating:
Put simply, clicking the "Update" button will update the data/winners.txt file. The latest date to which the data has been updated to is also listed on the main GUI window, useful for knowing whether the data needs updating or not.

### Settings:
Clicking the gear in the top right will launch the access to the settings:
<div align="center"><img src="https://i.imgur.com/qZvIgxY.png"/></div><br>
Image of the Settings GUI

#### Order Matters
By default, the program will have this setting checked off. The Win 4 numbers follow a particular order, and if one wishes to exclude number combinations that have been used (regardless of order), this setting can be toggled to off. Therefore, keeping this on will simply reject randomly generated lotto numbers if they match exactly, but not if they use the same pool of integers.

######*NOTE:* This software was written for the Win 4 page as it appeared from 2016-2017. This application may not update correctly with the current version of the page, should it have changed. 

<br>
<br>
## Future Implementation
* Ability to turn filtering of previously used values entirely
* Updating to work with the current version of of the past winning numbers page

-----------------------------------------------------------------------------------------

## Appendix

### A. "Win 4 Past Winning Numbers | New York Lottery": http://nylottery.ny.gov/wps/portal/Home/Lottery/home/your+lottery/winning+numbers/win4pastwinning+numbers
