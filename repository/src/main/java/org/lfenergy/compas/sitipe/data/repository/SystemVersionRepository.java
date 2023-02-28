// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.data.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.lfenergy.compas.sitipe.data.entity.SystemVersion;

import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.util.List;

@ApplicationScoped
public class SystemVersionRepository implements PanacheRepository<SystemVersion> {

    private final DataSource dataSource;

    public SystemVersionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<SystemVersion> findByVersion(final String version) {
        return this.list("version", version);
    }
}
