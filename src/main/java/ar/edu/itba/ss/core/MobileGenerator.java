
	package ar.edu.itba.ss.core;

	import java.util.List;
	import java.util.function.Consumer;
	import java.util.stream.Stream;

	import static java.util.stream.Collectors.toList;

	import ar.edu.itba.ss.core.interfaces.ParticleGenerator;

		/**
		* <p>Basado en un generador uniforme, permite generar partículas
		* móviles, es decir, con velocidad y dirección. Adicionalmente opera
		* como base de datos ya que es esperable que una partícula móvil
		* modifique su trayectoria y velocidad a lo largo de una
		* simulación.</p>
		*/

	public class MobileGenerator implements ParticleGenerator {

		protected final int size;
		protected final double maxLength;
		protected final double maxRadius;
		protected final double speed;
		protected final Consumer<MobileParticle> consumer;
		protected List<MobileParticle> particles;

		protected MobileGenerator(final Builder builder) {
			this.size = builder.size;
			this.maxLength = builder.maxLength;
			this.maxRadius = builder.maxRadius;
			this.consumer = builder.consumer;
			this.speed = builder.speed;
			this.particles = Stream.generate(() -> {
				final double vx = speed * (2 * Math.random() - 1.0);
				final double vy = Math.sqrt(speed * speed - vx * vx);
				return new MobileParticle(
						Math.random() * maxLength,
						Math.random() * maxLength,
						Math.random() * maxRadius,
						vx, Math.random() < 0.5? -vy : vy);
			}).limit(size).peek(consumer).collect(toList());
		}

		public static Builder of(final int size) {
			return new Builder(size);
		}

		@Override
		public Stream<MobileParticle> generate() {
			return particles.stream();
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public double maxRadius() {
			return maxRadius;
		}

		public double getSpeed() {
			return speed;
		}

		public MobileGenerator advance(
				final List<MobileParticle> particles) {
			this.particles = particles;
			return this;
		}

		public static class Builder {

			protected final int size;
			protected double maxLength;
			protected double maxRadius;
			protected double speed;
			protected Consumer<MobileParticle> consumer;

			public Builder(final int size) {
				this.size = size;
				this.maxRadius = 0;
				this.maxLength = 1.0;
				this.consumer = p -> {};
				this.speed = 0.05;
			}

			public Builder spy(final Consumer<MobileParticle> consumer) {
				this.consumer = consumer;
				return this;
			}

			public Builder maxRadius(final double radius) {
				this.maxRadius = radius;
				return this;
			}

			public Builder over(final double maxLength) {
				this.maxLength = maxLength;
				return this;
			}

			public Builder speed(final double speed) {
				this.speed = speed;
				return this;
			}

			public MobileGenerator build() {
				return new MobileGenerator(this);
			}
		}
	}
