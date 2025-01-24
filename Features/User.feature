Feature: Users

  Background: login for all scenarios 
    Given User opens URL "https://admin-ui-internal.homelyfservices.com/" on Chrome
    When User enters Username as "riturajsalavi.rs@gmail.com" and Password as "Password@1"
    And User click on login button
    Then User navigate to Home Page
  
  Scenario: Search User By Email
    Given Select User option on Home Page 
    When Enter email Address in Search Field
    Then Display User on select Email Address
    
    
    

    
  

 