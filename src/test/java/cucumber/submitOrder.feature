Feature: Purchase the order from Eomm Website

Background:
Given User on Eomm landing page

@Regression
Scenario Outline:
Given Logged with username <name> and password <password>
When I add the product <productName> to the cart
And checkout <productName> and submit the order
Then "THANKYOU FOR THE ORDER." message is displayed on the confirmation page
And I close the browser

Examples:

| name     					   | password   | productName |
| milindkrishna1998@gmail.com  | Milind@98  | ZARA COAT 3 |
