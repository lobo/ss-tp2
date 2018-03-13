package ar.edu.itba.ss.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import ar.edu.itba.ss.core.interfaces.ParticleGenerator;

public class Parser implements ParticleGenerator{
	
	static List<MobileParticle> particles = new ArrayList<MobileParticle>();
	
	public Parser (String dynamicFilePath) {

		try {
			
			File dynamicFIle = new File(dynamicFilePath);
			
			try { 
				double radius;
				Scanner dynamicRead = new Scanner(dynamicFIle);
				
				dynamicRead.next(); // avoid t0 in Dynamic File
			
				while(dynamicRead.hasNext()){
					particles.add(new MobileParticle(
							Double.parseDouble(dynamicRead.next()), // x
							Double.parseDouble(dynamicRead.next()), // y
							0, //radius always 0
							Double.parseDouble(dynamicRead.next()), // vx
							Double.parseDouble(dynamicRead.next())) // vy
					);
				}
				
				dynamicRead.close();			
			} catch (Exception e) {
				System.out.println("Error scanning file");
			}
			
		} catch (Exception e) {
			System.out.println("Error opening or finding file");
		}
		
	}
	
	public int getL() {
		return 0; // does not come in the file
	}
	
	@Override
	public Stream<MobileParticle> generate() {
		return particles.stream();
	}

	@Override
	public int size() {
		return particles.size();
	}

	@Override
	public double maxRadius() {
		return 0;
	}
}

