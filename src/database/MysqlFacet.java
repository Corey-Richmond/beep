package database;

import java.util.ArrayList;

public interface MysqlFacet {

	// INSERT into <table> (<column>) values('<contents>')
	public boolean insert(ArrayList<String> contents, String table, String column);
	
	// SELECT <what> FROM <table>
	public boolean get(String what, String table, ArrayList<String> contents);
}
