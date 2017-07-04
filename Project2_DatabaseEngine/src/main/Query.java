package main;

import java.util.ArrayList;
import java.util.Arrays;

public class Query {
	public static String[] checkEquation(String equ) {
		String comparator[] = new String[3];
		String temp[] = new String[2];
		if (equ.contains("=")) {
			temp = equ.split("=");
			comparator[0] = temp[0].trim();
			comparator[1] = "=";
			comparator[2] = temp[1].trim();
		}

		if (equ.contains("<")) {
			temp = equ.split("<");
			comparator[0] = temp[0].trim();
			comparator[1] = "<";
			comparator[2] = temp[1].trim();
		}

		if (equ.contains(">")) {
			temp = equ.split(">");
			comparator[0] = temp[0].trim();
			comparator[1] = ">";
			comparator[2] = temp[1].trim();
		}

		if (equ.contains("<=")) {
			temp = equ.split("<=");
			comparator[0] = temp[0].trim();
			comparator[1] = "<=";
			comparator[2] = temp[1].trim();
		}

		if (equ.contains(">=")) {
			temp = equ.split(">=");
			comparator[0] = temp[0].trim();
			comparator[1] = ">=";
			comparator[2] = temp[1].trim();
		}

		return comparator;
	}

	public static void parseUserCommand(String userCommand) {


		ArrayList<String> commandpositions = new ArrayList<String>(Arrays.asList(userCommand.split(" ")));

		switch (commandpositions.get(0)) {
		
		case "help":
			help();
			break;

		case "show":
			showTables();
			break;

		case "create":
			createCommand(userCommand);
			break;
			
		case "drop":
			dropTable(userCommand);
			break;

		case "insert":
			insertCommand(userCommand);
			break;

		case "delete":
			deleteCommand(userCommand);
			break;

		case "update":
			updateCommand(userCommand);
			break;

		case "select":
			queryCommand(userCommand);
			break;

		case "version":
			System.out.println("DB Version " + Main.version);
			System.out.println(Main.copyright);
			break;

		case "exit":
			Main.isExit = true;
			break;

		case "quit":
			Main.isExit = true;
			break;

		default:
			System.out.println("Command Syntax Error: \"" + userCommand + "\"");
			System.out.println();
			break;
		}
	}

	public static void help() {
		System.out.println(Main.drawLine("+", 100));
		System.out.println("SUPPORTED COMMANDS");
		System.out.println("(COMMANDS ARE CASE INSENSITIVE)");
		System.out.println();
		System.out.println("SHOW TABLES;                                                                          Display all the tables\n");
		System.out.println("CREATE TABLE table_name (column_name1 datatype1, column_name2 datatype2,...);         Create new table");
		System.out.println(
				"example: CREATE TABLE tbl (id int [not null], name text [not null], age int);\n");     
		System.out.println(
				"DROP TABLE table_name;                                                                Remove table data and its schema\n");
		System.out.println(
				"INSERT INTO TABLE table_name VALUES (value1,value2,...);                              Insert new record into the table (No BLANK SPACE between values)");
		System.out.println(
				"example: INSERT INTO TABLE tbl VALUES (11,David,23);\n");     
		System.out.println(
				"DELETE FROM TABLE table_name WHERE column_name = value;                               Delete record from the table whose identifier is given value");
		System.out.println(
				"example: DELETE FROM TABLE tbl WHERE ID = 11;\n");     
		System.out.println(
				"UPDATE table_name SET column_name = value WHERE condition;                            Modifies records in the table");
		System.out.println(
				"example: UPDATE tbl SET name = Jonny WHERE id=11;\n");     
		System.out.println(
				"SELECT * FROM table_name;                                                             Display all records in the table\n");
		System.out.println(
				"SELECT * FROM table_name WHERE column_name operator value;                            Display records in the table which fulfill the given condition\n");
		System.out.println("VERSION;                                                                              Show version\n");
		System.out.println("HELP;                                                                                 Show help information\n");
		System.out.println("EXIT;                                                                                 Exit\n");
		System.out.println(Main.drawLine("+", 100));
	}

	public static void showTables() {
		System.out.println("Processing Command:\"show tables\"");

		String table = "db_tables";
		String[] cols = { "table_name" };
		String[] cmptr = new String[0];
		Table.select(table, cols, cmptr);
	}

	public static void createCommand(String createString) {
		System.out.println("Processing Command:\"" + createString + "\"");

		String[] positions = createString.split(" ");
		String tableName = positions[2];
		String[] temp = createString.split(tableName);
		String cols = temp[1].trim();
		String[] create_cols = cols.substring(1, cols.length() - 1).split(",");

		for (int i = 0; i < create_cols.length; i++)
			create_cols[i] = create_cols[i].trim();

		if (Main.tableExists(tableName)) {
			System.out.println("Table " + tableName + " already exists.");
		} else {
			Table.createTable(tableName, create_cols);
		}

	}

	public static void dropTable(String dropTableString) {
		System.out.println("Processing Command:\"" + dropTableString + "\"");

		String[] positions = dropTableString.split(" ");
		String tableName = positions[2];
		if (!Main.tableExists(tableName)) {
			System.out.println("Table " + tableName + " does not exist.");
		} else {
			Table.drop(tableName);
		}
	}
	
	public static void insertCommand(String insertString) {
		System.out.println("Processing Command:\"" + insertString + "\"");

		String[] positions = insertString.split(" ");
		String table = positions[3];
		String[] temp = insertString.split("values");
		String temporary = temp[1].trim();
		String[] insert_vals = temporary.substring(1, temporary.length() - 1).split(",");
		for (int i = 0; i < insert_vals.length; i++)
			insert_vals[i] = insert_vals[i].trim();
		if (!Main.tableExists(table)) {
			System.out.println("Table " + table + " does not exist.");
		} else {
			Table.insertInto(table, insert_vals);
		}

	}

	public static void deleteCommand(String deleteString) {
		System.out.println("Processing Command:\"" + deleteString + "\"");

		String[] positions = deleteString.split(" ");
		String table = positions[3];
		String[] temp = deleteString.split("where");
		String cmpTemp = temp[1];
		String[] cmp = checkEquation(cmpTemp);
		if (!Main.tableExists(table)) {
			System.out.println("Table " + table + " does not exist.");
		} else {
			Table.delete(table, cmp);
		}

	}

	public static void updateCommand(String updateString) {
		System.out.println("Processing Command:\"" + updateString + "\"");

		String[] positions = updateString.split(" ");
		String table = positions[1];
		String[] temp1 = updateString.split("set");
		String[] temp2 = temp1[1].split("where");
		String cmpTemp = temp2[1];
		String setTemp = temp2[0];
		String[] cmp = checkEquation(cmpTemp);
		String[] set = checkEquation(setTemp);
		if (!Main.tableExists(table)) {
			System.out.println("Table " + table + " does not exist.");
		} else {
			Table.update(table, cmp, set);
		}

	}

	public static void queryCommand(String queryString) {
		System.out.println("Processing Command:\"" + queryString + "\"");

		String[] cmp;
		String[] column;
		String[] temp = queryString.split("where");
		if (temp.length > 1) {
			String tmp = temp[1].trim();
			cmp = checkEquation(tmp);
		} else {
			cmp = new String[0];
		}
		String[] select = temp[0].split("from");
		String tableName = select[1].trim();
		String cols = select[0].replace("select", "").trim();
		if (cols.contains("*")) {
			column = new String[1];
			column[0] = "*";
		} else {
			column = cols.split(",");
			for (int i = 0; i < column.length; i++)
				column[i] = column[i].trim();
		}

		if (!Main.tableExists(tableName)) {
			System.out.println("Table " + tableName + " does not exist.");
		} else {
			Table.select(tableName, column, cmp);
		}
	}

}
