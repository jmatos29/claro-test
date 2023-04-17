package com.claro.core;

import java.util.List;
import java.util.Map;

public class ClassDescription {


    private List<FieldDescription> fields;
    private List<MethodDescription> method;

    public ClassDescription(List<FieldDescription> fields, List<MethodDescription> method) {
        this.fields = fields;
        this.method = method;
    }

    public List<FieldDescription> getFields() {
        return fields;
    }

    public void setFields(List<FieldDescription> fields) {
        this.fields = fields;
    }

    public List<MethodDescription> getMethod() {
        return method;
    }

    public void setMethod(List<MethodDescription> method) {
        this.method = method;
    }

    static class MemberDescription {
        private String name;
        private String accessModifier;

        public MemberDescription(String name, String accessModifier) {
            this.name = name;
            this.accessModifier = accessModifier;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAccessModifier() {
            return accessModifier;
        }

        public void setAccessModifier(String accessModifier) {
            this.accessModifier = accessModifier;
        }
    }

    static class FieldDescription extends MemberDescription {
        private String valueType;

        public FieldDescription(String name, String accessModifier, String valueType) {
            super(name, accessModifier);
            this.valueType = valueType;
        }

        public String getValueType() {
            return valueType;
        }

        public void setValueType(String valueType) {
            this.valueType = valueType;
        }
    }
    static class MethodDescription extends MemberDescription {
        private String returnType;
        private Map<String, String> arguments;

        public MethodDescription(String name, String accessModifier, String returnType, Map<String, String> arguments) {
            super(name, accessModifier);
            this.returnType = returnType;
            this.arguments = arguments;
        }

        public Map<String, String> getArguments() {
            return arguments;
        }

        public void setArguments(Map<String, String> arguments) {
            this.arguments = arguments;
        }

        public String getReturnType() {
            return returnType;
        }

        public void setReturnType(String returnType) {
            this.returnType = returnType;
        }
    }
}
