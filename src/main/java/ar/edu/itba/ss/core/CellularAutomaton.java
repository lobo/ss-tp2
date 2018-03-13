
	package ar.edu.itba.ss.core;

	import java.util.List;
	import java.util.Map;
	import java.util.function.BiConsumer;

	import ar.edu.itba.ss.core.interfaces.DistanceProcessor;
	import ar.edu.itba.ss.core.interfaces.Space;

		/**
		* <p>Un autómata celular para bandadas de partículas
		* auto-propulsadas y puntuales.</p>
		* <p>Esta clase es <b>inmutable</b>, ya que el estado se almacena
		* dentro del generador de partículas.</p>
		*/

	public class CellularAutomaton {

		protected final double η;
		protected final double Δt;
		protected final double interactionRadius;
		protected final MobileGenerator generator;
		protected final DistanceProcessor processor;
		protected final Space space;

		protected CellularAutomaton(final Builder builder) {
			this.η = builder.η;
			this.Δt = builder.Δt;
			this.interactionRadius = builder.interactionRadius;
			this.generator = builder.generator;
			this.processor = CellIndexMethod
					.by(OptimalGrid.AUTOMATIC)
					.build();
			this.space = SquareSpace.of(builder.L)
					.periodicBoundary(true)
					.build();
		}

		public static Builder from(final MobileGenerator generator) {
			return new Builder(generator);
		}

		public CellularAutomaton advance(final int step) {
			final Map<Particle, List<Particle>> nnl = NearNeighbourList
					.from(generator)
					.with(processor)
					.over(space)
					.interactionRadius(interactionRadius)
					.cluster();
			// Completar...
			return this;
		}

		public static final class Builder {

			protected double interactionRadius;
			protected double L;
			protected final MobileGenerator generator;
			protected double η;
			protected double Δt;
			protected BiConsumer<Integer, List<MobileParticle>> spy;

			public Builder(final MobileGenerator generator) {
				this.generator = generator;
				this.η = 1.0;
				this.Δt = 1.0;
			}

			public CellularAutomaton build() {
				return new CellularAutomaton(this);
			}

			public Builder interactionRadius(final double interactionRadius) {
				this.interactionRadius = interactionRadius;
				return this;
			}

			public Builder spaceOf(final double L) {
				this.L = L;
				return this;
			}

			public Builder step(final double Δt) {
				this.Δt = Δt;
				return this;
			}

			public Builder noise(final double η) {
				this.η = η;
				return this;
			}

			public Builder spy(
					final BiConsumer<Integer, List<MobileParticle>> spy) {
				this.spy = spy;
				return this;
			}
		}
	}
