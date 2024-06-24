package com.testdata.allPages;

public interface TestDataInterface {

	interface Dashboard {
		String userName_passCase = "//span[contains(text(),'Chiragm')]";
		String userName_failCase = "//span[contains(text(),'Hello')]";
	}

}
