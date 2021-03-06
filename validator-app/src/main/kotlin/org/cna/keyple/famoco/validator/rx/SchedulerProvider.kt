/********************************************************************************
 * Copyright (c) 2020 Calypso Networks Association https://www.calypsonet-asso.org/
 *
 * See the NOTICE file(s) distributed with this work for additional information regarding copyright
 * ownership.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which is available at http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 ********************************************************************************/
package org.cna.keyple.famoco.validator.rx

import io.reactivex.Scheduler
import javax.inject.Inject

class SchedulerProvider @Inject constructor(
    private val backScheduler: Scheduler,
    private val foreScheduler: Scheduler
) {

    /**
     * IO thread pool scheduler
     */
    fun io(): Scheduler {
        return backScheduler
    }

    /**
     * Main Thread scheduler
     */
    fun ui(): Scheduler {
        return foreScheduler
    }
}
