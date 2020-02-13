@web
Feature:  Front end tasks

  Scenario:  Frontend Task 1
    Given that Thomas is viewing the top 100 cyrptocurrencies by market capitalisation
    When he requests to view all cryptocurrencies
    Then at least 100 cryptocurrencies will be returned

  #Scenario: Frontend Task 2
  #  Given that Jane adds between 5 and 10 currencies to a watchlist
  #  And she views the watchlist
  #  Then the watchlist will include the selected currencies