# Weavr - QA Engineer Test

This repository contains sample projects for backend and frontend QA automation as part of the Weavr QA Engineer assessment.

---

## Backend Automation

**Folder:** `InterviewBackendTest`  
**Tech Stack:** Java, JUnit, RestAssured, Maven  
**API:** [Go Rest](https://gorest.co.in/)

### Overview
This project provides a setup to test APIs using JUnit and RestAssured. A sample test class is included to help you start writing tests for Go Rest endpoints.

### Requirements
- Use the implemented **Create User** endpoint to write a set of functional tests.
- Implement tests for the **Update User** endpoint.
- Feel free to update or refactor the project as needed.


### Test Results
Running CreateUserTests
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0

Running UpdateUserTests
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0

BUILD SUCCESS

### How to Run
```bash
cd InterviewBackendTest
mvn clean test 