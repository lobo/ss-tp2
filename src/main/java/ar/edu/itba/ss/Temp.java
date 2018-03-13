
	package ar.edu.itba.ss;

	import java.util.stream.IntStream;

	import ar.edu.itba.ss.core.CellularAutomaton;
	import ar.edu.itba.ss.core.MobileGenerator;

		/**
		* <p>Punto de entrada principal de la simulación. Se encarga de
		* configurar los parámetros de operación y de desplegar el
		* procesamiento requerido.</p>
		*/

	public final class Temp {

		public static void main(final String [] arguments) {

			System.out.println("A cellular automaton...");

			// Parámetros de la simulación:
			final int cycles = 1;		// Cantidad de ciclos de la simulación
			final double Δt = 1.0;		// Paso temporal

			// Parámetros del TP N° 1:
			final int N = 10;			// Cantidad de partículas
			final double L = 10.0;		// Dimensión del espacio
			final double Rc = 0.75;		// Radio de interacción

			// Parámetros del TP N° 2:
			final double V = 0.03;		// Módulo de la velocidad de las partículas
			final double η = 1.0;		// Amplitud de ruido

			// Generador móvil:
			final MobileGenerator generator = MobileGenerator.of(N)
					.maxRadius(0)
					.speed(V)
					.over(L)
					.spy(System.out::println)
					.build();

			// El autómata celular:
			final CellularAutomaton automaton = CellularAutomaton.from(generator)
					.spy((step, ps) -> {})
					.interactionRadius(Rc)
					.spaceOf(L)
					.step(Δt)
					.noise(η)
					.build();

			// Simulación:
			IntStream.range(0, cycles).forEachOrdered(k -> {
				System.out.println("Simulación " + k);
				automaton.advance(k);
			});
		}
	}
