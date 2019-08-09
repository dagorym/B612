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
	
	Orbit(String e, double x, double y, double z, double vx, double vy, double vz){
		epoch=e;
		pos = new double[3];
		vel = new double[3];
		pos[0]=x;
		pos[1]=y;
		pos[2]=z;
		vel[0]=vx;
		vel[1]=vy;
		vel[2]=vz;
	}
	
	void propogateOrbit (double ts) {
		for (int i = 0; i < 3; i++) {
			pos[i] += ts*vel[i];
		}
	}
	
	String getDate() { return epoch; }
	double getX() { return pos[0]; }
	double getY() { return pos[1]; }
	double getZ() { return pos[2]; }
	double getVx() { return vel[0]; }
	double getVy() { return vel[1]; }
	double getVz() { return vel[2]; }
}
