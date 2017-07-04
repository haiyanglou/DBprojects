package main;

import java.io.RandomAccessFile;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;

public class Main {

	static String prompt = "sql> ";
	static String version = "© DB Engine Version v1.0";
	static String copyright = "© Apr 2017 Haiyang Lou";
	static boolean isExit = false;
	public static int pageSize = 512;
	static Scanner scanner = new Scanner(System.in).useDelimiter(";");

	public static void main(String[] args) {
		initial();

		splashScreen();

		String userCommand = "";

		while (!isExit) {
			System.out.print(prompt);
			userCommand = scanner.next().replace("\n", " ").replace("\r", "").trim().toLowerCase();
			Query.parseUserCommand(userCommand);
		}
		System.out.println("Thank you!");
	}

	public static void initial() {
		try {
			File dataDirectory = new File("data");
			if (dataDirectory.mkdir()) {
				System.out.println("Not existed database, initializing...");
				initialForm();
			} else {

				String[] oldTableFiles = dataDirectory.list();
				boolean checkTab = false;
				boolean checkCol = false;
				for (int i = 0; i < oldTableFiles.length; i++) {
					if (oldTableFiles[i].equals("dbbase_tables.tbl"))
						checkTab = true;
					if (oldTableFiles[i].equals("dbbase_columns.tbl"))
						checkCol = true;
				}

				if (!checkTab) {
					System.out.println("The dbbase_tables does not exit, initializing...");
					System.out.println();
					initialForm();
				}

				if (!checkCol) {
					System.out.println("The dbbase_columns table does not exit, initializing...");
					System.out.println();
					initialForm();
				}
			}
		} catch (SecurityException e) {
			System.out.println(e);
		}

	}

	public static void splashScreen() {
		System.out.println(drawLine("", 100));
		System.out.println("---Welcome to Database Engine---");
		System.out.println();
		System.out.println(version);
		System.out.println(copyright);
		System.out.println();
		System.out.println("Use \"help\" Command to get Instructions");
		System.out.println(drawLine("-", 100));
	}

	public static String drawLine(String str, int num) {
		String s = "";
		for (int i = 0; i < num; i++) {
			s += str;
		}
		return s;
	}

	public static void initialForm() {

		try {
			File dataDirectory = new File("data");
			dataDirectory.mkdir();
			String[] oldTableFiles;
			oldTableFiles = dataDirectory.list();
			for (int i = 0; i < oldTableFiles.length; i++) {
				File anOldFile = new File(dataDirectory, oldTableFiles[i]);
				anOldFile.delete();
			}
		} catch (SecurityException e) {
			System.out.println(e);
		}

		try {
			RandomAccessFile tablesCatalog = new RandomAccessFile("data/dbbase_tables.tbl", "rw");
			tablesCatalog.setLength(pageSize);
			tablesCatalog.seek(0);
			tablesCatalog.write(0x0D);
			tablesCatalog.writeByte(0x02);

			int size1 = 24;
			int size2 = 25;

			int offsetT = pageSize - size1;
			int offsetC = offsetT - size2;

			tablesCatalog.writeShort(offsetC);
			tablesCatalog.writeInt(0);
			tablesCatalog.writeInt(0);
			tablesCatalog.writeShort(offsetT);
			tablesCatalog.writeShort(offsetC);

			tablesCatalog.seek(offsetT);
			tablesCatalog.writeShort(20);
			tablesCatalog.writeInt(1);
			tablesCatalog.writeByte(1);
			tablesCatalog.writeByte(28);
			tablesCatalog.writeBytes("dbbase_tables");

			tablesCatalog.seek(offsetC);
			tablesCatalog.writeShort(21);
			tablesCatalog.writeInt(2);
			tablesCatalog.writeByte(1);
			tablesCatalog.writeByte(29);
			tablesCatalog.writeBytes("dbbase_columns");

			tablesCatalog.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			RandomAccessFile columnsCatalog = new RandomAccessFile("data/dbbase_columns.tbl", "rw");
			columnsCatalog.setLength(pageSize);
			columnsCatalog.seek(0);
			columnsCatalog.writeByte(0x0D);
			columnsCatalog.writeByte(0x08);

			int[] offset = new int[10];
			offset[0] = pageSize - 43;
			offset[1] = offset[0] - 47;
			offset[2] = offset[1] - 44;
			offset[3] = offset[2] - 48;
			offset[4] = offset[3] - 49;
			offset[5] = offset[4] - 47;
			offset[6] = offset[5] - 57;
			offset[7] = offset[6] - 49;

			columnsCatalog.writeShort(offset[7]);
			columnsCatalog.writeInt(0);
			columnsCatalog.writeInt(0);

			for (int i = 0; i < 8; i++)
				columnsCatalog.writeShort(offset[i]);

			columnsCatalog.seek(offset[0]);
			columnsCatalog.writeShort(33);
			columnsCatalog.writeInt(1);
			columnsCatalog.writeByte(5);
			columnsCatalog.writeByte(28);
			columnsCatalog.writeByte(17);
			columnsCatalog.writeByte(15);
			columnsCatalog.writeByte(4);
			columnsCatalog.writeByte(14);
			columnsCatalog.writeBytes("dbbase_tables");
			columnsCatalog.writeBytes("rowid");
			columnsCatalog.writeBytes("INT");
			columnsCatalog.writeByte(1);
			columnsCatalog.writeBytes("NO");

			columnsCatalog.seek(offset[1]);
			columnsCatalog.writeShort(39);
			columnsCatalog.writeInt(2);
			columnsCatalog.writeByte(5);
			columnsCatalog.writeByte(28);
			columnsCatalog.writeByte(22);
			columnsCatalog.writeByte(16);
			columnsCatalog.writeByte(4);
			columnsCatalog.writeByte(14);
			columnsCatalog.writeBytes("dbbase_tables");
			columnsCatalog.writeBytes("table_name");
			columnsCatalog.writeBytes("TEXT");
			columnsCatalog.writeByte(2);
			columnsCatalog.writeBytes("NO");

			columnsCatalog.seek(offset[2]);
			columnsCatalog.writeShort(34);
			columnsCatalog.writeInt(3);
			columnsCatalog.writeByte(5);
			columnsCatalog.writeByte(29);
			columnsCatalog.writeByte(17);
			columnsCatalog.writeByte(15);
			columnsCatalog.writeByte(4);
			columnsCatalog.writeByte(14);
			columnsCatalog.writeBytes("dbbase_columns");
			columnsCatalog.writeBytes("rowid");
			columnsCatalog.writeBytes("INT");
			columnsCatalog.writeByte(1);
			columnsCatalog.writeBytes("NO");

			columnsCatalog.seek(offset[3]);
			columnsCatalog.writeShort(40);
			columnsCatalog.writeInt(4);
			columnsCatalog.writeByte(5);
			columnsCatalog.writeByte(29);
			columnsCatalog.writeByte(22);
			columnsCatalog.writeByte(16);
			columnsCatalog.writeByte(4);
			columnsCatalog.writeByte(14);
			columnsCatalog.writeBytes("dbbase_columns");
			columnsCatalog.writeBytes("table_name");
			columnsCatalog.writeBytes("TEXT");
			columnsCatalog.writeByte(2);
			columnsCatalog.writeBytes("NO");

			columnsCatalog.seek(offset[4]);
			columnsCatalog.writeShort(41);
			columnsCatalog.writeInt(5);
			columnsCatalog.writeByte(5);
			columnsCatalog.writeByte(29);
			columnsCatalog.writeByte(23);
			columnsCatalog.writeByte(16);
			columnsCatalog.writeByte(4);
			columnsCatalog.writeByte(14);
			columnsCatalog.writeBytes("dbbase_columns");
			columnsCatalog.writeBytes("column_name");
			columnsCatalog.writeBytes("TEXT");
			columnsCatalog.writeByte(3);
			columnsCatalog.writeBytes("NO");

			columnsCatalog.seek(offset[5]);
			columnsCatalog.writeShort(39);
			columnsCatalog.writeInt(6);
			columnsCatalog.writeByte(5);
			columnsCatalog.writeByte(29);
			columnsCatalog.writeByte(21);
			columnsCatalog.writeByte(16);
			columnsCatalog.writeByte(4);
			columnsCatalog.writeByte(14);
			columnsCatalog.writeBytes("dbbase_columns");
			columnsCatalog.writeBytes("data_type");
			columnsCatalog.writeBytes("TEXT");
			columnsCatalog.writeByte(4);
			columnsCatalog.writeBytes("NO");

			columnsCatalog.seek(offset[6]);
			columnsCatalog.writeShort(49);
			columnsCatalog.writeInt(7);
			columnsCatalog.writeByte(5);
			columnsCatalog.writeByte(29);
			columnsCatalog.writeByte(28);
			columnsCatalog.writeByte(19);
			columnsCatalog.writeByte(4);
			columnsCatalog.writeByte(14);
			columnsCatalog.writeBytes("dbbase_columns");
			columnsCatalog.writeBytes("ordinal_position");
			columnsCatalog.writeBytes("TINYINT");
			columnsCatalog.writeByte(5);
			columnsCatalog.writeBytes("NO");

			columnsCatalog.seek(offset[7]);
			columnsCatalog.writeShort(41);
			columnsCatalog.writeInt(8);
			columnsCatalog.writeByte(5);
			columnsCatalog.writeByte(29);
			columnsCatalog.writeByte(23);
			columnsCatalog.writeByte(16);
			columnsCatalog.writeByte(4);
			columnsCatalog.writeByte(14);
			columnsCatalog.writeBytes("dbbase_columns");
			columnsCatalog.writeBytes("is_nullable");
			columnsCatalog.writeBytes("TEXT");
			columnsCatalog.writeByte(6);
			columnsCatalog.writeBytes("NO");

			columnsCatalog.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static boolean tableExists(String tablename) {
		tablename = tablename + ".tbl";

		try {
			File dataDirectory = new File("data");
			String[] oldTableFiles;
			oldTableFiles = dataDirectory.list();
			for (int i = 0; i < oldTableFiles.length; i++) {
				if (oldTableFiles[i].equals(tablename))
					return true;
			}
		} catch (SecurityException se) {
			System.out.println("Unable to create data container directory");
			System.out.println(se);
		}

		return false;
	}

}