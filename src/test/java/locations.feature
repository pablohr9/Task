Feature: Location
  Navigate to the site and get address 

  Scenario: Navigate to the site and get address
   Given Open western union page
   When open agent locations
   Then Get address details of the first location