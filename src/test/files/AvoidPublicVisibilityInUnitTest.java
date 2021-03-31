/**
 * This file is the sample code against we run our unit test.
 * It is placed src/test/files in order to not be part of the maven compilation.
 */
class AvoidPublicVisibilityInUnitTest {

  private static final String SOME_PRIVATE_STATIC_FINAL_CONST = "some value";
  private static String SOME_PRIVATE_STATIC_CONST = "some value";
  private final String SOME_PRIVATE_FINAL_CONST = "some value";
  private String SOME_PRIVATE_CONST = "some value";
  public String SOME_PUBLIC_CONST = "some value"; // Noncompliant {{Avoid 'public' visibility in a unit-test file}}

  public void methodWithMYCOMPANY() {
  }

  public void methodWithMyCompany() {
  }

  public void methodWithMyOtherCompany() {
  }

}
