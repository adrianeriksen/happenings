# ![Happenings Logo](media/logo.png)

> Create and discover events with the people that matters.

A half-hearted attempt to clone everyone's favorite event application. The
project is solely made for educational purposes, demonstrating an example
application using Ktor and React.

The application will probably never be production-ready, thus, the code is run
at your own risk.

## Setup
Before you can start the project. you need to configure a couple of environment
variables that point the application to your MariaDB instance.

- HAPPENINGS_JDBC_URL
- HAPPENINGS_USERNAME
- HAPPENINGS_PASSWORD

### Sample data
Sample data is located in the file `src/main/resources/sample_data.sql` and can
be loaded with the following command:
```
$ mysql -u username -p happenings_development < sample_data.sql```