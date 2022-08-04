package com.kanou;

import com.kanou.entity.CocRole;
import com.kanou.util.CocRoleCreate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Scanner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrpgHelperApplicationTests{

	@Test
	void contextLoads() throws IOException {
		CocRole cocRole = new CocRole();
		cocRole.setPlName("乔纳森");
		cocRole.setPcName("杰西卡");
		CocRoleCreate.getImageByRole(cocRole,1);
	}

	public static void main(String[] arg) {
		Scanner scanner = new Scanner(System.in);
		String a = scanner.next();
		System.out.println(a + 1);
	}

}
