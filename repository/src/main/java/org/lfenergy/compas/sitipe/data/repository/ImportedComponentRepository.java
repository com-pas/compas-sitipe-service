package org.lfenergy.compas.sitipe.data.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.lfenergy.compas.sitipe.data.entity.ImportedComponent;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.LockModeType;
import java.util.List;

@ApplicationScoped
public class ImportedComponentRepository implements PanacheRepositoryBase<ImportedComponent, Integer> {

    public List<ImportedComponent> getByAccessId(final String accessId) {
        return this.list("componentAccessId = ?1 AND type = 'IID'", accessId);
    }

    public ImportedComponent getById(final Integer id) {
        return this.findById(id, LockModeType.NONE);
    }
}