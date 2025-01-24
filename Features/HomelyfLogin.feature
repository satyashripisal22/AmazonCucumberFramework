Feature: HomelyfLogin
  
  @login
  Scenario: Successful login with valid credentials
    Given User opens URL "https://admin-ui-internal.homelyfservices.com/" on Chrome
    When User enters Username as "riturajsalavi.rs@gmail.com" and Password as "Password@1"
    And User click on login button
    Then User navigate to Home Page
    @Regreesion
    Scenario Outline: Login with Invalid credentials
    Given User opens URL "https://admin-ui-internal.homelyfservices.com/" on Chrome
    When User enters Username as "<email>" and Password as "<password>"
    And User click on login button
    Then User navigate to Home Page 
    
    Examples:
    |email|password|
    |testadmin@gmail.com|admin@123|
    |test@gmail.com|Admin@123|

  
