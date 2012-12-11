Milky-way games inc.
====================

http://wiki.eclipse.org/EGit/User_Guide#Manual_conflict_resolution

if "org.eclipse.jgit.errors.CheckoutConflictException"
then `Project | Team | Reset | Hard`

Git tutorial for dummies -> http://vk.cc/7NY3D <-

Deployment instructions
-------

List -_- :
* at first CREATE DATABASE `worldgames`
* `mvn tomcat:deploy`
* ???????
* `PROFIT`

inna commit***********
	Add check id, when player connect
	Auction 20sec
	Win/lose - working correctly
	Sending message after win or lose:
	type:logic
		game_state:{messages:(),player_loser:COLOR,player_money:(money),
		index:(list of players properties, can be available to buy by another players)}
		
		game_state:{messages:(),winner:COLOR,player_money:(money),game_end:ture/false}
*******************************