# accountservice
Account Service POC

Basic Service for playing with Spring, and create a quite simple account service.

# Tech Decissions
Service is not tested at unit level. Controllers are validated at functional level, Database does not require to be validated, as we rely on spring Data.
There is one class TransactionService that can be tested with mockito, but I have no time at this moment.

# Things to revisit
There is an error in jackson when Deserializing Account. Probably it is a quite clear example of overengineering. On the other side, 
I am quite sure that logically it maps problem (Domain definition I mean) as it should. There is some missconfiguration on ObjectMapper
I am not able to find now, but it should be feasible.
For the size of the project, I should start with simple Account object, and later on create two children, but, as this
is not a production code, I can allow this.

# Improvements
There are plenty of things that can be improved, for instace:
* Move Database from inMemory to real one
* For tests, have a copy from prod database, minimized and anonymized, and create a docker image (if someday in prod)
* Add more functional tests, like cucumber tests in a real scenario (app in one container, DB in another one)
* Add swagger (I still don't know why I do not add it know :) )
* Analyze if validation can be done via Hibernate Validator, not sure how it will do logic for Treasury or not validation

