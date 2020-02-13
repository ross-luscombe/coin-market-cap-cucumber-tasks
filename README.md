# coin-market-cap-cucumber-tasks

###Pre-requisites for running the tests
To execute the tests you will need to add your api key as an environment variable; 
e.g. COINMARKETPCAP_API_KEY=a16e6789-1pl6-9876-95h5-87f5h56554z9

The drivers currently bundled with the tests are compatible with Chrome version 80.0.3987.100

###Executing the tests
The tests can then be execute with the maven command;

    mvn clean verify
 
 NOTE:  The tests should run on a Windows or Mac but have currently only been verified to work on a Windows machine.
