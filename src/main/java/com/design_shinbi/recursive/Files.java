package com.design_shinbi.recursive;

import java.io.File;

public class Files {
	public static void displayFiles(File file) {
		if(file.isDirectory()) {
			System.out.println("=== " + file.getAbsolutePath() + " ===");
			
			File[] children = file.listFiles();
			if(children != null) {    // フォルダーの場合
				for(File child : children) {
					displayFiles(child);
				}
			}
		}
		else {    // ファイルの場合
			System.out.println("    " + file.getName());
		}
	}
}
