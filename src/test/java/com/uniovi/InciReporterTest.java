package com.uniovi;

import org.junit.Test;

import uo.asw.dbManagement.model.Incidence;
import uo.asw.reporter.InciReporter;

public class InciReporterTest {

	@Test
	public void test() {
		InciReporter.reportInci(new Incidence());
	}

}
