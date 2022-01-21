# WebLib - Web library platform

*description*

## Roles

### Guest
- Log in
- Sign up **(changes to the system)** *(needs validation)*
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
    - Reserve book **(changes to the system)**
- View your profile
    - Edit personal data **(changes to the system)** *(needs validation)*
- View your loan
- View your reservations
    - Cancel reservation **(changes to the system)**
- View your fines

### Manager
- Log in
- Log out
- View "About" page
- View "Contacts" page
- View books catalog
    - Search books
- View book details
    - Edit book data **(changes to the system)** *(needs validation)*
    - Delete book **(changes to the system)**
    - Issue book by user id **(changes to the system)**
- View your profile
    - Edit personal data **(changes to the system)** *(needs validation)*
- View inventory book
    - Search copies of books
    - Write in copies of existing book **(changes to the system)**
    - Write in copies of new book **(changes to the system)** *(needs validation)*
    - Write off copies of books **(changes to the system)**
    - Delete copy of book **(changes to the system)**
- View recent reservations
    - Cancel reservation **(changes to the system)**
    - Confirm reservation **(changes to the system)**
- View list of members
    - Search members
    - View member profile
        - View member loan
            - Issue book by inv. № **(changes to the system)**
            - Return book **(changes to the system)**
        - View member reservations
            - Cancel reservation **(changes to the system)**
            - Loan reserved book **(changes to the system)**
        - View member fines
            - Set fine status as paid **(changes to the system)**

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
    - Set user role **(changes to the system)**
    - Soft delete user **(changes to the system)**
    - Restore user **(changes to the system)**
- View reports
    - Get report for the period