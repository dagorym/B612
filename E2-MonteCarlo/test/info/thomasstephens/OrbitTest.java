/**
 * 
 */
package info.thomasstephens;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Tom
 *
 */
class OrbitTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDefaultConstruction() {
		Orbit o = new Orbit();
		assert(o.getDate() == "2001-01-01T00:00:00");
		assert(o.getX() == 0);
		assert(o.getY() == 0);
		assert(o.getZ() == 0);
		assert(o.getVx() == 0);
		assert(o.getVy() == 0);
		assert(o.getVz() == 0);
	}

	@Test
	void testParametreConstuction() {		
		Orbit o = new Orbit("2019-09-10T12:00:00",149e9,1e4,2e4,10,30e3,-5);
		assert(o.getDate() == "2019-09-10T12:00:00");
		assert(o.getX() == 1.49e11);
		assert(o.getY() == 1e4);
		assert(o.getZ() == 2e4);
		assert(o.getVx() == 10);
		assert(o.getVy() == 3e4);
		assert(o.getVz() == -5);	
	}
	
	@Test
	void testPropogateOrbit1() {
		Orbit o = new Orbit("2019-09-10T12:00:00",149e9,1e4,2e4,10,30e3,-5);
		o.propogateOrbit(1);
		assert(o.getX() == 1.49e11+10);
		assert(o.getY() == 1e4+3e4);
		assert(o.getZ() == 2e4-5);
		assert(o.getVx() == 10);
		assert(o.getVy() == 3e4);
		assert(o.getVz() == -5);	

	}
}
