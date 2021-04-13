# sonar-rules
sonar-rules

This example demonstrates how to write **Custom Rules** for SonarJava.

Remember: for proper validation of annotations, you may need to place extra a jar/zip file and config your JavaCheckVerifier.
For more details, see `CheckVerifier.withClassPath()`.

Also, only expected annotation parameters (`SymbolMetadata.AnnotationValue`) will be returned.
For example, `org.junit.Test` can receive a `expected` parameter, but your test-file contains a `@Test (someOtherThing = "someValue")`, the `someOtherThing` will not be returned.

### Credits
- This repo is a copy of source presented in SonarSource's [sonar-custom-rules-examples](https://github.com/SonarSource/sonar-custom-rules-examples)
    - it was not git-forked because many files has been deleted and there is no need to tracking them.