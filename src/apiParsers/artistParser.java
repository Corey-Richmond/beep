package apiParsers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class artistParser {

	@SuppressWarnings("null")
	public static void main(String[] args) throws IOException {
		
		double a = 36;
		double b = 42;
		int max = 9000;
		
		System.out.println((b-a)/b*max);/*
		File file = new File("daygos.txt");
		FileWriter fwr = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fwr);
		FileInputStream fileInput=null;
		int r;
		
		try {
 
			char c;
			
			fileInput = new FileInputStream("artists.xml");
			
 
			while ((r = fileInput.read()) != -1) {
				   c = (char) r;
				   String s="";
				   if(c=='<') {
					   r = fileInput.read();
					   c = (char)r;
					   if(c=='n') {
						   r = fileInput.read();
						   c = (char)r;
						   if(c=='a') {
							   r = fileInput.read();
							   c = (char)r;
							   if(c=='m') {
								   r = fileInput.read();
								   c = (char)r;
								   if(c=='e') {
									   r = fileInput.read();
									   c = (char)r;
									   if(c=='>') {
										   r = fileInput.read();
										   c = (char)r;
										   while(c!='<') {
											   if(c!='\n'||c!='\t') {
												   s+=c;
											   }
											   r = fileInput.read();
											   c = (char)r;
										   }
										   s+='\n';
									   }
								   }
							   }
						   }
					   }
				   }
				   if(!s.equals("")) {
					   //fwr.write(s);
					   //fwr.write(System.getProperty("line.separator"));
					   System.out.println(s);
				   }
			}
			fileInput.close();

 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
	
		bw.close();*/
	}

}
