
	package ar.edu.itba.ss.core;

		/**
		* <p>Un autómata celular para bandadas de partículas
		* auto-propulsadas y puntuales.</p>
		*/

	public class CellularAutomaton {

		protected final double η;
		protected final double Δt;
		protected final MobileGenerator generator;

		protected CellularAutomaton(final Builder builder) {
			this.η = builder.η;
			this.Δt = builder.Δt;
			this.generator = builder.generator;
		}

		public static Builder from(final MobileGenerator generator) {
			return new Builder(generator);
		}

		public static final class Builder {

			protected final MobileGenerator generator;
			protected double η;
			protected double Δt;

			public Builder(final MobileGenerator generator) {
				this.generator = generator;
				this.η = 1.0;
				this.Δt = 1.0;
			}

			public CellularAutomaton build() {
				return new CellularAutomaton(this);
			}
		}
	}
