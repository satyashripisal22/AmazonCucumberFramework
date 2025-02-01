
Feature: Verify Time Slots page in admin

	@login
  Background: login for all scenarios 
    Given User opens URL "https://admin-ui-internal.homelyfservices.com/" on Chrome
    When User enters Username as "riturajsalavi.rs@gmail.com" and Password as "Password@1"
    And User click on login button
    Then User navigate to Home Page
  
  Scenario: Verify Admin TimeSlots
    Given logged in as an admin  
    And navigate to timeslot page
    When view the time slots on page 
    Then verify the id Start time end time colomn shows correct format
    When click on kebab menu 
    And click on preview option 
    Then verify the start and end time showing correct 