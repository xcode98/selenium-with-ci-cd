
Feature: Feature Formulario

Rule: The user etc


Scenario Outline: As a Test Engineer, I want to validate that a text is present inside the list   
    Given I navigate to the list page
    When I search <city> in the list
    Then I can find <state> in the list

Examples:
|city     |state                  |
|Lima     |Lima, Provincia de Lima|