package com.newlinecode.scalewayautobackup

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ScalewayAutoBackupApplication implements CommandLineRunner {

	@Autowired
	private ServerLookup serverLookup

	static void main(String[] args) {
		SpringApplication.run(ScalewayAutoBackupApplication, args)
	}

	@Override
	public void run(String... args) {
		serverLookup.dispatchBackup()
	}

}
