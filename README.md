# Background: Library Management System

You are the IT department head of a large library. Recently, the library decided to launch a command-line library management system to facilitate book management and borrowing for administrators and users. As the project leader, you need to design and implement this command-line management system, making it feature basic book management capabilities and a simple user interaction interface.

# Business Requirements:

1. Users (User) and Administrators (Admin) can register and log in to the system.
2. Users can view the list of books, borrow multiple books, and return these books.
3. Administrators have the authority to view the list of books, add books, delete books, and modify book information. If a book is already in the system and the administrator tries to add it again, the system should merge the inventory rather than create a new one. Also, administrators cannot delete books that are currently being borrowed by users.

# Command-line Input/Output Examples:

```
register admin Alice password1
Admin Alice successfully registered.

register user Bob password2
User Bob successfully registered.

login Alice password1
Admin Alice successfully logged in.

add "Clean Code" "Robert C. Martin" 5
Book "Clean Code" by Robert C. Martin added successfully, inventory: 5.

listBooks
Book List:
1. Clean Code - Robert C. Martin - Inventory: 5

login Bob password2
User Bob successfully logged in.

search "Clean Code" " Robert C. Martin"
Clean Code - Robert C. Martin - Inventory: 5

borrow "Clean Code" " Robert C. Martin"
Book "Clean Code" successfully borrowed.

login Alice password1
Admin Alice successfully logged in.

delete "Clean Code" " Robert C. Martin"
Cannot delete book "Clean Code" because it is currently borrowed.

login Bob password2
User Bob successfully logged in.

return "Clean Code" " Robert C. Martin"
Book "Clean Code" successfully returned.

login Alice password1
Admin Alice successfully logged in.

add "Clean Code" "Robert C. Martin" 3
Book "Clean Code" inventory successfully updated, new inventory: 8.
```
# Examination Points:

1. OOP Awareness: Design classes and interfaces to implement the basic concepts and related features of books, users, and administrators.
2. Design Pattern Application: Adopt at least two design patterns (e.g., strategy pattern, factory pattern, etc.) in the solution to optimize the code structure.
3. Data Structure and Algorithm: Choose the appropriate data structure to store book and user information, and implement a simplified search algorithm for the search function.
4. Command-line Interface Design: Provide a simple and easy-to-use command-line interaction interface for users and administrators. The interface should support borrowing and returning multiple books by specifying the book title and handling special cases like merging inventory and restricting deletion.
5. Code Quality: Ensure clear code structure, readability, and appropriate comments.
6. Provide multiple scenario test cases.

# Stage2

1. Admin can know who borrow the book
```
check "Clean Code" "Robert C. Martin"
Alice
Car
```