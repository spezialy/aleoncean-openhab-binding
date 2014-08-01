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
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;

/**
 *
 * @author Markus Rathgeb <maggu2810@gmail.com>
 */
public abstract class RockerSwitchActionOnOffType extends ParameterClassTypeClassConverter {

    public static final Class<?> PARAMETER_CLASS = RockerSwitchAction.class;
    public static final Class<? extends State> STATE_TYPE_CLASS = OnOffType.class;
    public static final Class<? extends Command> COMMAND_TYPE_CLASS = OnOffType.class;

    protected abstract RockerSwitchAction onOffTypeToRockerSwitchAction(final OnOffType value) throws NoValueException;

    protected abstract OnOffType rockerSwitchActionToOnOffType(final RockerSwitchAction value) throws NoValueException;

    @Override
    public void commandFromOpenHAB(EventPublisher eventPublisher, String itemName, ItemInfo itemInfo, Command command) {
        assert COMMAND_TYPE_CLASS.isAssignableFrom(command.getClass());
        try {
            final RockerSwitchAction action = onOffTypeToRockerSwitchAction((OnOffType) command);
            setByParameter(itemInfo.getDevice(), itemInfo.getParameter(), action);
        } catch (NoValueException ex) {
        }
    }

    @Override
    public void stateFromOpenHAB(EventPublisher eventPublisher, String itemName, ItemInfo itemInfo, State state) {
        assert STATE_TYPE_CLASS.isAssignableFrom(state.getClass());
        try {
            final RockerSwitchAction action = onOffTypeToRockerSwitchAction((OnOffType) state);
            setByParameter(itemInfo.getDevice(), itemInfo.getParameter(), action);
        } catch (NoValueException ex) {
        }
    }

    @Override
    public void parameterFromDevice(EventPublisher eventPublisher, String itemName, ItemInfo itemInfo, Object value) {
        assert PARAMETER_CLASS.isAssignableFrom(value.getClass());
        try {
            final OnOffType type = rockerSwitchActionToOnOffType((RockerSwitchAction) value);
            postCommand(eventPublisher, itemName, type);
        } catch (NoValueException ex) {
        }
    }

}
