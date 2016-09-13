package cn.net.lisong.solr.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFiles {

	public static void main(String[] args) {
		String path = "E:/_recent/notes/linux/";
		File file = new File(path);
		if (file.isDirectory()) {
			File[] fs = file.listFiles();
			for (File f : fs) {
				if (f.isFile()) {
					try {
						System.out.println(f.getName());
						BufferedReader br = new BufferedReader(new FileReader(f));
						StringBuilder sb = new StringBuilder();
						String tmp = "";
						while ((tmp = br.readLine()) != null) {
							sb.append(tmp);
							sb.append("\n");
						}
						
						System.out.println(sb);
						
						br.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
}
