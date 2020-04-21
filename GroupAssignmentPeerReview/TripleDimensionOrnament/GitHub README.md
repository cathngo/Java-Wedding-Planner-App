# GitHub README

####TLEvent – First Class Planner 

This is a Maven JavaFX Application is develop using NetBeans with below specification:
Product Version: Apache NetBeans IDE 11.3 
Java: 11.0.7; OpenJDK 64-Bit Server VM 11.0.7+10 
Runtime: OpenJDK Runtime Environment 11.0.7+10 
System: Windows 10 version 10.0 running on amd64; GBK; en_US (nb) 
User directory: C:\Users\poh_y\AppData\Roaming\NetBeans\11.3 
Cache directory: C:\Users\poh_y\AppData\Local\NetBeans\Cache\11.3 

Acknowledgements
The purpose of developing this project is the course assignment of INFS2605, 2020 Trimester 1, Business School, University of New South Wales. Please read the copyright statement below before you use this application:
Copyright © 2020. We, the developers of this software application, declare that it is our work towards an assessment item submitted to fulfil the requirements of INFS2605 (UNSW Sydney). We declare that it is our own work, except where acknowledged, and has not been submitted for academic credit elsewhere. We acknowledge that the assessor of this item may, for the purpose of assessing this item: Reproduce this assessment item and provide a copy to another member of the University; and/or, Communicate a copy of this assessment item to a plagiarism checking service (which may then retain a copy of the assessment item on its database for the purpose of future plagiarism checking). We certify that we have read and understood the UNSW University Rules in respect of Student Academic Misconduct.
System Requirements
For macOS:
#macOS 10.14 Mojave or macOS 101.15 Catalina
For Windows:
#Windows 10, build 1903 (May 2019 update) or newer

Startup
1.Open Apache NetBeans IDE 11.3
2.Click “File” on to right -> Open the project or Import the project if it is zip
3.Right click on the project -> Click “Clean and Build”
4.Wait the project to download required dependencies
5.Click “Run” when the project is ready

Default Login Credential:
Admin User:
#Username: admin
#Password: admin
Guest User:
#Access code: guest1234
#Password: password

How the application functioning?
##For admin:
oAdmin can create, edit and delete event
oAdmin can send invitation to Guest Once invitation was sent, only the invitation note can be modified
oAdmin can manually create or delete Guest and Admin
oAdmin can generate event run sheet pdf in EventDetailScreen and see statistic of the Event
o
##For Guest:
oGuest can create account by themselves, but access code is auto generated which combine first and last name with a 4 digits random number
oGuest can reply to invitation through RSVP form
oRSVP Form cannot be submitted on expired invitation
##Both
oBoth type of user can change their profile information by click on their name tag on top left
oBoth type of user can logout by click the logout button on top right