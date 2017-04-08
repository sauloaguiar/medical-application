## Care Management Application

### Motivation

This is a learning project that involves application modeling, programming and development best practices.

### Application Context

The overall idea is to create a system that allows users to manage med administration for a third person.
Initially, the system should support three user roles - admin, caregiver and patients. Admins are special types of caregiver - they can manage the meds to be administered - add/remove them or change the time that they should be given to a patient.
A caregiver will be notified whenever a med should be administered and will certify that it was taken.
Every med should be associated with a patient.
A caretaker can provide services for more than one patient.

#### Domain Considerations

The following initial questions provide a guideline to the overall system development.

1. Which caregiver was assigned to a patient?
2. What is the schedule of a specific caregiver?
3. Which caregiver gave medications between a time period?
4. How to retrieve the frequency that a med should be given to a patient?
5. How to keep history of the medications given to a patient?
6. Which medications does a patient takes?
7. To which patients am I giving care to?
8. Which relatives am I responsible for?

### Technology Stack

* [Kotlin](https://kotlinlang.org/)
* [Spring Boot](https://projects.spring.io/spring-boot/)
* [Docker](https://www.docker.com/)
* [Potsgresql](https://www.postgresql.org/)

Check the wiki section on how to setup the environment.
