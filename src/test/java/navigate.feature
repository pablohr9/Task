Feature: Navigate
  Navigate to WU site and chage country

  Scenario: Navigate to WU site and chage country
    Given Navigate to western union page
		When Expand Burger menu
		When Select Settings page
		When Change WU.com Country to United States
		Then Assert that correct country page was loaded.
    