# WebLib - Web library platform

*description*

## Roles

### Guest
- Log in
- Sign up *(needs validation)*
- View "About" page
- View "Contacts" page
- View books catalog
    - Search books
- View book details

### Member
- Log in
- Log out
- View "About" page
- View "Contacts" page
- View books catalog
    - Search books
- View book details
    - Reserve book
- View your profile
    - Edit personal data *(needs validation)*
- View your loan
- View your reservations
    - Cancel reservation
- View your fines

### Manager
- Log in
- Log out
- View "About" page
- View "Contacts" page
- View books catalog
    - Search books
- View book details
    - Edit book data *(needs validation)*
    - Delete book
    - Issue book by user id
- View your profile
    - Edit personal data *(needs validation)*
- View inventory book
    - Search copies of books
    - Write in copies of existing book
    - Write in copies of new book *(needs validation)*
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
- Log in
- Log out
- View "About" page
- View "Contacts" page
- View books catalog
    - Search books
- View book details
- View list of users
    - Search users
    - Set user role
    - Soft delete user
    - Restore user
- View reports
    - Get report for the period