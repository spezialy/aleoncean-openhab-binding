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

import eu.aleon.aleoncean.values.RockerSwitchAction;
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
public class RockerSwitchActionDecimalType extends ParameterClassTypeClassConverter {

    public static final Class<?> PARAMETER_CLASS = RockerSwitchAction.class;
    public static final Class<? extends State> STATE_TYPE_CLASS = DecimalType.class;
    public static final Class<? extends Command> COMMAND_TYPE_CLASS = DecimalType.class;

    private static final int DEC_RSA_DIM_UP_PRESSED = 1;
    private static final int DEC_RSA_DIM_UP_RELEASED = 2;
    private static final int DEC_RSA_DIM_DOWN_PRESSED = 3;
    private static final int DEC_RSA_DIM_DOWN_RELEASED = 4;

    public static DecimalType rockerSwitchActionToDecimalType(final RockerSwitchAction action) throws NoValueException {
        switch (action) {
            case DIM_UP_PRESSED:
                return new DecimalType(DEC_RSA_DIM_UP_PRESSED);
            case DIM_UP_RELEASED:
                return new DecimalType(DEC_RSA_DIM_UP_RELEASED);
            case DIM_DOWN_PRESSED:
                return new DecimalType(DEC_RSA_DIM_DOWN_PRESSED);
            case DIM_DOWN_RELEASED:
                return new DecimalType(DEC_RSA_DIM_DOWN_RELEASED);
            default:
                throw new NoValueException();
        }
    }

    public static RockerSwitchAction decimalTypeToRockerSwitchAction(final DecimalType value) throws NoValueException {
        switch (value.intValue()) {
            case DEC_RSA_DIM_UP_PRESSED:
                return RockerSwitchAction.DIM_UP_PRESSED;
            case DEC_RSA_DIM_UP_RELEASED:
                return RockerSwitchAction.DIM_UP_RELEASED;
            case DEC_RSA_DIM_DOWN_PRESSED:
                return RockerSwitchAction.DIM_DOWN_PRESSED;
            case DEC_RSA_DIM_DOWN_RELEASED:
                return RockerSwitchAction.DIM_DOWN_RELEASED;
            default:
                throw new NoValueException();
        }
    }

    @Override
    public void commandFromOpenHAB(EventPublisher eventPublisher, String itemName, ItemInfo itemInfo, Command command) {
        assert COMMAND_TYPE_CLASS.isAssignableFrom(command.getClass());
        try {
            final RockerSwitchAction action = decimalTypeToRockerSwitchAction((DecimalType) command);
            setByParameter(itemInfo.getDevice(), itemInfo.getParameter(), action);
        } catch (NoValueException ex) {
        }
    }

    @Override
    public void stateFromOpenHAB(EventPublisher eventPublisher, String itemName, ItemInfo itemInfo, State state) {
        assert STATE_TYPE_CLASS.isAssignableFrom(state.getClass());
        try {
            final RockerSwitchAction action = decimalTypeToRockerSwitchAction((DecimalType) state);
            setByParameter(itemInfo.getDevice(), itemInfo.getParameter(), action);
        } catch (NoValueException ex) {
        }
    }

    @Override
    public void parameterFromDevice(EventPublisher eventPublisher, String itemName, ItemInfo itemInfo, Object value) {
        assert PARAMETER_CLASS.isAssignableFrom(value.getClass());
        try {
            final DecimalType type = rockerSwitchActionToDecimalType((RockerSwitchAction) value);
            postCommand(eventPublisher, itemName, type);
        } catch (NoValueException ex) {
        }
    }

}
