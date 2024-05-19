# WebLib - Web library platform

The WebLib platform provides functionality to automate library management, including an interactive catalog, online booking, subject stock management, and library activity reports.

## Roles

### Guest
- Log in
- Sign up
- View "About" page
- View "Contacts" page
- View subjects catalog
    - Search subjects
- View subject details

### Member
- Log out
- View "About" page
- View "Contacts" page
- View subjects catalog
    - Search subjects
- View subject details
    - Reserve subject
- View your profile
    - Edit personal data
- View your loan
- View your reservations
    - Cancel reservation
- View your fines

### Manager
- Log out
- View "About" page
- View "Contacts" page
- View subjects catalog
    - Search subjects
- View subject details
    - Edit subject data
    - Delete subject
    - Issue subject by user id
- View your profile
    - Edit personal data
- View inventory subject
    - Search copies of subjects
    - Write in copies of existing subject
    - Write in copies of new subject
    - Write off copies of subjects
    - Delete copy of subject
- View recent reservations
    - Cancel reservation
    - Confirm reservation
- View list of members
    - Search members
    - View member profile
        - View member loan
            - Issue subject by inv. №
            - Return subject
        - View member reservations
            - Cancel reservation
            - Loan reserved subject
        - View member fines
            - Set fine status as paid

### Admin (Director)
- Log out
- View "About" page
- View "Contacts" page
- View subjects catalog
    - Search subjects
- View subject details
- View your profile
    - Edit personal data
- View list of users
    - Search users
    - Set user role
    - Soft delete user
    - Restore user
- View reports
    - Get report for the period

## Database Diagram
[![db.png](https://i.postimg.cc/RZNn5QCN/db.png)](https://postimg.cc/5XWt8zCV)
