package org.jfree.data.test;
import java.lang.IllegalArgumentException;
import static org.junit.Assert.*;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jfree.data.KeyedValues;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataUtilitiesTestLab3 extends DataUtilities {
	Mockery values2DMockingContext;
	Mockery values2DMockingZeroContext;
	Values2D valuesZero; 
	Values2D values; 
	int [] validRows; 
	int [] validCols;
	double [] dataArray; 
	Number [] expectedNumberArray;
	double [][] dataArray2D; 
	double [][] otherArray2D; 
	double [][] equalOtherArray2D; 
	double [][] differentLengthArray2D;
	double [][] testArray2D; 
	double [][] test2Array2D; 
	double [][] test3Array2D; 
	double [][] test4Array2D; 
	double [][] test5Array2D;
	Number [][] expected2DNumberArray;
	Mockery keyedValuesMockingContext;
	KeyedValues inputValuePairs;
	Mockery keyedValuesMockingZeroContext;
	KeyedValues zeroPairs;
	Mockery keyedValuesMockingNullContext;
	KeyedValues someNullPairs;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		// setup for testing calculateColumnTotal and calculateRowTotal
		values2DMockingContext = new Mockery();
		values = values2DMockingContext.mock(Values2D.class);
		
		values2DMockingContext.checking(new Expectations() {{
			allowing(values).getRowCount();
			will(returnValue(4));
			allowing(values).getColumnCount(); 
			will(returnValue(3)); 
			allowing(values).getValue(0,0);
			will(returnValue(7.5));
			allowing(values).getValue(1, 0);
			will(returnValue(2.5));
			allowing(values).getValue(2, 0);
			will(returnValue(-3));
			allowing(values).getValue(3, 0);
			will(returnValue(10.7));
			allowing(values).getValue(0, 1); 
			will(returnValue(6.7));
			allowing(values).getValue(0, 2); 
			will(returnValue(9.3));
			allowing(values).getValue(1, 1); 
			will(returnValue(7.1));
			allowing(values).getValue(1, 2); 
			will(returnValue(-2.5));
			allowing(values).getValue(2, 1); 
			will(returnValue(null));
			allowing(values).getValue(2, 2); 
			will(returnValue(10));
			allowing(values).getValue(3, 1); 
			will(returnValue(null));
			allowing(values).getValue(3, 2); 
			will(returnValue(null));
		}}); 
		
		values2DMockingZeroContext = new Mockery();
		valuesZero = values2DMockingZeroContext.mock(Values2D.class);
		
		values2DMockingZeroContext.checking(new Expectations() {{
			allowing(valuesZero).getRowCount();
			will(returnValue(0));
			allowing(valuesZero).getColumnCount(); 
			will(returnValue(0));
		}});
		
		validRows = new int [] {1,2}; 
		validCols = new int [] {0,2};
		// setup for testing createNumberArray
		dataArray = new double[]{10.5, -6.7};
		expectedNumberArray = new Number[]{10.5, -6.7};
		
		// setup for testing create2DNumberArray
		dataArray2D = new double [][] {{-6.5, 15.2},{3.8, 8.4}};
		expected2DNumberArray = new Number[][] {{-6.5, 15.2},{3.8, 8.4}};
		
		// setup for testing equals 
		otherArray2D = new double [][] {{1,2,3},{4,5,6}}; 
		equalOtherArray2D = new double [][] {{1,2,3},{4,5,6}}; 
		differentLengthArray2D = new double [][] {{1,2,3,4}};
		testArray2D = new double [][] {{0,2,3,4}}; 
		test2Array2D = new double [][] {{1,2,3,4},{1,2,3,4}}; 
		test3Array2D = new double [][] {{2,2,2,2},{1,2,3,4}};
		test4Array2D = new double [][] {{-1,0,0,0}}; 
		test5Array2D = new double [][] {{2,2,2,2},{3,3,3,3}};
		
		// setup for testing getCumulativePercentages
		keyedValuesMockingContext = new Mockery();
		inputValuePairs = keyedValuesMockingContext.mock(KeyedValues.class); 
		
		keyedValuesMockingContext.checking(new Expectations() {{
			allowing(inputValuePairs).getKey(0); 
			will(returnValue(0));
			allowing(inputValuePairs).getKey(1); 
			will(returnValue(1));
			allowing(inputValuePairs).getKey(2); 
			will(returnValue(2));
			allowing(inputValuePairs).getItemCount();
			will(returnValue(3));
			allowing(inputValuePairs).getValue(0); 
			will(returnValue(5)); 
			allowing(inputValuePairs).getValue(1); 
			will(returnValue(9)); 
			allowing(inputValuePairs).getValue(2); 
			will(returnValue(2)); 
		}});
		
		keyedValuesMockingZeroContext = new Mockery();
		zeroPairs = keyedValuesMockingZeroContext.mock(KeyedValues.class); 
		
		keyedValuesMockingZeroContext.checking(new Expectations() {{
			allowing(zeroPairs).getItemCount();
			will(returnValue(0));
		}});
		
		keyedValuesMockingNullContext = new Mockery();
		someNullPairs = keyedValuesMockingNullContext.mock(KeyedValues.class); 
		
		keyedValuesMockingNullContext.checking(new Expectations() {{
			allowing(someNullPairs).getItemCount();
			will(returnValue(4));
			allowing(someNullPairs).getKey(0); 
			will(returnValue(0));
			allowing(someNullPairs).getKey(1); 
			will(returnValue(1));
			allowing(someNullPairs).getKey(2); 
			will(returnValue(2));
			allowing(someNullPairs).getKey(3); 
			will(returnValue(3));
			allowing(someNullPairs).getValue(0); 
			will(returnValue(5)); 
			allowing(someNullPairs).getValue(1); 
			will(returnValue(null)); 
			allowing(someNullPairs).getValue(2); 
			will(returnValue(35)); 
			allowing(someNullPairs).getValue(3); 
			will(returnValue(10));
		}});
	}
	
	@After
	public void tearDown() throws Exception {
	} 
// Test cases for equals method
	@Test 
	public void equal_WithNullValues_True() {
		assertTrue("Arrays are both null",DataUtilities.equal(null, null)); 
	}
	
	@Test
	public void equal_AIsNull_False() {
		assertFalse("Arrays are not equal",DataUtilities.equal(null, dataArray2D));
	}
	
	@Test
	public void equal_BIsNull_False() {
		assertFalse("Arrays are not equal",DataUtilities.equal(dataArray2D, null));
	}
	
	@Test 
	public void equal_AIsEqualToB_True() {
		assertTrue("Arrays are equal",DataUtilities.equal(dataArray2D, dataArray2D));
	}
	
	@Test
	public void equal_AIsNotEqualToB_False() {
		assertFalse("Arrays are not equal",DataUtilities.equal(dataArray2D, otherArray2D));
	}
	@Test
	public void equal_AHasDiffernetLengthThanB_False() {
		assertFalse("Arrays are not equal",DataUtilities.equal(dataArray2D, differentLengthArray2D));
	}
	// Test cases added for assignment 4
	@Test 
	public void equal_SameLengthDifferentFirstElement_False() {
		assertFalse("Arrays are not equal",DataUtilities.equal(differentLengthArray2D,testArray2D)); 
	}
	@Test 
	public void equal_SameArrayExceptBHasOneExtraRow_False() {
		assertFalse("Arrays are not equal",DataUtilities.equal(differentLengthArray2D,test2Array2D)); 
	}
	@Test 
	public void equal_SameArrayExceptAHasOneExtraRow_False() {
		assertFalse("Arrays are not equal",DataUtilities.equal(test2Array2D,differentLengthArray2D)); 
	}
	@Test 
	public void equal_FirstRowsNotEqualSecondRowsEqual_False() {
		assertFalse("Arrays are not equal",DataUtilities.equal(test2Array2D,test3Array2D)); 
	}
	@Test 
	public void equal_FirstRowsEqualSecondRowsNotEqual_False() {
		assertFalse("Arrays are not equal",DataUtilities.equal(test2Array2D,test3Array2D)); 
	}

// Test cases for clone method
	@Test(expected = IllegalArgumentException.class)
	public void clone_inputIsNull_ThrowEception() {
		DataUtilities.clone(null);
	}
	@Test
	public void clone_input2DArray_returnArray() {
		assertArrayEquals(dataArray2D,DataUtilities.clone(dataArray2D)); 
	}
	
// Test cases for calculateColumnTotal method
	@Test
	public void calculateColumnTotal_WithTwoRows() {
		
		double result = DataUtilities.calculateColumnTotal(values, 0,validRows); 
		assertEquals(-0.5, result, .000000001d);
	}
	@Test
	public void calculateColumnTotal_WithTwoValidRows_DataWithZeroRows() {
		
		double result = DataUtilities.calculateColumnTotal(valuesZero, 0,validRows); 
		assertEquals(0, result, .000000001d);
	}
	@Test
	public void calculateColumnTotal_WithTwoRows_WithSomeValuesAsNull() {
		
		double result = DataUtilities.calculateColumnTotal(values, 1,validRows); 
		assertEquals(7.1, result, .000000001d);
	}
	@Test(expected = IllegalArgumentException.class)
	public void calculateColumnTotal_WithNullDataAndTwoRows() {
		
		DataUtilities.calculateColumnTotal(null, 0,validRows); 
	}
	@Test
	public void calculateColumnTotal_WithFourValues() {
		// exercise
		double result = DataUtilities.calculateColumnTotal(values, 0);
		// verify
		assertEquals(17.7, result, .000000001d);
	}
	@Test
	public void calculateColumnTotal__DataWithZeroRows() {
		// exercise
		double result = DataUtilities.calculateColumnTotal(valuesZero, 0);
		// verify
		assertEquals(0, result, .000000001d);
	}
	@Test
	public void calculateColumnTotal_WithFourValues_WithSomeValuesAsNull() {
		// exercise
		double result = DataUtilities.calculateColumnTotal(values, 1);
		// verify
		assertEquals(13.8, result, .000000001d);
	}
	@Test(expected = IllegalArgumentException.class)
	public void calculateColumnTotal_WithNull() {
		DataUtilities.calculateColumnTotal(null, 0);  
	}
// Test cases for calculateRowTotal method
	@Test
	public void calculateRowTotal_WithTwoColumns() {
		double result = DataUtilities.calculateRowTotal(values, 1, validCols);
		assertEquals(0, result,.000000001d);
	}
	@Test
	public void calculateRowTotal_WithTwoValidColumns_DataWithZeroColumns() {
		double result = DataUtilities.calculateRowTotal(valuesZero, 1, validCols);
		assertEquals(0, result,.000000001d);
	}
	@Test
	public void calculateRowTotal_WithTwoColumns_WithSomeValuesAsNull() {
		double result = DataUtilities.calculateRowTotal(values, 3, validCols);
		assertEquals(10.7, result,.000000001d);
	}
	@Test(expected = IllegalArgumentException.class)
	public void calculateRowTotal_WithNullDataAndTwoColumns() {
		
		DataUtilities.calculateRowTotal(null, 1,validCols); 
	}
	@Test 
	public void calculateRowTotal_WithThreeValues() {
		// exercise
		double result = DataUtilities.calculateRowTotal(values, 0); 
		// verify
		assertEquals(23.5, result,.000000001d);
	}
	@Test 
	public void calculateRowTotal_DataWithZeroColumns() {
		// exercise
		double result = DataUtilities.calculateRowTotal(valuesZero, 0); 
		// verify
		assertEquals(0, result,.000000001d);
	}
	@Test 
	public void calculateRowTotal_WithThreeValues_WithSomeValuesAsNull() {
		// exercise
		double result = DataUtilities.calculateRowTotal(values, 2); 
		// verify
		assertEquals(7.0, result,.000000001d);
	}
	@Test(expected = IllegalArgumentException.class)
	public void calculateRowTotal_WithNull() {
		DataUtilities.calculateRowTotal(null, 0);  
	}
	
// Test cases for createNumberArray method
	@Test
	public void createNumberArray_WithTwoValues() {
		
		Number [] result = DataUtilities.createNumberArray(dataArray);
		
		assertArrayEquals(expectedNumberArray,result); 
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createNumberArray_WithNull() {
		DataUtilities.createNumberArray(null); 
	}
	
// Test cases for createNumberArray2D method
	@Test
	public void create2DNumberArray_WithFourValues() { 
		
		Number [][] result = DataUtilities.createNumberArray2D(dataArray2D); 
		
		assertArrayEquals(expected2DNumberArray,result); 
	}

	@Test(expected = IllegalArgumentException.class)
	public void create2DNumberArray_WithNull() {
		DataUtilities.createNumberArray2D(null); 
	}
	
// Test cases for getCumulativePercentages method
	@Test
	public void getCumulativePercentages_WithThreeValues() {
		
		KeyedValues result = DataUtilities.getCumulativePercentages(inputValuePairs); 
		
		assertEquals(0.3125,result.getValue(0).doubleValue(),.000000001d);
		assertEquals(0.875,result.getValue(1).doubleValue(),.000000001d);
		assertEquals(1.0,result.getValue(2).doubleValue(),.000000001d);
	}
	@Test
	public void getCumulativePercentages_WithZeroVaues() {
		
		KeyedValues result = DataUtilities.getCumulativePercentages(zeroPairs); 
		
		assertEquals(0,result.getItemCount(),.000000001d);
	}
	@Test
	public void getCumulativePercentages_WithSomeVauesAsNull() {
		
		KeyedValues result = DataUtilities.getCumulativePercentages(someNullPairs); 
		
		assertEquals(0.1,result.getValue(0).doubleValue(),.000000001d);
		assertEquals(0.1,result.getValue(1).doubleValue(),.000000001d);
		assertEquals(0.8,result.getValue(2).doubleValue(),.000000001d);
		assertEquals(1.0,result.getValue(3).doubleValue(),.000000001d);
	}
	@Test(expected = IllegalArgumentException.class)
	public void getCulativePercentages_WithNull() {
		DataUtilities.getCumulativePercentages(null); 
	}

}