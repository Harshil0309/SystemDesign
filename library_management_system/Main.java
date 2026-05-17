public class Main {

    public static void main(String[] args) {

        AppLogger.log("Library Management System Started");

        // Repositories

        UserRepository userRepository =
                new UserRepository();

        BookRepository bookRepository =
                new BookRepository();

        IssueRecordRepository issueRecordRepository =
                new IssueRecordRepository();

        // Services

        UserService userService =
                new UserService(userRepository);

        BookService bookService =
                new BookService(bookRepository);

        IssueRecordService issueRecordService =
                new IssueRecordService(
                        issueRecordRepository,
                        bookRepository
                );

        // Create Users

        User user1 = userService.createUser("Harshil","9999999999");

        AppLogger.log(
                "User created: " +
                user1.getName()
        );

        // Create Books

        Book book1 =bookService.addBook("Atomic Habits");

        Book book2 = bookService.addBook("Clean Code");

        AppLogger.log("Books added");

        // Issue Book

        IssueRecord record =
                issueRecordService.issueBook(
                        user1.getId(),
                        book1.getId(),
                        "17-05-2026"
                );

        if (record != null) {

            AppLogger.log(
                    "Book issued successfully"
            );

            System.out.println(
                    "Issue Record ID: " +
                    record.getId()
            );

            System.out.println(
                    "Book ID: " +
                    record.getBookId()
            );

            System.out.println(
                    "User ID: " +
                    record.getUserId()
            );

        } else {

            AppLogger.log(
                    "Book issue failed"
            );

            System.out.println(
                    "Book is not available"
            );
        }

        // Return Book

        issueRecordService.returnBook(
                record.getId(),
                "20-05-2026"
        );

        AppLogger.log(
                "Book returned successfully"
        );

        System.out.println(
                "Book returned successfully"
        );

        AppLogger.log(
                "Library Management System Finished"
        );
    }
}