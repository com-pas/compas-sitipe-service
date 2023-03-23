// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.helper;

import org.lfenergy.compas.sitipe.data.repository.BayTypicalRepository;
import org.lfenergy.compas.sitipe.data.repository.SystemVersionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class DatabaseCleaner {

    @Inject
    SystemVersionRepository systemVersionRepository;

    @Inject
    BayTypicalRepository bayTypicalRepository;

    @Transactional
    public void cleanUp() {
        bayTypicalRepository.deleteAll();
        systemVersionRepository.deleteAll();
    }
}