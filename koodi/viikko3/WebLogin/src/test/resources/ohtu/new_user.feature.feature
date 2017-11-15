Feature: New user can create an account and logout

Scenario: user can create an account
Given new user is selected
When new username "lauri" and password "lauripass2" and password confirmation "lauripass2" are given
Then new account is created

Scenario: can logout with successfully generated account
Given new user is selected
And   new username "laura" and password "laurapass1" and password confirmation "laurapass1" are given
And   navigate to main page is selected
When  logout is selected
Then  user is logged out