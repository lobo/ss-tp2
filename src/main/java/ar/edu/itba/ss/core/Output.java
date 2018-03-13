package ar.edu.itba.ss.core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class Output {
	private static Output instance = null;
	
	public static Output getInstace(){
		if(instance == null)
			instance = new Output();
		return instance;
	}

	public void write(Set<MobileParticle> set, double time){
		if(time == 0){
			try{
				PrintWriter pw = new PrintWriter("output.txt");
				pw.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", true)))) {
			out.write(String.valueOf(time) + "\n");
			for(MobileParticle p: set){
				out.write(p.getX() + " " +  p.getY() + " " + p.getθ() + "\n");
			}
		}catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
}
