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
package org.sonar.samples.java.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.Modifier;
import org.sonar.plugins.java.api.tree.VariableTree;

@Rule (key = "AvoidPublicVisibilityInUT", tags =
{ "junit", "tests" })
public class AvoidPublicVisibilityInUTRule extends BaseTreeVisitor implements JavaFileScanner
{

    private static final String UNIT_TEST_FILE_SUFFIX = "Test.java";

    private JavaFileScannerContext context;

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
    public void visitVariable(VariableTree tree)
    {
        tree.modifiers().modifiers()
                .forEach(modifierTree -> {
                    if (modifierTree.modifier().equals(Modifier.PUBLIC))
                    {
                        context.reportIssue(this, tree, "Avoid 'public' visibility in a unit-test file");
                    }
                });

        // The call to the super implementation allows to continue the visit of the AST.
        // Be careful to always call this method to visit every node of the tree.
        super.visitVariable(tree);

        // All the code located after the call to the overridden method is executed when leaving the node
    }
}
