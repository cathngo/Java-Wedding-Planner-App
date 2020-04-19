# README
Please open this file in GitHub for easier readability!

## Sample Credentials for Login
|Admin User|Guest|
|---|---|
| User: admin462 Pw: hello21  |  Code: PeterKim2341 |
| User: admin29138 Pw: chocolate23 | Code: WilliamWong9837 |
| User: admin239 Pw: yellowblue34 | Code: KellyLu0193  |

For more sample credentials for login, run "DatabaseManager.printObjectsInTable("admin")" OR "DatabaseManager.printObjectsInTable("guest")" in App.java to view all credentials.

## Admin View Features
There are four main functions on the side menu: **Events**, **Guests**, **Invitation** and **Runsheets**. The following tables explain the features available for each function:

**Events**
|  Feature  | Navigation  | Description  |
|---|---|---|
|  View Events | Events  | -Able to see all events created in a table.<br/> -To view a particular event in more detail, select an event and press ‘View Details’.|
|  Create Event |  Events -> Create New Event |  Able to create a new event |
|  Edit Event |  Events -> View Details -> Edit |  Able to edit an existing event |
|  View Guest List | Events -> View Details -> Guest List  | Able to see the guests invited to  a particular event, followed by a **RSVP pie chart**  |
|   Invite Existing Guests|  Events -> View Details -> Guest List -> Invite Guests <br/> OR <br/> Event -> Invite Guests| -You will be able to select multiple guests from the ‘Existing Guests’ table and add them to a ‘Guest List’ by clicking ‘Add to Guest List’.<br/> -Remove guests from guest list through ‘Remove’. <br/> -Press ‘Invite to Event’ to invite all guests in the Guest List.|
|  Create and Invite New Guests | Events -> View Details -> Guest List -> Invite Guests  -> Invite New Guests  <br/>OR<br/>Event -> Invite Guests -> Invite New Guests| Able to create a guest and add them to the guest list for an event |

**Guests**
|  Feature  | Navigation   |  Description |
|---|---|---|
|  View Guest | Guests  |  -Able to see all existing guests. <br/> - To view a particular guest in more detail, select a guest and press 'View Guest'<br/> -Able to view a guest's unique **access code** |
| Create Guest  | Guests -> View Guest  | -Able to create a new guest. <br/> - Automatically generates **guest access code** when created  |
|  Edit Guest | Guest -> View Guest -> Edit  |  Able to edit an existing guest |
|  Invite Guest | Guest -> View Guest -> Edit -> Invite Guest <br/> OR <br/> Guest -> Invite Guest -> Invite to Event  |  Able to select an event to invite a guest to |

**Invitation**
|  Feature | Navigation  |  Description |
|---|---|---|
|  View Invitations |  Invitations | -Able to view all created invitations.  <br/> -Select an invitation and press 'View Invitation PDF' to open the invitation in a PDF viewer |
| Create Invitation  |  Invitations -> Create Invitation -> Create Invitation PDF |After selecting an event, an invitation PDF will be automatically created for the event   |

**Runsheets**
|  Feature | Navigation  | Description  |
|---|---|---|
| View Runsheets  | Runsheets   | -Able to view all created runsheets <br/>  -Select a runsheet and press 'View Runsheet PDF' to open the runsheet in a PDF viewer |
|  Create Runsheet | Runsheets -> Create Runsheet -> Select Event -> Create Runsheet | -Able to add contents to a runsheet and generate it into a PDF |
|  Edit Runsheet |  Runsheets -> Edit Runsheet | -**NOTE: must close the runsheet if it is opened in another program before editing** <br/> -eg. if 'View Runsheet PDF' was clicked, ensure the PDF is closed before editing runsheet  |

## Guest View Features

**Guest Dashboard**

|  Feature | Navigation   |  Description |
|---|---|---|
|  View Invitations Guest Has Already Responded Too |  Dashboard |  Able to see all events guest has already submitted an RSVP to |
|  Edit RSVP |  Dashboard -> Edit RSVP <br/> OR <br/> Dashboard -> View Details -> View RSVP -> Edit | Able to edit a RSVP to a particular event that is selected  |
|  View Event Details | Dashboard -> View Details  |  Able to view the details of a particular event that is selected |
|  View RSVP |  Dashboard -> View Details -> View RSVP | Able to view a submitted RSVP to a particular event  |
|  View Invitation PDF | Dashboard -> View Details -> View Invitation  |  -Opens the invitation of the event in a PDF viewer program if available|
|  View Runsheet PDF | Dashboard -> View Details -> View Runsheet  |  -Opens the runsheet of the event in a PDF viewer program if available |

**Invitation**
|  Feature | Navigation   | Description  |
|---|---|---|
|  View Pending Invitations |  Invitation | Able to view invitations guest has not yet submitted an RSVP for  |
|  Submit RSVP | Invitation -> Submit RSVP  | Able to submit a RSVP to an event yet to be responded to  |

