
	package ar.edu.itba.ss.tp2.core;

	import java.util.List;
	import java.util.Map;
	import java.util.function.BiConsumer;

	import static java.util.stream.Collectors.toList;

	import ar.edu.itba.ss.core.CellIndexMethod;
	import ar.edu.itba.ss.core.NearNeighbourList;
	import ar.edu.itba.ss.core.OptimalGrid;
	import ar.edu.itba.ss.core.Particle;
	import ar.edu.itba.ss.core.SquareSpace;
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
		protected final BiConsumer<Integer, List<MobileParticle>> spy;

		protected CellularAutomaton(final Builder builder) {
			this.spy = builder.spy;
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
			// Computar vecinos de cada partícula:
			final Map<Particle, List<Particle>> nnl = NearNeighbourList
					.from(generator)
					.with(processor)
					.over(space)
					.interactionRadius(interactionRadius)
					.cluster();
			// Actualizar de forma sincrónica:
			final double L = space.dimensions().get(0);
			final List<MobileParticle> particles = nnl.entrySet().stream()
					.map(e -> {
						final MobileParticle p = (MobileParticle) e.getKey();
						final List<Particle> neighbours = e.getValue();
						neighbours.add(p);
						return p.move(Δt, L).rotateTo(
								Θ(neighbours),
								generator.getSpeed());
					})
					.collect(toList());
			// Almacenar en el generador y externalizar:
			generator.advance(particles);
			spy.accept(step, particles);
			return this;
		}

		public double getOrder() {
			final double speed = generator.getSpeed();
			final double N = generator.generate().count();
			final Pair<Double> sum = generator.generate()
					.map(p -> new Pair<Double>(p.getVx(), p.getVy()))
					.reduce(
						new Pair<Double>(0.0, 0.0),
						(p1, p2) -> p1.combine(p2, (v1, v2) -> v1 + v2));
			return Math.hypot(sum.getLeft(), sum.getRight()) / (speed * N);
		}

		private double Θ(final List<Particle> neighbours) {
			final Pair<Double> sum = neighbours.stream()
					.map(p -> (MobileParticle) p)
					.map(MobileParticle::getθ)
					.map(θ -> new Pair<Double>(Math.sin(θ), Math.cos(θ)))
					.reduce(
						new Pair<Double>(0.0, 0.0),
						(p1, p2) -> p1.combine(p2, (v1, v2) -> v1 + v2));
			return Math.atan2(sum.left, sum.right) + Δθ();
		}

		private double Δθ() {
			return (Math.random() - 0.5) * η;
		}

		public static final class Builder {

			protected final MobileGenerator generator;
			protected double interactionRadius;
			protected double L;
			protected double η;
			protected double Δt;
			protected BiConsumer<Integer, List<MobileParticle>> spy;

			public Builder(final MobileGenerator generator) {
				this.generator = generator;
				this.L = 1.0;
				this.η = 1.0;
				this.Δt = 1.0;
				this.interactionRadius = 1.0;
				this.spy = (k, ps) -> {};
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
