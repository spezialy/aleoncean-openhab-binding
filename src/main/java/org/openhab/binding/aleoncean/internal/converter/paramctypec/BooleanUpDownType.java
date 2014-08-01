/*
 * Copyright (c) 2014 aleon GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Markus Rathgeb - initial API and implementation and/or initial documentation
 */
package org.openhab.binding.aleoncean.internal.converter.paramctypec;

import org.openhab.binding.aleoncean.internal.converter.ParameterClassTypeClassConverter;
import org.openhab.binding.aleoncean.internal.devices.ItemInfo;
import org.openhab.core.events.EventPublisher;
import org.openhab.core.library.types.UpDownType;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;

/**
 *
 * @author Markus Rathgeb <maggu2810@gmail.com>
 */
public class BooleanUpDownType extends ParameterClassTypeClassConverter {

    public static final Class<?> PARAMETER_CLASS = Boolean.class;
    public static final Class<? extends State> STATE_TYPE_CLASS = UpDownType.class;
    public static final Class<? extends Command> COMMAND_TYPE_CLASS = UpDownType.class;

    private UpDownType booleanToUpDownType(final Boolean i) {
        return i ? UpDownType.UP : UpDownType.DOWN;
    }

    private Boolean upDownTypeToBoolean(final UpDownType i) {
        return i.equals(UpDownType.UP);
    }

    @Override
    public void commandFromOpenHAB(EventPublisher eventPublisher, String itemName, ItemInfo itemInfo, Command command) {
        assert COMMAND_TYPE_CLASS.isAssignableFrom(command.getClass());
        setByParameter(itemInfo.getDevice(), itemInfo.getParameter(), upDownTypeToBoolean((UpDownType) command));
    }

    @Override
    public void stateFromOpenHAB(EventPublisher eventPublisher, String itemName, ItemInfo itemInfo, State state) {
        assert STATE_TYPE_CLASS.isAssignableFrom(state.getClass());
        setByParameter(itemInfo.getDevice(), itemInfo.getParameter(), upDownTypeToBoolean((UpDownType) state));
    }

    @Override
    public void parameterFromDevice(EventPublisher eventPublisher, String itemName, ItemInfo itemInfo, Object value) {
        assert PARAMETER_CLASS.isAssignableFrom(value.getClass());
        postCommand(eventPublisher, itemName, booleanToUpDownType((Boolean) value));
    }

}
