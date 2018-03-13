
package ar.edu.itba.ss;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import ar.edu.itba.ss.Main.EXIT_CODE;
import ar.edu.itba.ss.core.Particle;

/**
	* <p>Punto de entrada principal de la simulación. Se encarga de
	* configurar los parámetros de operación y de desplegar el
	* procesamiento requerido.</p>
	*/


// cycles dt N L RC V n
public final class Main {
	
	private static final String HELP_TEXT = "Cell Index Method Implementation.\n" +
										"Arguments: \n" + 
										"* cycles dt N L RC V noise \n";
	
	enum EXIT_CODE {
		NO_ARGS(-1), 
		BAD_N_ARGUMENTS(-2),
		BAD_ARGUMENT(-3);
		
		private final int code;
		
		EXIT_CODE(final int code) {
			this.code = code;
		}
		
		public int getCode() {
			return code;
		}
	}
	
	private static void exit(final EXIT_CODE exitCode) {
		System.exit(exitCode.getCode());
	}

	public static void main(final String [] args) {
		if (args.length == 0) {
			System.out.println("[FAIL] - No arguments passed. Try 'help' for more information.");
			exit(EXIT_CODE.NO_ARGS);
		} else if (args.length != 7 || args.length != 1) {
			System.out.println("[FAIL] - Wrong number of arguments. Try 'help' for more information.");
			exit(EXIT_CODE.BAD_N_ARGUMENTS);
		}
		
		switch (args[0]) {
			case "help":
				System.out.println(HELP_TEXT);
				break;
			default:
				System.out.println("[FAIL] - Invalid argument. Try 'help' for more information.");
				exit(EXIT_CODE.BAD_ARGUMENT);
				break;
		}
		
		System.out.println("A cellular automaton...");
		final long start = System.nanoTime();
		
		generate(args, start);

		printExecutionTime(start);
	}
	
	// cycles dt N L RC V noise
	private static void generate(String[] args, final long start) {
		Integer cycles = Integer.valueOf(args[0]);
		Double dt = Double.valueOf(args[1]);
		Integer N = Integer.valueOf(args[2]);
		Double L = Double.valueOf(args[3]);
		Double RC = Double.valueOf(args[4]);
		Double V = Double.valueOf(args[5]);
		Double noise = Double.valueOf(args[6]);
		
		
	}
	
	private static void consoleLogging(final Map<Particle, List<Particle>> nnl, final long start) {		
		nnl.forEach((particle, neighbours) -> {

			System.out.println(
					"Particle ID: " + 
					particle.hashCode() + "\t- Position: X: " +
					particle.getX() + ";\t Y: " +
					particle.getY() + "\t - R: " +
					particle.getRadius() + "\t- Neighbours IDs: [" +
					list(neighbours) + "]");
		});
	
	}	
	
	private static void fileLogging(final Map<Particle, List<Particle>> nnl, final long start, final String output_filename) throws FileNotFoundException {
		System.out.println("The output has been written into a file.");
		final String filename = "./" + output_filename + ".txt";
		File file = new File(filename);
		FileOutputStream fos = new FileOutputStream(file);
		PrintStream ps = new PrintStream(fos);
		System.setOut(ps);
		
		nnl.forEach((particle, neighbours) -> {
			System.out.println( 
					particle.hashCode() + " " +
					list(neighbours)
					);
		});
	}
	
	private static void smartLogging(Map<Particle, List<Particle>> nnl, final long start, String filename) throws FileNotFoundException {
		if (filename.equals("null")) {
			consoleLogging(nnl, start);
			
		} else {
			fileLogging(nnl, start, filename);
		}
	}
	
	private static void printExecutionTime(final long start) {
		PrintStream consoleStream = new PrintStream(
                new FileOutputStream(FileDescriptor.out));
				
		System.setOut(consoleStream);
		System.out.println(
				"Execution Time: " + 1E-9*(System.nanoTime() - start) + " sec.\n");
		System.out.println("\n[DONE]");	
	}
	
	private static String list(final List<Particle> neighbours) {
		String list = "";
		for (final Particle particle : neighbours) {
			list += particle.hashCode() + " ";
		}
		if (!neighbours.isEmpty()) {
			return list.substring(0, list.length() - 2);
		}
		else return list;
	}
}
