// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.data.table;

import org.eclipse.microprofile.config.ConfigProvider;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class SitipeFrameworkIdNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    private final String prefix;

    public SitipeFrameworkIdNamingStrategy() {
        this.prefix = ConfigProvider.getConfig().getValue("compas.sitipe.framework-id", String.class);
    }
    @Override
    public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnvironment) {
        if (identifier == null) {
            return null;
        }

        final String tableName = prefix != null ? prefix + "__" + identifier.getText() : identifier.getText();

        return Identifier.toIdentifier(tableName);
    }
}
