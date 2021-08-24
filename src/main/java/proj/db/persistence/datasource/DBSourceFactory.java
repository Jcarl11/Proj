package proj.db.persistence.datasource;

public class DBSourceFactory {

	private static DBSourceFactory dbSourceFactory = null;

	private DBSourceFactory() {}

	public static DBSourceFactory createInstance() {
		if (dbSourceFactory == null) {
			return new DBSourceFactory();
		}
		return dbSourceFactory;
	}

}
