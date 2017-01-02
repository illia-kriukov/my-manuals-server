# my-manuals-server
Back-end part of the "My Manuals" application by Team A. Autumn 2016.

# How-to run the project
1. Run MySQL server;
2. Make sure that 'root' user has no password;
3. Clone 'my-manuals-server' repository;
4. Navigate to the root folder;
5. Run .sh (if you use Unix based OS) or .bat (for Windows OS) file.

Note: Before running set up you MySQL for storing manual files. Add next settings to my.cnf file into [mysqld] section:

```
max_allowed_packet = 100M
innodb_log_file_size = 200M
innodb_log_buffer_size = 50M
```

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/166657a67b6e20019c61)
