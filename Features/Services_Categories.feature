

Feature: Verify Admin Service Categories Page
	
	
	 Background: login for all scenarios 
    Given User opens URL "https://admin-ui-internal.homelyfservices.com/" on Chrome
    When User enters Username as "admin@gmail.com" and Password as "Admin@123"
    And User click on login button
    Then User navigate to Home Page 
	
	@serviceCategory
  Scenario: Verify categories and search functionality on the service page
    Given the user is on the service page
    When the user verifies that the categories list is displayed
    And the user searches for a category using the search box with the keyword "spa"
    Then matching categories should be displayed in the results
	
	@pagination
  Scenario: Verify pagination for categories
    Given the user is on the service page
    When the user selects 10 rows per page
    Then verify that exactly 10 rows are displayed

	@rowData
  Scenario: Verify category row data and actions
    Given the user is on the service page
    When the user verifies that each category row contains a name and description
    Then each row should provide an action menu for further operations

	@Addservice
  Scenario: Verify adding a new category
    Given the user is on the service page
    When the user clicks the Add category button
    And fills out the form with valid category details
    Then the new category should be listed in the table

