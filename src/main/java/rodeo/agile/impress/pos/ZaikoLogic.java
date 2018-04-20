package rodeo.agile.impress.pos;

import java.sql.SQLException;

public class ZaikoLogic {
	
	private ZaikoDao dao;

	public ZaikoLogic(ZaikoDao dao) {
		this.dao = dao;
	}
		
	public void add(int id, int suryo) throws ClassNotFoundException, SQLException {
		if (suryo <= 0) {
			throw new RuntimeException("Failed to add zaiko which suryo lesser equal 0.");
		}
		dao.insert(id, suryo);
	}

}
