Feature: LogIn Feature

  Scenario: 1. User is able to Sign up in the application
    Given User open the browser
    And User navigates to the application url
    When User clicks on Sign up link at the top right corner of the application
    And User enters name and Phone number as mention in below table and clicks on Submit Button
      | name         | Shraddha   |
      | phone number | 8983207270 |

    Then User is displayed with the message as OTP is sent on number