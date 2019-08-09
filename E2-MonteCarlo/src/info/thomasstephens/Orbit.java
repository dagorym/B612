/**
 * 
 */
package info.thomasstephens;

/**
 * @brief Class to hold orbital data
 * 
 * This class stores the orbital data for a single orbit (epoch and state vector)
 * and has the logic to propagate the orbit over a single timestep.
 * 
 * @todo Just using arrays for the position and velocity data.  In production code,
 * this should probably be made to use some sort of tuple or vector object.  
 * Leaving it as is for this exercise
 * 
 * @date Created: Aug 9, 2019
 * @date Modified: Aug 9, 2019
 * @author Tom Stephens
 */
public class Orbit {

	private String epoch;
	private	double pos[];
	private double vel[];
	
	/**
	 * @brief Default Constructor
	 * 
	 * This constructor creates an empty orbit object setting the epoch to midnight on
	 * Jan 1, 2001 and the position and velocity values equal to zero
	 * 
	 * @date Created: Aug 9, 2019
	 * @date Modified: Aug 9, 2019
	 * @author Tom Stephens
	 */
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
	
	/**
	 * @brief Parameterized Constructor
	 * 
	 * This constructor creates an new orbit object setting the epoch and orbital elements
	 * to the values specified in the input parameters.  
	 * 
	 * All parameters are assumed to be in meters and meters per second.
	 * The format for the epoch is YYYY-MM-DDTHH:MM:SS.
	 * 
	 * @param epoch The epoch of the current orbit state
	 * @param x - Object X coordinate (m)
	 * @param y - Object Y coordinate (m)
	 * @param z - Object Z coordinate (m)
	 * @param vx - Object X velocity (m/s)
	 * @param vy - Object Y velocity (m/s)
	 * @param vz - Object Z velocity (m/s)
	 * 
	 * @date Created: Aug 9, 2019
	 * @date Modified: Aug 9, 2019
	 * @author Tom Stephens
	 */
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
	
	/**
	 * @brief Advance the orbit by the given time step
	 * 
	 * This method advances the orbit by the timestep specified in the input parameter
	 * which is a time given in seconds.
	 * 
	 * Currently this just updates the position by the velocities stored in the velocity
	 * vectors.  It makes no change to the velocities
	 * 
	 * @todo Method should update the epoch as well. However, we will ignore that for this exercise.
	 * 
	 * @param ts The time step (in seconds) by which to advance the orbital state vector
	 * 
	 * @date Created: Aug 9, 2019
	 * @date Modified: Aug 9, 2019
	 * @author Tom Stephens
	 */
	void propagateOrbit (double ts) {
		for (int i = 0; i < 3; i++) {
			pos[i] += ts*vel[i];
		}
	}
	
	/// Return the current epoch of the orbit
	String getDate() { return epoch; }
	/// Return the orbit's X coordinate
	double getX() { return pos[0]; }
	/// Return the orbit's Y coordinate
	double getY() { return pos[1]; }
	/// Return the orbit's Z coordinate
	double getZ() { return pos[2]; }
	/// Return the orbit's X velocity
	double getVx() { return vel[0]; }
	/// Return the orbit's Y velocity
	double getVy() { return vel[1]; }
	/// Return the orbit's Z velocity
	double getVz() { return vel[2]; }
}
