package com.springboot.demo.shiro_jwt;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroJwtApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testSha() {
		ByteSource sale = ByteSource.Util.bytes("graython");
		Sha1Hash sha1Hash = new Sha1Hash("123456", sale, 16);
		System.out.println(sha1Hash);
	}

	@Test
	public void testSimple() {
		String hashAlgorithmName = "SHA-1";
		SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, "123456", ByteSource.Util.bytes("plum"), 16);
		System.out.println(simpleHash.toString());
	}
}
