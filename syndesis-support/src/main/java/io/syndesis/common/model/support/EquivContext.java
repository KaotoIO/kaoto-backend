/*
 * Copyright (C) 2016 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.syndesis.common.model.support;

import java.util.Optional;

class EquivContext {

    private static final int TRUNCATE_LENGTH = 15;

    private final String name;

    private final String type;

    private String failedProperty;

    private String a;

    private String b;

    EquivContext(String name, Class<?> klazz) {
        this.name = name;
        this.type = klazz.getSimpleName().replace("Immutable", "");
    }

    private static String truncate(String fullText, String diffText,
                                   int diffIndex) {
        StringBuilder truncated = new StringBuilder();

        //
        // Adds a portion of the full text at the start of the value
        // then include '...'
        //
        if (diffIndex > 0 && diffIndex <= TRUNCATE_LENGTH) {
            truncated.append(fullText, 0, diffIndex);
        } else if (diffIndex > TRUNCATE_LENGTH) {
            truncated
                    .append(fullText, 0, TRUNCATE_LENGTH)
                    .append(" ").append("---").append(" ");
        }

        if (diffText.length() > 70) {
            truncated.append(diffText, 0, 70).append(" ").append(
                    "---");
        } else {
            truncated.append(diffText);
        }

        return truncated.toString();
    }

    private static String toString(Object value) {
        if (value instanceof Optional) {
            Optional<?> ov = (Optional<?>) value;
            Object v = ov.orElse(null);
            if (v == null) {
                return "";
            } else {
                return v.toString();
            }
        }

        return value.toString();
    }

    public String id() {
        StringBuilder builder = new StringBuilder();
        builder.append(type);

        if (name != null) {
            builder
                    .append("{")
                    .append("\"")
                    .append(name)
                    .append("\"")
                    .append("}");
        }

        return builder.toString();
    }

    public boolean hasFailed() {
        return this.failedProperty != null;
    }

    public String getFailed() {
        return "'" + this.failedProperty + "' is different" + "\n"
                + "  " + "=> " + "\"" + this.a + "\"" + "\n"
                + "  " + "=> " + "\"" + this.b + "\"" + "\n";
    }

    public void setFail(String property, Object a, Object b) {
        this.failedProperty = property;
        String aStr = toString(a);
        String bStr = toString(b);

        int diffPos = 0;
        if (diffPos < 0) {
            this.a = aStr;
            this.b = bStr;
            return;
        }

        String aDiff = aStr.substring(diffPos);
        String bDiff = bStr.substring(diffPos);

        this.a = truncate(aStr, aDiff, diffPos);
        this.b = truncate(bStr, bDiff, diffPos);
    }
}
