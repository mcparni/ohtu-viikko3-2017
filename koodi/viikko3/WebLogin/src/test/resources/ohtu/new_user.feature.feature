Feature: A new user account can be created if a proper unused username and password are given

Scenario: creation is successful with valid username and password
Given command new user is selected
When  a valid username "liisa" and password "salainen1" and matching password confirmation are entered
Then a new user is created

Scenario: creation fails with too short username and valid password
Given command new user is selected
When  too short username "li" and valid password "salainen1" are entered
Then user is not created and error "username should have at least 3 characters" is reported

Scenario: creation fails with correct username and too short password
Given command new user is selected
When a valid username "uolevi" and too short password "salsa" are entered
Then user is not created and error "password should have at least 8 characters" is reported 

Scenario: creation fails with already taken username and valid pasword
Given command new user is selected
When  valid but reserved username "jukka" and valid password "akkujjukka1" are entered
Then user is not created and error "username is already taken" is reported

Scenario: creation fails when password and password confirmation do not match
Given command new user is selected
When  a valid username "toivo" and valid password "voivoitoivo1" but mismatching password confirmation "voivoitoivo2" are entered
Then user is not created and error "password and password confirmation do not match" is reported 

Scenario: can logout with successfully generated account
Given command new user is selected
And   new username "laura" and password "laurapass1" and password confirmation "laurapass1" are given
And   navigate to main page is selected
When  logout is selected
Then  user is logged out