package com.lambdatest;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class MethodTest {

	public static void main(String[] args) {
	}

	
	@Test
	public void testFileSystem() throws IOException {
		
		String path = "parent/newdir";
		String fileName = "test.txt";
		
		File dirs = new File(path);
		
		if (!dirs.exists()) {
			dirs.mkdirs();
		}
		
		
		File file = new File(path + "/" + fileName);
		
		if (!file.exists()) {
			file.delete();
		}
		
		file.createNewFile();
		
		System.out.println(file.getPath());
		
	}

}
