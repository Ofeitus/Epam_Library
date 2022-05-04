# WebLib - Web library platform

The WebLib platform provides functionality to automate library management, including an interactive catalog, online booking, book stock management, and library activity reports.

## Roles

### Guest
- Log in
- Sign up
- View "About" page
- View "Contacts" page
- View books catalog
    - Search books
- View book details

### Member
- Log out
- View "About" page
- View "Contacts" page
- View books catalog
    - Search books
- View book details
    - Reserve book
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
- View books catalog
    - Search books
- View book details
    - Edit book data
    - Delete book
    - Issue book by user id
- View your profile
    - Edit personal data
- View inventory book
    - Search copies of books
    - Write in copies of existing book
    - Write in copies of new book
    - Write off copies of books
    - Delete copy of book
- View recent reservations
    - Cancel reservation
    - Confirm reservation
- View list of members
    - Search members
    - View member profile
        - View member loan
            - Issue book by inv. №
            - Return book
        - View member reservations
            - Cancel reservation
            - Loan reserved book
        - View member fines
            - Set fine status as paid

### Admin (Director)
- Log out
- View "About" page
- View "Contacts" page
- View books catalog
    - Search books
- View book details
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
