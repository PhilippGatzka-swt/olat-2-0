package ch.bbw.olat.views.admin.group;

import ch.bbw.olat.data.entity.OlatGroupEntity;
import ch.bbw.olat.data.repository.IOlatGroupRepository;
import ch.bbw.olat.data.service.OlatDataService;
import ch.bbw.olat.views.MainLayout;
import ch.bbw.olat.views.admin.AMaster;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;
import java.util.Comparator;
import java.util.List;

@PageTitle("Admin | Group Master")
@Route(value = "admin/group-master", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class AdminGroupMaster extends AMaster<OlatGroupEntity, IOlatGroupRepository, AdminGroupMasterForm> {

    public AdminGroupMaster(OlatDataService dataService) {
        super(dataService);
    }

    @Override
    protected OlatGroupEntity createEntity() {
        return new OlatGroupEntity();
    }

    @Override
    protected AdminGroupMasterForm instantiateForm(OlatDataService dataService) {
        return new AdminGroupMasterForm(dataService, this);
    }

    @Override
    protected Grid<OlatGroupEntity> instantiateGrid() {
        return new Grid<>(OlatGroupEntity.class, true);
    }

    @Override
    protected void configureGrid() {
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.asSingleSelect().addValueChangeListener((HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<Grid<OlatGroupEntity>, OlatGroupEntity>>) event -> select(event.getValue()));
    }

    @Override
    protected void configureGridColumns() {
        grid.setSizeFull();
    }

    @Override
    protected List<OlatGroupEntity> gatherData() {
        return dataService.getOlatGroupService().getAll();
    }

    @Override
    protected List<OlatGroupEntity> filter() {
        return dataService.getOlatGroupService().filterAll(filterText.getValue());
    }
}
