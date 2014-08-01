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
import org.openhab.core.library.types.OnOffType;

/**
 *
 * @author Markus Rathgeb <maggu2810@gmail.com>
 */
public class RockerSwitchActionOnOffTypePressedUpDown extends RockerSwitchActionOnOffType {

    public static final String CONV_PARAM = "PressedUpDown";

    @Override
    protected RockerSwitchAction onOffTypeToRockerSwitchAction(final OnOffType value) {
        return value.equals(OnOffType.ON) ? RockerSwitchAction.DIM_UP_PRESSED : RockerSwitchAction.DIM_DOWN_PRESSED;
    }

    @Override
    protected OnOffType rockerSwitchActionToOnOffType(final RockerSwitchAction value) throws NoValueException {
        switch (value) {
            case DIM_UP_PRESSED:
                return OnOffType.ON;
            case DIM_DOWN_PRESSED:
                return OnOffType.OFF;
            default:
                throw new NoValueException();
        }
    }

}
