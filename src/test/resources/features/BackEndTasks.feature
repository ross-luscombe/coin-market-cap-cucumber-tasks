@api
Feature:  Back-end Tasks

  Scenario Outline:  Back-end Task 1
    When Simon converts <cryptocurrency> into Bolivian Boliviano
    Then the <cryptocurrency> will be converted into Bolivian Boliviano

    Examples:
      | cryptocurrency |
      | Bitcoin        |
      | Tether         |
      | Ethereum       |

  Scenario:  Back-end Task 2
    When Tracy retrieves the metadata for Etherium
    Then the metadata for Etherium will be successfully returned

  Scenario:  Back-end Task 3
    When Roger requests cryptocurrencies the first 10 cryptocurrencies
    Then he will see 10 are mineable
