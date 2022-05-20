package ch.bbw.olat.views.admin.user;


import ch.bbw.olat.data.entity.OlatUserEntity;
import ch.bbw.olat.data.repository.IOlatUserRepository;
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
import java.util.List;

@PageTitle("Admin | User Master")
@Route(value = "admin/user-master", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class AdminUserMaster extends AMaster<OlatUserEntity, IOlatUserRepository, AdminUserMasterForm> {

    public AdminUserMaster(OlatDataService dataService) {
        super(dataService);
    }

    @Override
    protected OlatUserEntity createEntity() {
        return new OlatUserEntity();
    }

    @Override
    protected AdminUserMasterForm instantiateForm(OlatDataService dataService) {
        return new AdminUserMasterForm(dataService, this);
    }

    @Override
    protected Grid<OlatUserEntity> instantiateGrid() {
        return new Grid<>(OlatUserEntity.class, false);
    }

    @Override
    protected void configureGrid() {
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.asSingleSelect().addValueChangeListener((HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<Grid<OlatUserEntity>, OlatUserEntity>>) event -> select(event.getValue()));
    }

    @Override
    protected void configureGridColumns() {
        grid.setColumns("username", "roles", "inUse", "fileSystemPrefix", "id");
        grid.setSizeFull();
    }

    @Override
    protected List<OlatUserEntity> gatherData() {
        return dataService.getOlatUserService().getAll();
    }

    @Override
    protected List<OlatUserEntity> filter() {
        return dataService.getOlatUserService().filterAll(filterText.getValue());
    }
}
