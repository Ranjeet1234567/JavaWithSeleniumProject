# JavaWithSeleniumProject
# Selenium Java Automation Framework (POM Model)
# This is a robust Selenium Automation Framework built using Java, Maven, TestNG, and Page Object Model (POM).
# It supports cross-browser execution, Allure reporting, and automatic email delivery of test reports after execution.
# Tech Stack
# Tool	              Purpose
# Java	              Programming language
# Selenium WebDriver	  Browser automation
# Maven	              Build & dependency management
# TestNG	              Test execution & suite management
# Page Object Model	  Framework design pattern
# Allure Report	      Advanced test reporting
# JavaMail API	      Email report integration
# Jenkins	CI/CD integration (optional)
📂 Project Structure (POM Model)
JavaAutomationFramework
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── base        → BaseTest, Driver setup
│   │   │   ├── pages       → Page Object classes
│   │   │   ├── utils       → Utilities (Email, Zip, Config)
│   │   │   └── listeners   → TestNG listeners
│   │
│   └── resources
│       └── config.properties
│
├── src
│   └── test
│       ├── java
│       │   └── tests       → Test classes
│       └── resources
│           └── testng.xml
│
├── target
│   ├── allure-results      → Allure raw results
│   ├── allure-report       → Generated HTML report
│   └── surefire-reports
│
├── pom.xml
├── Jenkins file
└── README.md

# Framework Flow
TestNG XML
↓
Test Classes
↓
Page Objects
↓
Selenium Actions
↓
Assertions
↓
Allure Results (JSON)
↓
Allure HTML Report
↓
ZIP Report
↓
Email Attachment

# Test Execution
▶ Run Tests Using Maven
mvn clean test
# Always use Maven execution for proper Allure reporting.
# Allure Report Integration
# Generate & Open Report
# allure serve target/allure-results
OR
# allure generate target/allure-results --clean
OR
# allure generate target/allure-results -o target/allure-report --clean
# allure open
# Features
# Test execution summary
# Graphs & trends
# Failure screenshots
# Timeline view
📧 Email Report Integration
After test execution:
✔ Allure report is generated
✔ Report is zipped automatically
✔ ZIP is attached and sent via email

Email Trigger
Implemented using TestNG @AfterSuite
Uses Google App Password (SMTP)
🔐 Gmail App Password Setup (Required)
Enable 2-Step Verification
Go to Google App Passwords
Select:
App → Mail
Device → Other (Automation)
Generate password
Use it in EmailUtils.java