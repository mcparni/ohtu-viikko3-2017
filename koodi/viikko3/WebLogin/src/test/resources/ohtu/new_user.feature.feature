Feature: New user can create an account and logout

Scenario: user can create an account
Given new user is selected
When new username "lauri" and password "lauripass" and password confirmation "lauripass" are given
Then new account is created

Scenario: can logout with successfully generated account
Given new user is selected
And   user "laura" with password "laurapass" is created
And   navigate to main page is selected
When  logout is selected
Then  user is logged out