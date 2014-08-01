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
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;

/**
 *
 * @author Markus Rathgeb <maggu2810@gmail.com>
 */
public class DoubleDecimalType extends ParameterClassTypeClassConverter {

    public static final Class<?> PARAMETER_CLASS = Double.class;
    public static final Class<? extends State> STATE_TYPE_CLASS = DecimalType.class;
    public static final Class<? extends Command> COMMAND_TYPE_CLASS = DecimalType.class;

    private DecimalType doubleToDecimalType(final Double i) {
        return new DecimalType(i);
    }

    private Double decimalTypeToDouble(final DecimalType i) {
        return i.doubleValue();
    }

    @Override
    public void commandFromOpenHAB(EventPublisher eventPublisher, String itemName, ItemInfo itemInfo, Command command) {
        assert COMMAND_TYPE_CLASS.isAssignableFrom(command.getClass());
        setByParameter(itemInfo.getDevice(), itemInfo.getParameter(), decimalTypeToDouble((DecimalType) command));
    }

    @Override
    public void stateFromOpenHAB(EventPublisher eventPublisher, String itemName, ItemInfo itemInfo, State state) {
        assert STATE_TYPE_CLASS.isAssignableFrom(state.getClass());
        setByParameter(itemInfo.getDevice(), itemInfo.getParameter(), decimalTypeToDouble((DecimalType) state));
    }

    @Override
    public void parameterFromDevice(EventPublisher eventPublisher, String itemName, ItemInfo itemInfo, Object value) {
        assert PARAMETER_CLASS.isAssignableFrom(value.getClass());
        postCommand(eventPublisher, itemName, doubleToDecimalType((Double) value));
    }
}
