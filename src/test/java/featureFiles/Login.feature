#Facebook login
@tag
Feature: Login

  @tag1
  Scenario: User should be able to login to facebook using valid credentials
    Given browser is open
    When user enters URL
    Then login page is displayed
    And user enters username and password and clicks on login button
    Then homepage should be displayed