package org.sonar.samples.java.checks.unittests;

import org.junit.Test;
//import org.sonar.java.checks.verifier.FilesUtils;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class EnsureUnitTestFunctionNameAsPatternRuleCheckTest
{
    @Test
    public void detected()
    {
        String testJarsDirectory = "src/test/files/test-jars/";
        // Verifies that the check will raise the adequate issues with the expected message.
        // In the test file, lines which should raise an issue have been commented out
        // by using the following syntax: "// Noncompliant {{EXPECTED_MESSAGE}}"
        JavaCheckVerifier.newVerifier()
                .onFile("src/test/files/EnsureUnitTestFunctionNameAsPattern.java")
                .withCheck(new EnsureUnitTestFunctionNameAsPatternRule())
//                .withClassPath(FilesUtils.getClassPath(testJarsDirectory))
                .verifyIssues();
    }
}
