package com.claro.core;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.nodeTypes.modifiers.NodeWithAccessModifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassDescriptor {

    public ClassDescription describe(String source) {

        CompilationUnit compilationUnit = StaticJavaParser.parse(source);
        String className = DescriptionUtil.getDeclaredClassName(compilationUnit);
        ClassOrInterfaceDeclaration classDeclaration = compilationUnit.getClassByName(className).orElseThrow(IllegalArgumentException::new);

        List<ClassDescription.FieldDescription> fieldDescriptions = new ArrayList<>();
        List<ClassDescription.MethodDescription> methodDescriptions = new ArrayList<>();



        classDeclaration.getFields().stream().forEach(field ->
                field.getVariables().stream().forEach(e ->
                        fieldDescriptions.add(new ClassDescription.FieldDescription(
                                e.getNameAsString(), getAccessModifier(field), e.getTypeAsString()))
                )
        );

        classDeclaration.getMethods().stream().forEach(method -> {
            Map<String, String> methodArgs = method.getParameters().stream()
                    .collect(Collectors.toMap((p) -> p.getNameAsString(), (p) -> p.getTypeAsString()));
            methodDescriptions.add(new ClassDescription.MethodDescription(
                    method.getNameAsString(), getAccessModifier(method), method.getTypeAsString(), methodArgs));
        });

        return new ClassDescription(fieldDescriptions, methodDescriptions);
    }

    private static String getAccessModifier(NodeWithAccessModifiers field) {
        if (field.isPublic()) {
            return "public";
        }
        if (field.isProtected()) {
            return "protected";
        }
        if (field.isPrivate()) {
            return "private";
        }
        return "package-private";
    }
}
