import org.junit.Test;

/**
 * This file is the sample code against we run our unit test.
 * It is placed src/test/files in order to not be part of the maven compilation.
 */
class EnsureUnitTestFunctionNameAsPattern {

    @Test
    public void badMethodNameWithoutParam() { // Noncompliant
    }

    @Test
    public void badMethodNameWithParam(String paramStr) { // Noncompliant
    }

    @Test (expected = "expectedValue!!")
    public void badMethodNameWithAnnotationParam() { // Noncompliant
    }

    @Test
    public void methodName_withoutParam_shouldBeAccepted() {
    }

    @Test
    public void methodName_whenParamValid_shouldBeAccepted() {
    }

    @Test
    public void methodName_withParam_shouldBeAccepted(String paramStr) {
    }

    @Test (expected = "expectedValue!!")
    public void methodName_withAnnotationParam_shouldBeAccepted() {
    }

    private void assertSomethingHere(){
        // non-@Test functions can have any name pattern
    }

    private bool anotherUsefulFunction(){
        // non-@Test functions can have any name pattern
        return true;
    }
}
