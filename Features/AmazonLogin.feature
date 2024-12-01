Feature: Verify Amazon Login Functionality

Scenario: Verify Amazon shopping Login page
    Given Open browser
    And Launch then Amazon shopping url "https://www.amazon.in/"
    When Customer Enters email id "kure.onkar41@gmail.com"
    And click on continue button 
    And Customer Enters password as "Onkar@0390"
    And click on sign in button 
    Then Customer redirect to amazon shopping home page title should be "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in"  
    

  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
