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
package org.openhab.binding.aleoncean.internal;

import org.openhab.binding.aleoncean.AleonceanBindingProvider;
import org.openhab.core.items.GroupItem;
import org.openhab.core.items.Item;
import org.openhab.model.item.binding.AbstractGenericBindingProvider;
import org.openhab.model.item.binding.BindingConfigParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is responsible for parsing the binding configuration.
 *
 * @author Markus Rathgeb <maggu2810@gmail.com>
 * @since 1.6.0
 */
public class AleonceanGenericBindingProvider extends AbstractGenericBindingProvider implements AleonceanBindingProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AleonceanGenericBindingProvider.class);

    @Override
    public String getBindingType() {
        return "aleoncean";
    }

    public AleonceanBindingConfig getBindingConfig(final String itemName) {
        return (AleonceanBindingConfig) bindingConfigs.get(itemName);
    }

    @Override
    public void validateItemType(Item item, String bindingConfig) throws BindingConfigParseException {
        LOGGER.debug("validateItemType({},{})", item, bindingConfig);
        //if (!(item instanceof SwitchItem || item instanceof DimmerItem)) {
        //    throw new BindingConfigParseException("item '" + item.getName()
        //            + "' is of type '" + item.getClass().getSimpleName()
        //            + "', only Switch- and DimmerItems are allowed - please check your *.items configuration");
        //}
    }

    @Override
    public void processBindingConfiguration(String context, Item item, String bindingConfig) throws BindingConfigParseException {
        super.processBindingConfiguration(context, item, bindingConfig);

        LOGGER.debug("processBindingConfiguration({},{},{})", context, item, bindingConfig);

        AleonceanBindingConfig config = new AleonceanBindingConfig(bindingConfig);
        if (item instanceof GroupItem) {
            config.setItemType(((GroupItem) item).getBaseItem().getClass());
        } else {
            config.setItemType(item.getClass());
        }
        config.setAcceptedDataTypes(item.getAcceptedDataTypes());
        config.setAcceptedCommandTypes(item.getAcceptedCommandTypes());

        addBindingConfig(item, config);
    }

}
