package rodeo.agile.impress.pos;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ZaikoLogicTest {

	private ZaikoDao dao = null;
	private ZaikoLogic zaikoLogic = null;
	
	@Before
	public void setup() {
		dao = mock(ZaikoDao.class);
		zaikoLogic = new ZaikoLogic(dao);
	}
	
	
	@Test
	public void testInsertMethodShouldBeCalledIfValuesAreValid() throws ClassNotFoundException, SQLException {		
		zaikoLogic.add(1, 5);
		
		verify(dao, times(1)).insert(1, 5);
	}
	
	@Test (expected=RuntimeException.class)
	public void testExceptionShouldBeThrownIfSuryoIsZero() throws ClassNotFoundException, SQLException {
		zaikoLogic.add(1, 0);
	}
	
	@Test (expected=RuntimeException.class)
	public void testExceptionShouldBeThrownIfSuryoIsMinus() throws ClassNotFoundException, SQLException {
		zaikoLogic.add(1, -1);
	}
	
	@Test (expected=RuntimeException.class)
	public void testExceptionShouldBeThrownIfNotExist() throws ClassNotFoundException, SQLException {
		zaikoLogic.add(-1, 1);
	}


}
