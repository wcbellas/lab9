Ability: Parsing User Input

  Background:
    Given the game parser
    
  Scenario: Parse a valid single word command
    When the command "inventory" is parsed
    Then the verb is "inventory"
    And  there is no direct object
    And  the parser response is "Success"