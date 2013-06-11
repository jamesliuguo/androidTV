package com.v.tv;

import junit.framework.TestCase;

public class test extends TestCase {
	public void test()
	{
		NetClient c = new NetClient();
		String ret = c.Get("");
		System.out.print(ret);
	}
}
