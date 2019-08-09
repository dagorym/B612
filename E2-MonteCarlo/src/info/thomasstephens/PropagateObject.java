/**
 * 
 */
package info.thomasstephens;


/**
 * @author Tom
 *
 */
public class PropagateObject {
	
	/**
	 * @brief Apply initial orbit perturbation based on error matrix
	 * 
	 * This method perturbs the passed in value based on the passed in error.
	 * Currently, it simply picks a random value between +error and -error and
	 * adds that to the value which it then returns
	 * 
	 * @todo Change the selection to pick from a range assuming that the passed
	 * in error is the std deviation of a normal distribution
	 * 
	 * @param value The value to be modified
	 * @param error The error value used to determining the scale of the perturbation
	 * @return The updated value modified by the error
	 * 
	 * @date Created: Aug 9, 2019
	 * @date Modified: Aug 9, 2019
	 * @author Tom Stephens
	 */
	static double getPerturbation(double value, double error) {
		double var = Math.random()*2-1.0;
		return value * var;
	}

	/**
	 * @brief Get an perturbed orbit based on the error matrix
	 * 
	 * This method returns a new orbit that has been slightly perturbed from
	 * the original based on the supplied error matrix.  Each element of the
	 * orbit is modified using the getPerturbation() method.
	 * 
	 * Currently this method just used the diagonal elements of the error matrix.
	 * 
	 * @param o The original orbit
	 * @param errorMatrix The full error matrix
	 * 
	 * @return A new Orbit object with values perturbed by the values in the error matrix
	 * 
	 * @date Created: Aug 9, 2019
	 * @date Modified: Aug 9, 2019
	 * @author Tom Stephens
	 */
	static Orbit getPerturbedOrbit(Orbit o, double errorMatrix[][]) {
		double x = getPerturbation(o.getX(),errorMatrix[0][0]);
		double y = getPerturbation(o.getY(),errorMatrix[1][1]);
		double z = getPerturbation(o.getZ(),errorMatrix[2][2]);
		double vx = getPerturbation(o.getVx(),errorMatrix[3][3]);
		double vy = getPerturbation(o.getVy(),errorMatrix[4][4]);
		double vz = getPerturbation(o.getVz(),errorMatrix[5][5]);
		
		return new Orbit(o.getDate(),x,y,z,vx,vy,vz);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the orbit propagator.");
		// Open and read the input file
//		try(FileInputStream fin = new FileInputStream(args[0])) {
//			String line = "";
//			do {
//				// @TODO Need to figure out how to read from files
//			} while (line != "STOP");
//		} catch(FileNotFoundException e) {
//			System.out.println("File Not Found.");
//		} catch(IOException e) {
//			System.out.println("An I/O Error Occurred");
//		}
		
		// for now we'll just preset the initial conditions instead of reading it from a file
//		Orbit origOrbit = new Orbit("2019-09-10T12:00:00",149e9,1e4,2e4,10,30e3,-5);
		Orbit origOrbit = new Orbit("2019-09-10T12:00:00",149e9,1e4,2e4,1,1,1);
		int nSims = 1000;  // set number of simulations to run
		double errorMatrix[][] = new double[6][6];
		for (int i = 0; i<6; i++) {
			for (int j = 0; j<6; j++) {
				if (i == j) {
					errorMatrix[i][j] = 1;
				} else {
					errorMatrix[i][j] = 0;
				}
			}
		}

		
		// For the purposes of this exercise, we're setting the size of the time steps
		// to ten minutes and running the simulation for 10 years.  Normally we'd read
		// those values in from an external sources (or the propagator would handle the
		// time step at least).
		double stepSize = 600; // units are seconds
		int nSteps = 525600;  // number of 10 minute steps in 10 years (ignoring leap days)
		
		// Some arrays to store statistics information
		double SMAList[] = new double[nSims];
		double eList[] = new double[nSims];
		
		// Loop over the number of simulations
		// We'll also sum up the semi-major axes and eccentricities as we go
		double meanSMA = 0;
		double meanE = 0;
		System.out.println("Running "+nSims+" simulations");
		for (int sim = 0; sim < nSims; sim ++) {
			// Set initial conditions for this orbit selecting variation from error matrix values
			Orbit o = getPerturbedOrbit(origOrbit,errorMatrix);
//			Orbit o = new Orbit("2019-09-10T12:00:00",149e9,1e4,2e4,10,30e3,-5);
			// run the orbit forward for the full duration
			for (int time = 0; time < nSteps; time++) {
				o.propagateOrbit(stepSize);
			}
			// compute and store the semi-major axis and eccentricity
			SMAList[sim]=o.getSemiMajorAxis();
			meanSMA += SMAList[sim];
			eList[sim]=o.getEccentricity();
			meanE += eList[sim];
//			System.out.println(SMAList[sim]+ "  " + eList[sim]);
		}
		
		// generate the statistics
		// First the means, we've already tallied them above so just need to divide by the number of sims
		meanSMA /= nSims;
		meanE /= nSims;
		// next the standard deviations 
		// @TODO check calculation as I think I'm not remembering the formula correctly
		double stdSMA = 0;
		double stdE = 0;
		for (int i = 0; i<nSims; i++) {
			stdSMA += (SMAList[i] - meanSMA) * (SMAList[i] - meanSMA);
			stdE += (eList[i] - meanE) * (eList[i] - meanE);
		}
//		System.out.println(stdSMA);
//		System.out.println(stdE);
		
		stdSMA = Math.sqrt(stdSMA/nSims);
		stdE = Math.sqrt(stdE/nSims);
		
		System.out.println("Semi-major Axis: " + meanSMA + " +/- " + stdSMA);
		System.out.println("Eccentricity: " + meanE + " +/- " + stdE);
		
		
		
	}

}
