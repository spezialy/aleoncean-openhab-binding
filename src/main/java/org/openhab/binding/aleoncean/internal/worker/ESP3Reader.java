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
package org.openhab.binding.aleoncean.internal.worker;

import eu.aleon.aleoncean.packet.ESP3Packet;
import eu.aleon.aleoncean.rxtx.ESP3Connector;
import eu.aleon.aleoncean.rxtx.ReaderShutdownException;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Markus Rathgeb <maggu2810@gmail.com>
 */
public class ESP3Reader implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ESP3Reader.class);

    private final ESP3Connector connector;
    private final WorkerQueue workerQueue;

    public ESP3Reader(final ESP3Connector connector, final WorkerQueue workerQueue) {
        this.connector = connector;
        this.workerQueue = workerQueue;
    }

    @Override
    public void run() {
        while (true) {
            ESP3Packet packet;

            try {
                packet = connector.read(1000, TimeUnit.DAYS);
            } catch (ReaderShutdownException ex) {
                // Received indication that the read end was shut down.
                return;
            }

            if (packet == null) {
                LOGGER.warn("Received a null package.");
            } else {
                workerQueue.add(new WorkerItemPacket(packet));
            }
        }
    }

}
