/**
 * @author Igor A. Brandão
 * @date 10/08/2017
 */ 
package br.ufrn.imd;

public class TriangleUtil {
	
	public enum TriangleType
	{
		NOT_TRIANGLE, EQUILATERAL, ISOCELES, SCALENE;
	}
	
	public TriangleType defineType(int side1, int side2, int side3) throws IllegalArgumentException
	{
		// Validate the user input
		if ( (side1 <= 0 || side1 > 100) || (side2 <= 0 || side2 > 100) || (side3 <= 0 || side3 > 100) ) {
	        throw new IllegalArgumentException();
	    }
		
		// Check triangle type
        if(side1==side2 && side2==side3)
        	return TriangleType.EQUILATERAL;

        else if(side1 >= (side2+side3) || side3 >= (side2+side1) || side2 >= (side1+side3) )
            return TriangleType.NOT_TRIANGLE;

        else if ((side1==side2 && side2!=side3 ) || (side1!=side2 && side3==side1) || (side3==side2 && side3!=side1))
        	return TriangleType.ISOCELES;

        else if(side1!=side2 && side2!=side3 && side3!=side1)
        	return TriangleType.SCALENE;
        
        return null;
	}
}