/**
 * 
 */
package info.thomasstephens;

/**
 * @author Tom
 *
 */
public class Orbit {

	private String epoch;
	private	double pos[];
	private double vel[];
	
	Orbit(){
		epoch="2001-01-01T00:00:00";
		pos = new double[3];
		vel = new double[3];
		pos[0]=0;
		pos[1]=0;
		pos[2]=0;
		vel[0]=0;
		vel[1]=0;
		vel[2]=0;
	}
	
	String getDate() { return epoch; }
	double getX() { return pos[0]; }
	double getY() { return pos[1]; }
	double getZ() { return pos[2]; }
	double getVx() { return vel[0]; }
	double getVy() { return vel[1]; }
	double getVz() { return vel[2]; }
}
