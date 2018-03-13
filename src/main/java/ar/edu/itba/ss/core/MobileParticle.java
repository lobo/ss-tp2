
	package ar.edu.itba.ss.core;

		/**
		* <p>Extensión de una partícula simple. En este caso, la partícula
		* presenta un vector de velocidad bidimensional y, por lo tanto, es
		* móvil.</p>
		* <p>Esta clase es <b>inmutable</b>.</p>
		*/

	public class MobileParticle extends Particle {

		protected final double vx;
		protected final double vy;

		public MobileParticle(
				final double x, final double y, final double radius,
				final double vx, final double vy) {
			super(x, y, radius);
			this.vx = vx;
			this.vy = vy;
		}

		public double getVx() {
			return vx;
		}

		public double getVy() {
			return vy;
		}

		public double getθ() {
			return Math.atan2(vy, vx);
		}

		public double getDegrees() {
			return Math.toDegrees(getθ());
		}

		public double getSpeed() {
			return Math.hypot(vx, vy);
		}

		public MobileParticle move(final double Δt) {
			return new MobileParticle(
					x + vx * Δt, y + vy * Δt, radius, vx, vy);
		}

		public MobileParticle rotateTo(
				final double angle, final double speed) {
			return new MobileParticle(
					x, y, radius,
					speed * Math.cos(angle),
					speed * Math.sin(angle));
		}

		public MobileParticle rotateTo(final double angle) {
			return rotateTo(angle, getSpeed());
		}

		@Override
		public String toString() {
			return "(x:" + x + ", y:" +
					y + ", r:" +
					radius + ", vx:" +
					vx + ", vy:" +
					vy + ", θ°:" +
					getDegrees() + ")";
		}
	}
