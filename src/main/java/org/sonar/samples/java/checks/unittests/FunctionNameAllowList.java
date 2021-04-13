package org.sonar.samples.java.checks.unittests;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class FunctionNameAllowList
{
    static boolean isAllowedFunctionName(String functionName)
    {
        return ALLOWED_FUNCTION_NAMES.contains(functionName);
    }

    private static final List<String> ALLOWED_FUNCTION_NAMES = ImmutableList.of(
            "");
}
