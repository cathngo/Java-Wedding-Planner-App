# infs2605-20t1-BreatheCollectionRoar
# Planit Event Planning Application
# (2) GitHub README
<p align="center">
  <img width="150" height="150" src="https://i.ibb.co/6J25kkL/smaller.png">
</p>
This is a JavaFX application designed for event planning in Netbeans 11 using OpenJDK 11, OpenJFX 11.

## System Requirements

For Windows:
* Windows 10, build 1903 (May 2019 update) or newer.

For macOS:
* macOS 10.14 Mojave or macOS 10.15 Catalina

## Application Requirements
* Netbeans 11,
* OpenJDK 11
* OpenJFX11

## Running the application
1. Download **"PlanitAppFinalEventApp"** Folder
2. Open Apache NetBeans IDE 11.2
3. Click *File* > *Open Project*
4. Find where the project is located within the directory and open it
5. With the project selected, click *Clean and Build*
6. Click *Run*

### Sample login credentials

Once the application is open, log in using the following credentials.

**Admin login details**\
Username: admin1\
Password: 123

**Guest login details**\
Access code: Guest Access Codes (Provided in Guests i.e. Jono620)\
*No password is required for guests*

## Admin User Guide
### EVENTS
**Creating an event**
1. In the form fields, enter the event name, date, event description (optional), location, time (in 24hr)
2. Click *Add*

**Viewing an event**
1. Select an event in the table to see its details
2. Click *Create Pie Chart* to generate a pie chart for guest responses

**Updating an event**
1. Click on the event you wish to edit
2. Change the event details as needed
3. Click *Update*

### Guests
**Adding a guest to the database**
1. In the form fields, enter the guest’s name, phone number and email address
Note: the username is automatically generated and cannot be changed
2. Click *Add*

**Adding multiple guests**
1. Click the *Upload Multiple Guests* button in the top left-hand corner of the screen
2. Fill in the details for the guests you wish to add
3. Click *Add guests*

**Updating a guest's details**
1. Select the guest in the table whose details you wish to edit
2. Change the guest’s details as needed
3. Click *Update*

***Note:** Guest usernames/access codes are randomly generated at creation and cannot be changed.*

**Inviting guests to an event**
1. Select the guest you would like to invite to an event from the table (Hold down the Ctrl key while selecting to select multiple guests)
2. In the dropdown menu under **Select guest(s) from the table and invite them to an event**, select the event you would like to invite them to
3. Click *Send invitation*

### Invitations
In this section, you are able to see all the guests who have been invited to an event

**Printing an invitation**
1. Select the invitation that you would like to print
2. Click *Create PDF Invitation*\
Note: The invitation PDF files are stored in the project folder

### Runsheets
**Updating a runsheet item**
1. Select the runsheet item in the table you would like to edit
2. Change the item details as needed
3. Click *Update*

**Adding a runsheet item**
1. In the dropdown menu under **Add/Update**, select the event you are creating a runsheet item for
2. Fill in the item time (in 24hr time) and description
3. Click *Add*

**Printing a runsheet**
1. In the dropdown menu under **Choose an event to print Runsheet PDF**, select the event you would like to print a runsheet for
2. Click *Print Runsheet*\
Note: The runsheet PDF files are stored in the project folder

### Logout
Click Logout in the navigation bar at the top of the screen to log out.

## Guest User Guide

### RSVPs
1. Select the invitation you would like to RSVP to from the table
2. Choose your RSVP response from the drop down list
3. Enter any dietary requirements int the text field
4. Press "Send/Update Response" button to submit your response
5. Need to change your response? Simply repeat the same steps and modify accordingly. Once sent, the event admin will receive updated response!

### Logout
Click Logout in the navigation bar at the top of the screen to log out.

# (3) Usability Summary

### Nielsen’s 10 Heuristics
Heuristics are commonly employed throughout various stages of the design process to evaluate the usability of interfaces and provides a cost-effective and practical means to effectively assess designs. In order to create a pleasant user experience, we have designed our application to adhere to Nielsen’s 10 Heuristics as much as possible.

#### Visibility of system status 
A well-designed application should make sure users can follow what is going on from the feedback provided. We’ve applied this to our application by providing feedback when the user pressed the “enter” button on the keyboard after entering the access code or username and password. It allows the user to know whether they have entered the correct information. Secondly, when the user creates an event, invites guests or creates a PDF invitation, there is a notification displayed to inform the user that the action has successfully been performed.

#### Match between system and the real world
Another important aspect of good UX is matching the system to the users as this reduces the unfamiliarity they may experience using a new application. As a result, in order to cater to both the Admin users and the casual Guest users, we ensured the language throughout the application is relatively simple and free of technical jargon, instead of sticking to common terms and phrases. Therefore, our application is designed for the average user.

#### User Control and freedom
In the real world, users might leave an unwanted state because of the mistake, so we designed the relative support to help the user eliminate their mistake. We have all of the buttons at the top, so the user can easily jump to the correct section. Also, we have the back button at the left top which means the application will allow the “oops” mistake. We also thought about the mistyped information, so we designed an update button which has the ability to update events, guests details and reservation responses.

#### Consistency and standards
Consistency in the design of a system greatly reduces uncertainty in the user experience
As such, we have made efforts to maintain both visual and functional consistency within our application. This is achieved through the use of the same colour scheme, font and navigation layout on each screen. In addition, the processes by which actions are completed are similar in nature and predictable. As a result, the user does not have to guess what each button does, even if they are new to the system.

#### Error prevention
Another aspect of good design is the prevention of user error. We have implemented this by eliminating areas which commonly caused user error in testing and reducing the elements on the screen to only those which have a potential user action. In this way, only buttons that can be clicked and are functional in that condition are visible to the user.

#### Recognition rather than recall
Reducing the memory load on the user is vital for a comfortable and easy UX and we have addressed this issue by making actions and options visible to the user. For most actions such as creating an event, or inviting guests, the user only needs to navigate through two pages, these being the Home page and the second page upon which action can be taken. There is no need to recall long processes to complete a simple action. 

#### Flexibility and efficiency of use
A system’s ability to be used by different types of users is important and we have attempted to maximise this by creating an interface that is easy to use and is hence, approachable. We have also accounted for different usages of the application by providing two different methods by which to add guests to the database. Users can add guests individually, or they can add up to ten guests at once, for increased efficiency. Similarly, users can invite multiple guests to an event.

#### Aesthetic and minimalist design
In order to provide users with a clean and neat interface and experience, the application should not include any irrelevant information as it might increase the user's usage burden. To achieve this, we made each section of our app clean and simple, using the consistent scheme colour for every section of the app. Meanwhile, we designed a reasonable distance between each section on the page to prevent it from looking crowded or overwhelming to the eye. Most importantly, everything shown on the screen has a specific purpose, there are not any unmeaningful words or buttons.

#### Help users recognise, diagnose and recover from errors
In order to have a better experience, when the user takes an action which leads to an error, an error message should be displayed. The message should include what kind of problem is existing and how to solve it, therefore, users will not be bothered to find the error and how to overcome it. In our design, error messages appear when an action is not possible or out of range and the error message is written in plain language that explains what is wrong. To make it more obvious, we made a red “X” shape symbol to indicate.

#### Help and documentation
We have provided some additional assistance to the user in the form of a Help button. In the case of this application being actually implemented, the Help section would direct the user to contact the event planning company’s customer service staff.

# (4) Project Management Summary

The team was very conscious that it was very important to set up a project management plan that was conducive to being collaborative, transparent and empathetic with respect to the group assignment. From a task management perspective, the Gantt Chart outlines an exhaustive list of the requirements and deliverables that ensured the entire team could comprehend the full scope of the project and could plan accordingly.

### 1.1 Gantt Chart
<p align="center">
  <img src="https://i.ibb.co/m9nfNf7/gantt.png">
</p>

As well as utilising this tool, the team has implemented a weekly two-hour meeting (that transitioned from face-to-face to virtual) from 11am-12pm on a Thursday to run through a ‘standup’ type meeting and raise any immediate questions with regards to the project. The stand up consisted of answering 3 questions;
*What was done last week?
*What is going to be done this week?
*Any blockers?

We found this to be a really useful, practical and productive way of structuring our weekly meetings and lent credibility to the scrum framework we tried to employ for the coding aspect of our assignment. Despite not being across all the intricacies of the scrum framework, each requirement was structured in deliberately short “sprint,” to maximise the amount of output that could be produced in the available. 

The team had a very limited coding experience prior to INFS2605, so it took  a little longer to adapt to using GitHub. As a result, we tended to work fairly independently at the start, only adapting to use the full extent of GitHub towards the latter end of the project timeline. Furthermore, the group communicates regularly via Messenger and also utilised G-Suites (Docs, Drive) to ensure all work is collaborative. 

