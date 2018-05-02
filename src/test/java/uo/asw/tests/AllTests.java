package uo.asw.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uo.asw.tests.business.PruebaJSON2String;

@RunWith(Suite.class)
@SuiteClasses({
	
	PruebaJSON2String.class
	
})
public class AllTests { }