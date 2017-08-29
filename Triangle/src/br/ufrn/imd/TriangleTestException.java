/**
 * @author Igor A. Brandão
 * @date 10/08/2017
 */ 
package br.ufrn.imd;

import org.junit.Test;
import br.ufrn.imd.TriangleUtil;

public class TriangleTestException {
	
	@Test(expected = IllegalArgumentException.class)
	public void evaluateShouldThrowException() {
		// Triangle object
		TriangleUtil tu = new TriangleUtil();
		
		// Case of test
		tu.defineType(100, 5, 0);
	}
}