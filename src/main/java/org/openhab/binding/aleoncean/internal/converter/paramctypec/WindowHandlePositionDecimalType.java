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

import eu.aleon.aleoncean.values.WindowHandlePosition;
import org.openhab.binding.aleoncean.internal.converter.NoValueException;
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
public class WindowHandlePositionDecimalType extends ParameterClassTypeClassConverter {

    public static final Class<?> PARAMETER_CLASS = WindowHandlePosition.class;
    public static final Class<? extends State> STATE_TYPE_CLASS = DecimalType.class;
    public static final Class<? extends Command> COMMAND_TYPE_CLASS = DecimalType.class;

    private static final int DEC_WINDOW_HANDLE_POS_UP = 4;
    private static final int DEC_WINDOW_HANDLE_POS_DOWN = 8;
    private static final int DEC_WINDOW_HANDLE_POS_LEFT_OR_RIGHT = 48;

    private DecimalType windowHandlePositionToDecimalType(final WindowHandlePosition i) throws NoValueException {
        switch (i) {
            case UP:
                return new DecimalType(DEC_WINDOW_HANDLE_POS_UP);
            case DOWN:
                return new DecimalType(DEC_WINDOW_HANDLE_POS_DOWN);
            case LEFT_OR_RIGHT:
                return new DecimalType(DEC_WINDOW_HANDLE_POS_LEFT_OR_RIGHT);
            default:
                throw new NoValueException();
        }
    }

    private WindowHandlePosition decimalTypeToWindowHandlePosition(final DecimalType i) throws NoValueException {
        switch (i.intValue()) {
            case DEC_WINDOW_HANDLE_POS_UP:
                return WindowHandlePosition.UP;
            case DEC_WINDOW_HANDLE_POS_DOWN:
                return WindowHandlePosition.DOWN;
            case DEC_WINDOW_HANDLE_POS_LEFT_OR_RIGHT:
                return WindowHandlePosition.LEFT_OR_RIGHT;
            default:
                throw new NoValueException();
        }
    }

    @Override
    public void commandFromOpenHAB(EventPublisher eventPublisher, String itemName, ItemInfo itemInfo, Command command) {
        assert COMMAND_TYPE_CLASS.isAssignableFrom(command.getClass());
        try {
            setByParameter(itemInfo.getDevice(), itemInfo.getParameter(), decimalTypeToWindowHandlePosition((DecimalType) command));
        } catch (NoValueException ex) {
        }
    }

    @Override
    public void stateFromOpenHAB(EventPublisher eventPublisher, String itemName, ItemInfo itemInfo, State state) {
        assert STATE_TYPE_CLASS.isAssignableFrom(state.getClass());
        try {
            setByParameter(itemInfo.getDevice(), itemInfo.getParameter(), decimalTypeToWindowHandlePosition((DecimalType) state));
        } catch (NoValueException ex) {
        }
    }

    @Override
    public void parameterFromDevice(EventPublisher eventPublisher, String itemName, ItemInfo itemInfo, Object value) {
        assert PARAMETER_CLASS.isAssignableFrom(value.getClass());
        try {
            postCommand(eventPublisher, itemName, windowHandlePositionToDecimalType((WindowHandlePosition) value));
        } catch (NoValueException ex) {
        }
    }

}
