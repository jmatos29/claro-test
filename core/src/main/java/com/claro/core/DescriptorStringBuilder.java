package com.claro.core;

import java.util.Map;
import java.util.stream.Collectors;

public class DescriptorStringBuilder {

    private static String startLine =
            "┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓\n";
    private static String header =
            "┃NAME                        ┃VAR           ┃SCOPE             ┃SIGNATURE                                                     ┃\n";

    private static String rowBody =
            "┃  %-25s ┃ %-12s ┃ %-16s ┃ %-60s ┃\n";

    private static String continuationLine =
            "┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━╋━━━━━━━━━━━━━━╋━━━━━━━━━━━━━━━━━━╋━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫\n";

    private static String endLine =
            "┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛\n";

    private StringBuilder result = new StringBuilder();


    public DescriptorStringBuilder appendFieldDescription(String name, String scope, String type) {
        result.append(String.format(rowBody, name, 'A', scope, "TYPE: " + type));
        result.append(continuationLine);
        return this;
    }

    public DescriptorStringBuilder appendMethodDescription(String name, String scope, String methodReturnType, Map<String, String> params) {
        String methodArgs = params.entrySet().stream().map( entry -> entry.getKey() + " " + entry.getValue()).collect(Collectors.joining());
        result.append(String.format(rowBody, name, 'M', scope, "RTYPE: " + methodReturnType + " PARAMS: " + (!methodArgs.isEmpty()? methodArgs : "none")));
        result.append(continuationLine);
        return this;
    }

    @Override
    public String toString() {
        result.insert(0, startLine + header + continuationLine);
        result.append(endLine);
        return result.toString();
    }
}