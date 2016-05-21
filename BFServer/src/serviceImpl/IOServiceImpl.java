package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import service.IOService;

public class IOServiceImpl implements IOService {

	@Override
	public boolean writeFile(String file, String userId, String fileName) {
		File f = new File(userId + "_" + fileName);
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String readFile(String userId, String fileName) {
		// TODO Auto-generated method stub
		File f = new File(userId + "_" + fileName);
		String content = "";

		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			try {
				String line = br.readLine();
				while (line != null) {
					content += line;
					line = br.readLine();
				}
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			return "File Not Found!";
		}
		
		return content;
	}

	@Override
	public String readFileList(String userId) {
		// TODO Auto-generated method stub
		
		return "OK";
	}

}
