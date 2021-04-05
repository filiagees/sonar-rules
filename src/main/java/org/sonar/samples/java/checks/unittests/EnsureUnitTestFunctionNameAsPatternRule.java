/*
 * SonarQube Java Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.samples.java.checks.unittests;

import static org.sonar.samples.java.checks.PrinterVisitor.tokensPositionsRange;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.samples.java.checks.PrinterVisitor;

/**
 * Ensure that functions name used in unit-test agreed with the regex pattern.
 */
@Rule (key = "EnsureUnitTestFunctionNameAsPattern", tags =
{
        "junit",
        "tests"
})
public class EnsureUnitTestFunctionNameAsPatternRule extends BaseTreeVisitor implements JavaFileScanner
{
    private JavaFileScannerContext context;
    private final String EXPECTED_UT_METHOD_NAME_PATTERN = "[a-zA-Z]*_with[a-zA-Z]*_should[A-Z][a-zA-Z]*";

    @Override
    public void scanFile(JavaFileScannerContext context)
    {
        this.context = context;

        scan(context.getTree());

        // For debugging purpose, you can print out the entire AST of the analyzed file
        System.out.println("\n" + PrinterVisitor.print(context.getTree()));
    }

    /**
     * Overriding the visitor method to implement the logic of the rule.
     * @param tree AST of the visited method.
     */
    @Override
    public void visitMethod(MethodTree tree)
    {
        String methodSimpleName = tree.simpleName().name();
        System.out.printf(" -- %20s %n", methodSimpleName);
        //        tree.simpleName().annotations().forEach(annotationTree -> System.out.printf("    `- " + annotationTree.atToken().text()));

        long testAnnotationCount = tree.modifiers().annotations().stream()
                .filter(annotationTree -> annotationTree.annotationType().toString().equalsIgnoreCase("Test"))
                .count();

        if (testAnnotationCount == 1 && !methodSimpleName.matches(EXPECTED_UT_METHOD_NAME_PATTERN))
        {
//            System.out.println(" -- tree.symbol().declaration() = " + tokensPositionsRange(tree.symbol().declaration()));
            System.out.println(" -- tree.simpleName() = " + tokensPositionsRange(tree.simpleName()));


            context.reportIssue(this, tree.simpleName(), "Method in UT doesn't follow expected name pattern.");
        }

        // The call to the super implementation allows to continue the visit of the AST.
        // Be careful to always call this method to visit every node of the tree.
        super.visitMethod(tree);

        // All the code located after the call to the overridden method is executed when leaving the node
    }
}

//        tree.modifiers().annotations().stream()
//                .forEach(annotationTree ->
//                        System.out.println("   `- modifiers().annotations() = "+annotationTree.annotationType().toString()));
//
//        for (AnnotationTree annotation : tree.simpleName().annotations())
//        {
//            System.out.printf("   `- annotation.atToken.text = %s", annotation.atToken().text());
//        }
//
//        List<SymbolMetadata.AnnotationValue> annotationValues = tree.symbol().metadata().valuesForAnnotation("org.junit.Test");
//        String annotationName = "";
//        String annotationValue = "";
//        if (annotationValues != null && !annotationValues.isEmpty())
//        {
//            annotationName = annotationValues.get(0).name();
//            annotationValue = (String) annotationValues.get(0).value();
//        }
//        System.out.println("   `- [org.junit.Test].annotationValues.[0].name = " + annotationName + ":" + annotationValue);
