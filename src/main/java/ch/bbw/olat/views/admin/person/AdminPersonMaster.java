package ch.bbw.olat.views.admin.person;


import ch.bbw.olat.data.entity.OlatPersonEntity;
import ch.bbw.olat.data.repository.IOlatPersonRepository;
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

@PageTitle("Admin | Person Master")
@Route(value = "admin/person-master", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class AdminPersonMaster extends AMaster<OlatPersonEntity, IOlatPersonRepository, AdminPersonMasterForm> {

    public AdminPersonMaster(OlatDataService dataService) {
        super(dataService);
    }

    @Override
    protected OlatPersonEntity createEntity() {
        return new OlatPersonEntity();
    }

    @Override
    protected AdminPersonMasterForm instantiateForm(OlatDataService dataService) {
        return new AdminPersonMasterForm(dataService, this);
    }

    @Override
    protected Grid<OlatPersonEntity> instantiateGrid() {
        return new Grid<>(OlatPersonEntity.class, true);
    }

    @Override
    protected void configureGrid() {
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.asSingleSelect().addValueChangeListener((HasValue.ValueChangeListener<AbstractField.ComponentValueChangeEvent<Grid<OlatPersonEntity>, OlatPersonEntity>>) event -> select(event.getValue()));
    }

    @Override
    protected void configureGridColumns() {
        grid.setSizeFull();
    }

    @Override
    protected List<OlatPersonEntity> gatherData() {
        return dataService.getOlatPersonService().getAll();
    }

    @Override
    protected List<OlatPersonEntity> filter() {
        return dataService.getOlatPersonService().filterAll(filterText.getValue());
    }
}
