
	package ar.edu.itba.ss.tp2;

	import java.util.List;
	import java.util.stream.IntStream;

	import static java.util.stream.Collectors.toList;

	import ar.edu.itba.ss.tp2.core.CellularAutomaton;
	import ar.edu.itba.ss.tp2.core.MobileGenerator;
	import ar.edu.itba.ss.tp2.core.MobileParticle;

	public class Benchmarking {

		public static void main(final String [] arguments) {
			KineticOverNoise();
			KineticOverDensity();
		}

		public static void KineticOverDensity() {

			final int cycles = 100;
			final double Δt = 1.0;
			final double Rc = 1.0;
			final double V = 0.03;

			final int ΔN = 80;
			final double L = 20.0;
			final double [] ηs = {0.1, 2.0, 5.0};

			final int replicas = 10;

			for (final double η : ηs) { // Una curva para cada amplitud...
				for (int N = 0; N <= 4000; N += ΔN) { // Implica un paso de 0.2 en la densidad...

					final MobileGenerator generator = MobileGenerator.of(N)
							.maxRadius(0)
							.speed(V)
							.over(L)
							.build();

					final List<MobileParticle> particles = generator
							.generate()
							.collect(toList());

					System.out.print(N/(L*L));
					for (int replica = 0; replica < replicas; ++replica) {

						generator.advance(particles);

						final CellularAutomaton automaton = CellularAutomaton.from(generator)
								.interactionRadius(Rc)
								.spaceOf(L)
								.step(Δt)
								.noise(η)
								.build();

						IntStream.range(0, cycles)
							.forEachOrdered(automaton::advance);

						System.out.print(" " + automaton.getOrder());
					}
					System.out.println("");
				}
				System.out.println("End simulation with η = " + η + ".\n");
			}
		}

		public static void KineticOverNoise() {

			final int cycles = 100;
			final double Δt = 1.0;
			final double Rc = 1.0;
			final double V = 0.03;

			final int [] N = {40, 100, 400, 4000, 10000};
			final double [] L = {3.16, 5, 10, 31.6, 50};
			final double Δη = 0.2;

			final int replicas = 10;

			for (int k = 0; k < N.length; ++k) {

				final MobileGenerator generator = MobileGenerator.of(N[k])
						.maxRadius(0)
						.speed(V)
						.over(L[k])
						.build();

				final List<MobileParticle> particles = generator
						.generate()
						.collect(toList());

				for (double η = 0.0; η < 6.2 ; η += Δη) { // De 0 a 6...
					System.out.print(η);
					for (int replica = 0; replica < replicas; ++replica) {

						generator.advance(particles);

						final CellularAutomaton automaton = CellularAutomaton.from(generator)
								.interactionRadius(Rc)
								.spaceOf(L[k])
								.step(Δt)
								.noise(η)
								.build();

						IntStream.range(0, cycles)
							.forEachOrdered(automaton::advance);

						System.out.print(" " + automaton.getOrder());
					}
					System.out.println("");
				}
				System.out.println("End simulation with N = " + N[k] + ".\n");
			}
		}
	}
