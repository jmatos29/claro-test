package com.claro.core;

import com.github.javaparser.ast.CompilationUnit;

import java.util.ArrayList;
import java.util.List;

public class DescriptionUtil {

    public static String toString(ClassDescription description) {
        DescriptorStringBuilder builder = new DescriptorStringBuilder();
        description.getFields().stream()
                .forEach((field) -> builder.appendFieldDescription(field.getName(), field.getAccessModifier(), field.getValueType()));

        description.getMethod().stream()
                .forEach((method) -> builder.appendMethodDescription(
                        method.getName(), method.getAccessModifier(), method.getReturnType(), method.getArguments()));

        return builder.toString();
    }

    public static void print(ClassDescription classDescription) {
        System.out.println(toString(classDescription));
    }

    public static String getDeclaredClassName(CompilationUnit unit) {
        ClassNameVisitor visitor = new ClassNameVisitor();
        List<String> classNames = new ArrayList<>();

        visitor.visit(unit, classNames);
        return classNames.get(classNames.size() - 1); // if class has child class then get the last "parent"
    }

}
