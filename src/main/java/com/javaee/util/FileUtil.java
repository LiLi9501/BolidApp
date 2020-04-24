package com.javaee.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtil {
	private Logger log = Logger.getLogger(FileUtil.class.toString());
	private String filePath;

	public FileUtil(String filePath) {
		this.filePath = filePath;
	}


	public File openFile() {
		File file = new File(FileUtil.class.getClassLoader().getResource(filePath).getFile());
		if (file.exists())
			return file;

		log.log(Level.SEVERE, "File not found: " + filePath);
		return null;
	}

	

	public void saveMessageToFile(String message) {

		FileWriter fw = null;
		File file = openFile();

		try {

			String filename = file.getPath();
			fw = new FileWriter(filename, true);
			fw.write(message + "\n");

		} catch (IOException e) {
			log.log(Level.SEVERE, "Error on save file: " + e.getMessage());
		} finally {
			if (fw != null)
				try {
					fw.close();
				} catch (Exception e) {
					log.log(Level.SEVERE, "Error on save file: " + e.getMessage());
					e.printStackTrace();
				}
		}
		log.info("Message saved under : " + file.getPath());
	}
}
