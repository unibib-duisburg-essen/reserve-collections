/**
 * Copyright (C) 2014 - 2016 Universitaet Duisburg-Essen (semapp|uni-due.de)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package unidue.rc.ui;


import org.apache.tapestry5.Field;

/**
 * An immutable copy of a Field. Handy for taking a copy of a Field in a row as a Loop iterates through them.
 *
 * @author Nils Verheyen
 * @since 20.09.13 14:53
 */
public class FieldCopy implements Field {
    private String clientId;
    private String controlName;
    private String label;
    private boolean disabled;
    private boolean required;

    public FieldCopy(Field field) {
        clientId = field.getClientId();
        controlName = field.getControlName();
        label = field.getLabel();
        disabled = field.isDisabled();
        required = field.isRequired();
    }

    @Override
    public String getControlName() {
        return controlName;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public boolean isDisabled() {
        return disabled;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public String getClientId() {
        return clientId;
    }
}
