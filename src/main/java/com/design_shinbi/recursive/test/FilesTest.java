package com.design_shinbi.recursive.test;

import java.io.File;

import org.junit.jupiter.api.Test;

import com.design_shinbi.recursive.Files;

class FilesTest {

	@Test
	void test() {
		File folder = new File("C:\\xampp");
		Files.displayFiles(folder);
	}
}
