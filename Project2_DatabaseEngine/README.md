davisql> help;
++++++++++++++++++++++++++++++++++
SUPPORTED COMMANDS
(COMMANDS ARE CASE INSENSITIVE)

SHOW TABLES;                                                                          Display all the tables

CREATE TABLE table_name (column_name1 datatype1, column_name2 datatype2,...);         Create new table
example: CREATE TABLE tbl (id int [not null], name text [not null], age int);

DROP TABLE table_name;                                                                Remove table data and its schema

INSERT INTO TABLE table_name VALUES (value1,value2,...);                              Insert new record into the table (No BLANK SPACE between values)
example: INSERT INTO TABLE tbl VALUES (11,David,23);

DELETE FROM TABLE table_name WHERE column_name = value;                               Delete record from the table whose identifier is given value
example: DELETE FROM TABLE tbl WHERE ID = 11;

UPDATE table_name SET column_name = value WHERE condition;                            Modifies records in the table
example: UPDATE tbl SET name = Jonny WHERE id=11;

SELECT * FROM table_name;                                                             Display all records in the table

SELECT * FROM table_name WHERE column_name operator value;                            Display records in the table which fulfill the given condition

VERSION;                                                    Show version

HELP;                                                       Show help information

EXIT;                                                       Exit

++++++++++++++++++++++++++++++++++

