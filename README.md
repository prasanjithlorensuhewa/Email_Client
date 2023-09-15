# Command-Line-Based-java-email-client
 This is a project done in 2nd semester using Java. This is a java based command-line application that can send emails to any recipients. It is Implemented using java and oop concepts. It uses Java Mail API to send emails and modified to save the emails sent using serialization and deserialization.

# How to run
First you need to install a package called javax.mail.package. This package is included in the javax.mail.jar website which can be download from here. https://javaee.github.io/javamail/#Download_JavaMail_Release

Then you need generate an App Password using guidence given below. https://support.google.com/accounts/answer/185833?p=InvalidSecondFactor

Then the generated password and email should be added to the respective place in the code of SendEmailTLS.java

Then you can compile and run App.java and check the following functionalities. The way to check all the functionalities is given in the code.

# Java Object Serialization
All the emails sent out by the email client is saved into the hard disk, in the form of objects – Object serialization. The user should be able to retrieve information of all the mails sent on a particular day by using a command-line option - Object deserialization.

# Goals
A program which support to send emails without accessing a browser and to serialize the emails that program sent.

# Purpose
This Application is used for sending emails to different types of recipients according to their positions.
In here,
 Official friends and personal recipients will be sent different messages (e.g. Wish you a Happy Birthday. <your name> for an office friend, and hugs and love on your birthday. <your name> for personal recipients). But all personal recipients receive the same message, and all office friends should receive the same message.
 
# Scope
This source file named EmailClient is the one user interacts.
Sending emails were performed by using java mail.
Mail Server like yahoo, Gmail, Hotmail, outlook can be used for accessing the mail send by the sender.
Additional - Sent a birthday wish to saved recipients automatically when the program starts.

# Deliverables
Command-line options available for :

 Markup : * Adding a new recipient
          * Sending an email
          * Printing out all the names of recipients who have their birthday set to current date
          * Printing out details (subject and recipient) of all the emails sent on a date specified by user input
          * Printing out the number of recipient objects in the application

# Knowledge Areas Needed for Project

 Markup : * Basic Programming Concepts
          * Java Programming Language
          * Object Orientated Programming Concepts
          * Objects Serialization & Deserialization
          * JavaMail API
          * Activation Jar file
          * General Constraints
          * The sender email-id and password is required. The mail will be sent to particular server







