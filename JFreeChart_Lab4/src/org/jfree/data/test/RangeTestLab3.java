package org.jfree.data.test;
import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.Range;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RangeTestLab3 {

	private Range testRange1;
	private Range testRange2;
	private Range testRange3;
	private Range testRange4;
	private Range testRange5;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testRange1 = new Range(-10, -5);
		testRange2 = new Range(-1, 1);
		testRange3 = new Range(-5, 5);
		testRange4 = new Range(5, 7);
		testRange5 = new Range(0,4);
	}

	@After
	public void tearDown() throws Exception {
	}	

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none(); 
	
//	---------------------- Assignment 4 Tests -------------------------------
	
	@Test
	public void getCentralValue_ValidRange_CenterValue() {
		assertEquals("Center Value should be 2", 2, testRange5.getCentralValue(), .000000001d);
		assertEquals("Upper bound should still be 4", 4, testRange5.getUpperBound(), .000000001d);
		assertEquals("Lower bound should still be 0", 0, testRange5.getLowerBound(), .000000001d);
		assertEquals("Center Value should be -7.5", -7.5, testRange1.getCentralValue(), .000000001d);
		
	}
	
	@Test
	public void testIntersects() {
		assertTrue("Should be true, lower is below range upper", testRange5.intersects(3.5, 5));
		assertFalse("Should be false, lower is range upper", testRange5.intersects(4, 5));
		assertFalse("Should be false, lower is above range upper", testRange5.intersects(4.5, 5));
		assertFalse("Should be false, upper is below range lower", testRange5.intersects(-5, -0.5));
		assertFalse("Should be false, upper is range lower", testRange5.intersects(-5, 0));
		assertTrue("Should be true, upper is above range lower", testRange5.intersects(-5, 0.5));
		assertTrue("Should be true, upper is above range lower", testRange1.intersects(-7, -2));
	}
	
	@Test
	public void testExpand() {
		Range result = Range.expand(testRange1, 0.5, 0.25);
		
		assertEquals("Upper bound should be -3.75", -3.75, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be -12.5", -12.5, result.getLowerBound(), .000000001d);
		

		result = Range.expand(testRange2, -1, -1);
		
		assertEquals("Upper bound should be 0", 0, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, result.getLowerBound(), .000000001d);
		

		result = Range.expand(testRange4, -2, -2);
		
		assertEquals("Upper bound should be 6", 6, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 6", 6, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void testScale() {
		Range result1 = Range.scale(testRange1, 2);
		
		assertEquals("Upper bound should be -10", -10, result1.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be -20", -20, result1.getLowerBound(), .000000001d);

		Range result2 = Range.scale(testRange1, 0.1);
		
		assertEquals("Upper bound should be -0.5", -0.5, result2.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be -1", -1, result2.getLowerBound(), .000000001d);
	}
	
	@Test
	public void testConstrain() {
		assertEquals("Value is inside range", 1, testRange5.constrain(1), .000000001d);
		assertEquals("Value is lower bound", 0, testRange5.constrain(0), .000000001d);
		assertEquals("Value is outside range", 0, testRange5.constrain(-1), .000000001d);
	}
	
	@Test
	public void testCombine() {
		Range range1 = new Range(0,4);
		Range range2 = new Range(-1,3);
		Range result = Range.combine(range1, range2);

		assertEquals("Upper bound should be 4", 4, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be -1", -1, result.getLowerBound(), .000000001d);
		

		range1 = new Range(0,4);
		range2 = new Range(1,5);
		result = Range.combine(range1, range2);

		assertEquals("Upper bound should be 5", 5, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void testEquals() {
		assertTrue("Objects are equal", testRange3.equals(new Range(-5,5)));
		assertFalse("Objects are not equal", testRange3.equals(new Range(-6,5)));
		assertFalse("Objects are not equal", testRange3.equals(new Range(-4,5)));
		assertFalse("Objects are not equal", testRange3.equals(new Range(-5,4)));
		assertFalse("Objects are not equal", testRange3.equals(new Range(-5,6)));
	}
	
	@Test
	public void testHashCode() {
        assertEquals(testRange5.hashCode(), 1074790400);

    }
	
	
//	---------------------- Assignment 3 Tests -------------------------------
	
	@Test
	public void constructor_ValidFields_ReturnsValidRange() {
		Range range = new Range(0,4);
		assertEquals("Upper bound should be 4", 4, range.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, range.getLowerBound(), .000000001d);
	}
	
	@Test
	public void constructor_InvalidFields_ReturnsException() {
		exceptionRule.expect(IllegalArgumentException.class);
		Range range = new Range(5,0);
	}
	
	@Test
	public void getLowerBound_ValidRange_LowerBound() {
		assertEquals("Lower Bound should be 0", 0, testRange5.getLowerBound(), .000000001d);
	}
	
	@Test
	public void getUpperBound_ValidRange_UpperBound() {
		assertEquals("Upper Bound should be 4", 4, testRange5.getUpperBound(), .000000001d);
	}
	
	@Test
	public void getLength_ValidRange_Length() {
		assertEquals("Length should be 4", 4, testRange5.getLength(), .000000001d);
	}
	
	
	
	@Test
	public void contains_ValueBelowRange_False() {
		assertFalse("Value is below range", testRange5.contains(-2));
	}
	
	@Test
	public void contains_ValueInRange_True() {
		assertTrue("Value is inside range", testRange5.contains(2));
	}
	
	@Test
	public void contains_ValueAboveRange_False() {
		assertFalse("Value is above range", testRange5.contains(7));
	}
	
	@Test
	public void intersects_ParameterLowerBelowRange_True() {
		assertTrue("Lower value is below range", testRange5.intersects(-2, 2));
	}
	
	@Test
	public void intersects_ParameterLowerInsideRange_True() {
		assertTrue("Lower value is inside range", testRange5.intersects(2, 4));
	}
	
	@Test
	public void intersects_ParameterRangeInsideRange_True() {
		Range range = new Range(2,3);
		assertTrue("Parameter range is inside range", testRange5.intersects(range));
	}
	
	@Test
	public void constrain_ValueBelowRange_LowerBound() {
		assertEquals("Value is below range", 0, testRange5.constrain(-2), .000000001d);
	}
	
	@Test
	public void constrain_ValueInsideRange_Value() {
		assertEquals("Value is inside range", 2, testRange5.constrain(2), .000000001d);
	}
	
	@Test
	public void constrain_ValueAboveRange_UpperBound() {
		assertEquals("Value is above range", 4, testRange5.constrain(7), .000000001d);
	}
	
	@Test
	public void combine_OverlappingValidRanges_CombinedRange() {
		Range range1 = new Range(0,4);
		Range range2 = new Range(2,5);
		Range result = Range.combine(range1, range2);

		assertEquals("Upper bound should be 5", 5, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void combine_NullSecondRange_FirstRange() {
		Range range1 = new Range(0,4);
		Range range2 = null;
		Range result = Range.combine(range1, range2);

		assertEquals("Upper bound should be 4", 4, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void combine_NullFirstRange_SecondRange() {
		Range range1 = null;
		Range range2 = new Range(0,4);
		Range result = Range.combine(range1, range2);

		assertEquals("Upper bound should be 4", 4, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void combineIgnoringNan_OverlappingValidRanges_CombinedRange() {
		Range range1 = new Range(0,4);
		Range range2 = new Range(2,5);
		Range result = Range.combineIgnoringNaN(range1, range2);

		assertEquals("Upper bound should be 5", 5, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void combineIgnoringNan_NanSecondRange_Null() {
		Range range1 = new Range(0,4);
		Range range2 = new Range(Double.NaN,Double.NaN);
		Range result = Range.combineIgnoringNaN(range1, range2);

		assertEquals("Upper bound should be 4", 4, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void combineIgnoringNan_NullFirstRange_SecondRange() {
		Range range1 = null;
		Range range2 = new Range(2,5);
		Range result = Range.combineIgnoringNaN(range1, range2);

		assertEquals("Upper bound should be 5", 5, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 2", 2, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void combineIgnoringNan_NullFirstRangeNaNSecondRangeUpperBound_Null() {
		Range range1 = null;
		Range range2 = new Range(0,Double.NaN);
		Range result = Range.combineIgnoringNaN(range1, range2);

		assertEquals("Upper bound should be NaN", Double.NaN, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void combineIgnoringNan_NullFirstRangeNaNSecondRangeLowerBound_Null() {
		Range range1 = null;
		Range range2 = new Range(Double.NaN,4);
		Range result = Range.combineIgnoringNaN(range1, range2);

		assertEquals("Upper bound should be 4", 4, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be NaN", Double.NaN, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void combineIgnoringNan_NullSecondRange_FirstRange() {
		Range range1 = new Range(0,4);
		Range range2 = null;
		Range result = Range.combineIgnoringNaN(range1, range2);

		assertEquals("Upper bound should be 4", 4, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void combineIgnoringNan_NaNFirstRangeLowerBoundNullSecondRange_Null() {
		Range range1 = new Range(Double.NaN,4);
		Range range2 = null;
		Range result = Range.combineIgnoringNaN(range1, range2);

		assertEquals("Upper bound should be 4", 4, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be NaN", Double.NaN, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void combineIgnoringNan_NaNFirstRangeUpperBoundNullSecondRange_Null() {
		Range range1 = new Range(0,Double.NaN);
		Range range2 = null;
		Range result = Range.combineIgnoringNaN(range1, range2);

		assertEquals("Upper bound should be NaN", Double.NaN, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void combineIgnoringNan_NullFirstRangeNanSecondRange_Null() {
		Range range1 = null;
		Range range2 = new Range(Double.NaN,Double.NaN);
		Range result = Range.combineIgnoringNaN(range1, range2);

		assertNull("Result should be null", result);
	}
	
	@Test
	public void combineIgnoringNan_NanFirstRangeNullSecondRange_Null() {
		Range range1 = new Range(Double.NaN,Double.NaN);
		Range range2 = null;
		Range result = Range.combineIgnoringNaN(range1, range2);

		assertNull("Result should be null", result);
	}
	
	@Test
	public void combineIgnoringNan_FirstRangeNanUpperSecondRangeNanLower_Null() {
		Range range1 = new Range(0,Double.NaN);
		Range range2 = new Range(Double.NaN,5);
		Range result = Range.combineIgnoringNaN(range1, range2);

		assertEquals("Upper bound should be 5", 5, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void combineIgnoringNan_FirstRangeNanLowerSecondRangeNanUpper_Null() {
		Range range1 = new Range(Double.NaN,4);
		Range range2 = new Range(2,Double.NaN);
		Range result = Range.combineIgnoringNaN(range1, range2);

		assertEquals("Upper bound should be 4", 4, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 2", 2, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void expandToInclude_ValueBelowRange_ExpandedRange() {
		Range result = Range.expandToInclude(testRange5, -2);
		
		assertEquals("Upper bound should be 4", 4, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be -2", -2, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void expandToInclude_ValueInsideRange_OriginalRange() {
		Range result = Range.expandToInclude(testRange5, 2);
		
		assertEquals("Upper bound should be 4", 4, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void expandToInclude_ValueAboveRange_ExpandedRange() {
		Range result = Range.expandToInclude(testRange5, 7);
		
		assertEquals("Upper bound should be 7", 7, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void expandToInclude_NullRange_RangeOfValue() {
		Range result = Range.expandToInclude(null, 7);
		
		assertEquals("Upper bound should be 7", 7, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 7", 7, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void expand_ValidMargins_ExpandedRange() {
		Range result = Range.expand(testRange5, 0.5, 0.5);
		
		assertEquals("Upper bound should be 6", 6, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be -2", -2, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void expand_MarginsCrossingBounds_RangeOfCentralValue() {
		Range result = Range.expand(testRange5, -1, -1);
		
		assertEquals("Upper bound should be 2", 2, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 2", 2, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void shift_ValidValue_ShiftedRange() {
		Range result = Range.shift(testRange5, 5);

		assertEquals("Upper bound should be 9", 9, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 5", 5, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void shift_ShiftPositiveRangeDownWithZeroCrossing_ShiftedRange() {		
		Range result = Range.shift(new Range(1,8), -5, true);
		
		assertEquals("Upper bound should be 3", 3, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be -4", -4, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void shift_ShiftPositiveRangeDownWithoutZeroCrossing_ShiftedRangeTruncatedAtZero() {		
		Range result = Range.shift(new Range(1,8), -5, false);
		
		assertEquals("Upper bound should be 3", 3, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void shift_ShiftRangeWithNegLowerBoundDownWithoutZeroCrossing_ShiftedRange() {		
		Range result = Range.shift(new Range(-1,8), -5, false);
		
		assertEquals("Upper bound should be 3", 3, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be -6", -6, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void shift_ShiftRangeWithZeroLowerBoundDownWithoutZeroCrossing_ShiftedRange() {		
		Range result = Range.shift(new Range(0,8), -5, false);
		
		assertEquals("Upper bound should be 3", 3, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be -5", -5, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void scale_PositiveFactor_ScaledRange() {
		Range result = Range.scale(testRange5, 2);
		
		assertEquals("Upper bound should be 8", 8, result.getUpperBound(), .000000001d);
		assertEquals("Lower bound should be 0", 0, result.getLowerBound(), .000000001d);
	}
	
	@Test
	public void scale_NegativeFactor_ReturnsException() {
		exceptionRule.expect(IllegalArgumentException.class);
		Range result = Range.scale(testRange5, -2);
	}
	
	@Test
	public void equals_NullObject_False() {
		assertFalse("Result should be null", testRange5.equals(null));
	}
	
	@Test
	public void equals_EqualRange_True() {
		assertTrue("Objects are equal", testRange5.equals(new Range(0,4)));
	}
	
	@Test
	public void equals_LowerBoundDifferent_False() {
		assertFalse("Lower bound is different, not equal", testRange5.equals(new Range(1,4)));
	}
	
	@Test
	public void equals_UpperBoundDifferent_False() {
		assertFalse("Upper bound is different, not equal", testRange5.equals(new Range(0,5)));
	}
	
	@Test
	public void isNanRange_ValidRange_False() {
		assertFalse("Valid range, return false", testRange5.isNaNRange());
	}
	
	@Test
	public void isNanRange_NanRange_True() {
		Range range = new Range(Double.NaN, Double.NaN);
		assertTrue("Nan range, return true", range.isNaNRange());
	}
	
	@Test
	public void hashCode_ValidRange_HashCode() {
		assertTrue("Hashes are equal", new Range(0,4).hashCode() == new Range(0,4).hashCode());
	}
	
	@Test
	public void toString_ValidRange_JavadocsString() {
		assertEquals("String from Javadocs", "Range[0.0,4.0]", testRange5.toString());
	}
	
	
	
	

	
	
	
	
//	---------------------- Assignment 2 Tests -------------------------------
	
//	Tests for constrain
	
	//this test covers the above upper bound case for value in the constrain method using a range of (-1, 1)
	@Test
	public void constrain_ValueAboveUpperBound_ReturnsUpperBound() {
		assertEquals("Contrain with value of 1.1 should be 1", 1, testRange2.constrain(1.1), .000000001d);
	}
	
	//this test covers the upper bound case for value in the constrain method using a range of (-1, 1)
	@Test
	public void constrain_ValueUpperBound_ReturnsUpperBound() {
		assertEquals("Contrain with value of 1 should be 1", 1, testRange2.constrain(1), .000000001d);
	}
	
	//this test covers the below upper bound case for value in the constrain method using a range of (-1, 1)
	@Test
	public void constrain_ValueBelowUpperBound_ReturnsInputValue() {
		assertEquals("Contrain with value of 0.9 should be 0.9", 0.9, testRange2.constrain(0.9), .000000001d);
	}
	
	//this test covers the between bounds case for value in the constrain method using a range of (-1, 1)
	@Test
	public void constrain_ValueBetweenBounds_ReturnsInputValue() {
		assertEquals("Contrain with value of 0 should be 0", 0, testRange2.constrain(0), .000000001d);
	}
	
	//this test covers the above lower bound case for value in the constrain method using a range of (-1, 1)
	@Test
	public void constrain_ValueAboveLowerBound_ReturnsInputValue() {
		assertEquals("Contrain with value of -0.9 should be -0.9", -0.9, testRange2.constrain(-0.9), .000000001d);
	}
	
	//this test covers the lower bound case for value in the constrain method using a range of (-1, 1)
	@Test
	public void constrain_ValueLowerBound_ReturnsLowerBound() {
		assertEquals("Contrain with value of -1 should be -1", -1, testRange2.constrain(-1), .000000001d);
	}
	
	//this test covers the below lower bound case for value in the constrain method using a range of (-1, 1)
	@Test
	public void constrain_ValueBelowLowerBound_ReturnsLowerBound() {
		assertEquals("Contrain with value of -1.1 should be -1", -1, testRange2.constrain(-1.1), .000000001d);
	}
	
//	Tests for getLength
	
	//this test covers the negative range values case for the getLength method with a range of (-10,-5)
	@Test
	public void getLength_NegativeRange_ReturnsLength() {
		assertEquals("Length should be 5", 5, testRange1.getLength(), .000000001d);
	}
	
	//this test covers the mixed range values case for the getLength method with a range of (-5,5)
	@Test
	public void getLength_MixedRange_ReturnsLength() {
		assertEquals("Length should be 10", 10, testRange3.getLength(), .000000001d);
	}
	
	//this test covers the positive range values length case for the getLength method with a range of (5,7)
	@Test
	public void getLength_PositiveRange_ReturnsLength() {
		assertEquals("Length should be 2", 2, testRange4.getLength(), .000000001d);
	}
	
//	Tests for getLowerBound
	
	//this test covers the negative range values case for the getLowerBound method with a range of (-10,-5)
	@Test
	public void getLowerBound_NegativeRange_LowerBound() {
		assertEquals("Lower Bound should be -10", -10, testRange1.getLowerBound(), .000000001d);
	}
	
	//this test covers the mixed range values case for the getLowerBound method with a range of (-5,5)
	@Test
	public void getLowerBound_MixedRange_LowerBound() {
		assertEquals("Lower Bound should be -5", -5, testRange3.getLowerBound(), .000000001d);
	}
	
	//this test covers the positive range values case for the getLowerBound method with a range of (5,7)
	@Test
	public void getLowerBound_PositiveRange_LowerBound() {
		assertEquals("Lower Bound should be 5", 5, testRange4.getLowerBound(), .000000001d);
	}
	
//	Tests for getUpperBound
	
	
	//this test covers the negative range values case for the getUpperBound method with a range of (-10, -5)
	@Test
	public void getUpperBound_NegativeRange_UpperBound() {
		assertEquals("Upper Bound should be -5", -5, testRange1.getUpperBound(), .000000001d);
	}
	
	//this test covers the mixed range values case for the getUpperBound method with a range of (-5,5)
	@Test
	public void getUpperBound_MixedRange_UpperBound() {
		assertEquals("Upper Bound should be 5", 5, testRange3.getUpperBound(), .000000001d);
	}
	
	//this test covers the positive range values case for the getUpperBound method with a range of (5,7)
	@Test
	public void getUpperBound_PositiveRange_UpperBound() {
		assertEquals("Upper Bound should be 7", 7, testRange4.getUpperBound(), .000000001d);
	}
	
//	Tests for contain
	
	//this test covers the above upper bound case for value in the contains method using a range of (-10,-5)
	@Test
	public void contains_ValueAboveUpperBound_ReturnsFalse() {
		assertFalse("Contains with value of -4.9 should be False", testRange1.contains(-4.9));
	}
	
	//this test covers the upper bound case for value in the contains method using a range of (-10,-5)
	@Test
	public void contains_ValueUpperBound_ReturnsTrue() {
		assertTrue("Contains with value of -5 should be True", testRange1.contains(-5));
	}
	
	//this test covers the below upper bound case for value in the contains method using a range of (-10,-5)
	@Test
	public void contains_ValueBelowUpperBound_ReturnsTrue() {
		assertTrue("Contains with value of -5.1 should be True", testRange1.contains(-5.1));
	}
	
	//this test covers the between bounds case for value in the contains method using a range of (-10,-5)
	@Test
	public void contains_ValueBetweenBounds_ReturnsTrue() {
		assertTrue("Contains with value of -7.5 should be True", testRange1.contains(-7.5));
	}
	
	//this test covers the above lower bound case for value in the contains method using a range of (-10,-5)
	@Test
	public void contains_ValueAboveLowerBound_ReturnsTrue() {
		assertTrue("Contains with value of -9.9 should be True", testRange1.contains(-9.9));
	}
	
	//this test covers the lower bound case for value in the contains method using a range of (-10,-5)
	@Test
	public void contains_ValueLowerBound_ReturnsTrue() {
		assertTrue("Contains with value of -10 should be True", testRange1.contains(-10));
	}
	
	//this test covers the below lower bound case for value in the contains method using a range of (-10,-5)
	@Test
	public void contains_ValueBelowLowerBound_ReturnsFalse() {
		assertFalse("Contains with value of -10.1 should be False", testRange1.contains(-10.1));
	}
	
	
	
}
