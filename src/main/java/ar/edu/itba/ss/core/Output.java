package ar.edu.itba.ss.core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Output {
	private static Output instance = null;
	
	public static Output getInstace(){
		if(instance == null)
			instance = new Output();
		return instance;
	}

	public void write(List<MobileParticle> list, int cycle){
		if(cycle == 0){
			try{
				PrintWriter pw = new PrintWriter("output.txt");
				pw.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", true)))) {
			out.write(String.valueOf(list.size()) + "\n");
			out.write(String.valueOf(cycle) + "\n");
			for(MobileParticle p: list){
				out.write(p.getX() + " " +  p.getY() + " " + p.getθ() + "\n");
			}
		}catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
}
