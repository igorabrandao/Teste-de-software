/**
 * @author Igor A. Brandão
 * @date 10/08/2017
 */ 
package br.ufrn.imd;

import static org.junit.Assert.assertEquals;
import java.util.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import br.ufrn.imd.TriangleUtil.TriangleType;

@RunWith(value = Parameterized.class)
public class TriangleTest {
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] 
				{
					// EQUILATERAL
					{ 6,6,6, TriangleType.EQUILATERAL },
					
					// ISOCELES
					{ 5,5,4, TriangleType.ISOCELES },
					{ 5,3,4, TriangleType.ISOCELES },
					{ 4,5,5, TriangleType.ISOCELES },
					
					// SCALENE
					{ 5,4,3, TriangleType.SCALENE }, // Case-Test-id: A
					
					// NOT TRIANGLE
					{ 25,3,5, TriangleType.NOT_TRIANGLE }
				});
	}
	
	private int side1;
	private int side2;
	private int side3;
	private TriangleType fExpected;
	
	public TriangleTest(int side1, int side2, int side3, TriangleType expected) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
		this.fExpected = expected;
	}
	
	@Test
	public void checkTriangle() {
		// Triangle object
		TriangleUtil tu = new TriangleUtil();
		
		// Case of test
		TriangleType tType = tu.defineType(this.side1, this.side2, this.side3);
		
		// Check the result
		assertEquals(this.fExpected, tType);
	}
}