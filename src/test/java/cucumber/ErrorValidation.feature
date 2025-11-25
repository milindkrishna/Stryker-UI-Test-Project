Feature: Error Validation

Background:
Given 

@ErrorValidation
Scenario Outline:
Given User is on Eomm landing page
And Logged in with username <name> and password <password>
Then "Incorrect email or password." message is displayed
And I close the browser and end the session

Examples:
| name                       | password   |
|milindkrishna1998@gmail.com | milind@990 |
