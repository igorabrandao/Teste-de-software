package imd0412.academic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Suite of tests for the Grade Module.
 */
public class GradeModuleSuiteTest
{
	@Test
	public void testShouldReturnREC()
	{
		ApprovalStatus status = GradeModule.performPartialConsolidation(0.0f, 10.0f, 6.0f, 80);

		assertEquals(ApprovalStatus.REC, status);
	}

	@Test
	public void testShouldReturnREP()
	{
		ApprovalStatus status = GradeModule.performPartialConsolidation(0.0f, 0.0f, 6.0f, 80);

		assertEquals(ApprovalStatus.REP, status);
	}
}
