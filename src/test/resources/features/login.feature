Feature: Login functionality of UAMS application

  Background:
    Given User is on login page

  # ===============================
  # Positive Scenario
  # ===============================
  @validLogin
  Scenario Outline: Verify login with valid credentials

    When user enters username "<Username>"
    And user enters password "<Password>"
    And user clicks on login button
    Then User should be logged in successfully

  Examples:
    | Username           | Password  |
    | rakeshr@optimeyes.ai  | Rakesh@9254  |

@invalidPassword
  # ===============================
  # Negative Scenario - Invalid Password
  # ===============================
  Scenario Outline: Verify login with invalid password

    When user enters username "<Username>"
    And user enters password "<Password>"
    And user clicks on login button
    Then User should see error message "Credentials mismatch!! Could not login"

  Examples:
    | Username           | Password  |
    | rakeshr@optimeyes.ai | Wrong123  |

@invalidUsername

  # ===============================
  # Negative Scenario - Invalid Username
  # ===============================
  Scenario Outline: Verify login with invalid username

    When user enters username "<Username>"
    And user enters password "<Password>"
    And user clicks on login button
    Then User should see error message "No user found!!"

  Examples:
    | Username          | Password  |
    | wrong@mail.com    |Rakesh@9254 |

