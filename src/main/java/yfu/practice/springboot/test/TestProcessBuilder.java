package yfu.practice.springboot.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestProcessBuilder {

	public static void main(String[] args) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder("chmod", "-R", "777", "/home/yfu/COV");
		pb.redirectErrorStream(true);
		Process process = pb.start();
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))){
			String line;
			while ((line = br.readLine()) != null) {
				System.err.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("exitValue: " + process.waitFor());
		
		process = new ProcessBuilder("chown", "-R", "yfu:yfu", "/home/yfu/COV").start();
		System.err.println("exitValue: " + process.waitFor());
	}
	
}