package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import service.IOService;

public class IOServiceImpl implements IOService {

	@Override
	public boolean writeFile(String file, String userId, String fileName) {
		File f = new File("F:\\Workspace\\BFServer\\"+userId+"\\"+userId + "_" + fileName+".txt");
		IOServiceImpl.createHistory(file, userId, fileName);
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
		File f = new File("F:\\Workspace\\BFServer\\"+userId+"\\"+userId + "_" + fileName+".txt");
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
	public String[] readFileList(String userId,String fileName) {
		// TODO Auto-generated method stub
		File history=new File("F:\\Workspace\\BFServer\\"+userId+"\\"+userId+"_"+fileName+"_history");
		ArrayList<String> versions=new ArrayList<String>();
		String content="";

		try {
			FileReader fr = new FileReader(history);
			BufferedReader br=new BufferedReader(fr);
			try {
				String line=br.readLine();
				while(line!=null){
					
						content=line.substring(0,line.indexOf('/'));
						versions.add(content);
						line=br.readLine();

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int size=versions.size();
		String[] v=(String[])versions.toArray(new String[size]);

		return v;
	}


	public static boolean createHistory(String file, String userId, String fileName) {
		File history = new File("F:\\Workspace\\BFServer\\"+userId+"\\"+userId + "_" + fileName + "_history");

		try {
			FileWriter fw = new FileWriter(history, true);
			Calendar cal=Calendar.getInstance();
			fw.write(String.valueOf(cal.get(Calendar.YEAR)) + String.valueOf(cal.get(Calendar.MONTH)+1)
					+ String.valueOf(cal.get(Calendar.DATE)) + String.valueOf(cal.get(Calendar.HOUR))
					+ String.valueOf(cal.get(Calendar.MINUTE)) + String.valueOf(cal.get(Calendar.SECOND))+"/"+file+"\n");
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String[] getVersionContent(String userId,String fileName){
		File history=new File("F:\\Workspace\\BFServer\\"+userId+"\\"+userId+"_"+fileName+"_history");
		ArrayList<String> versions=new ArrayList<String>();
		String content="";

		try {
			FileReader fr = new FileReader(history);
			BufferedReader br=new BufferedReader(fr);
			try {
				String line=br.readLine();
				while(line!=null){
					
						content=line.substring(line.indexOf('/')+1);
						versions.add(content);
						line=br.readLine();

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int size=versions.size();
		String[] versionContent=(String[])versions.toArray(new String[size]);

		return versionContent;
		
	}
}
