

/**
 * Celestial Body class for NBody
 * Modified from original Planet class
 * used at Princeton and Berkeley
 * @author ola
 *
 * If you add code here, add yourself as @author below
 *@author Malcolm Rodgers
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename){
			myXPos = xp;
			myYPos = yp;
			myXVel = xv;
			myYVel = yv;
			myMass = mass;
			myFileName = filename;

	}

	/**
	 *
	 * @return
	 */

	public double getX() {
		return myXPos;
	}

	/**
	 *
	 * @return
	 */
	public double getY() {
		return myYPos;
	}

	/**
	 * Accessor for the x-velocity
	 * @return the value of this object's x-velocity
	 */
	public double getXVel() {

		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		return myYVel;
	}

	/**
	 *
	 * @return
	 */
	public double getMass() {
		return myMass;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		double xDiff = this.myXPos - b.myXPos;
		double yDiff = this.myYPos - b.myYPos;
		double rSquared = Math.pow(xDiff, 2) + Math.pow(yDiff, 2);

		return Math.sqrt(rSquared);
	}

	public double calcForceExertedBy(CelestialBody b) {
		double gravTimesMasses = (6.67*1e-11) * (this.myMass * b.myMass);
		double distSquared = Math.pow(calcDistance(b), 2);
		double force = gravTimesMasses / distSquared;
		
		return force;
	}

	public double calcForceExertedByX(CelestialBody b) {
		return calcForceExertedBy(b) * (b.myXPos - myXPos) / calcDistance(b);
	}

	public double calcForceExertedByY(CelestialBody b) {
		return calcForceExertedBy(b) * (b.myYPos - myYPos) / calcDistance(b);
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double sum = 0.0;
		for (CelestialBody b : bodies) {
			if (!b.equals(this)) {
					sum += calcForceExertedByX(b);
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for (CelestialBody b : bodies) {
			if (!b.equals(this)) {
					sum += calcForceExertedByY(b);
			}
		}
		return sum;
	}

	public void update(double deltaT, 
			           double xforce, double yforce) {
		double aX = xforce / this.myMass;
		double aY = yforce / this.myMass;
		double nvx = this.myXVel + deltaT * aX;
		double nvy = this.myYVel + deltaT * aY;
		double nx = this.myXPos + deltaT * nvx;
		double ny = this.myYPos + deltaT * nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
