package com.util;

import java.util.List;

import org.junit.Test;

public class EasyMobApiUtilsTest {

	@Test
	public void testGetFriends() {
		List<String> friends = EasyMobApiUtils.getFriends("13308699693", EasyMobApiUtils.getToken());
		System.out.println("========================" + friends);
	}

	@Test
	public void testGetToken() {
		String token = EasyMobApiUtils.getToken();
		System.out.println("=======================: " +token);
	}

}
