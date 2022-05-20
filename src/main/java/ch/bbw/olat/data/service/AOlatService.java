package ch.bbw.olat.data.service;

import ch.bbw.olat.data.entity.AOlatEntity;
import ch.bbw.olat.data.repository.IOlatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public abstract class AOlatService<Repository extends IOlatRepository<Entity>, Entity extends AOlatEntity> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    protected final Repository repository;

    public AOlatService(Repository repository) {
        this.repository = repository;
    }

    public void save(Entity entity) {
        logger.info("Saving entity: " + entity);
        repository.save(entity);
    }

    public Optional<Entity> get(long id) {
        return repository.findById(id);
    }

    public List<Entity> getAll() {
        return repository.findAll();
    }

    public Entity update(Entity entity) {
        logger.info("Update entity: " + entity);
        return repository.save(entity);
    }

    public void delete(long id) {
        logger.info("Deleting entity with id: " + id);
        repository.delete(get(id).orElseThrow());
    }

    public void delete(Entity entity) {
        delete(entity.getId());
    }

    public Repository getRepository() {
        return repository;
    }

    protected String createFileSystemPrefix(String string){
        return string.replaceAll("[^a-zA-Z\\d]","_").toLowerCase();
    }

    public boolean canBeDeleted(Entity entity){
        return !entity.getInUse();
    }

    public abstract String checkConsistency(Entity entity);

    protected String attributeNotUnique(String attrName){
        return "Error: Attribute '" + attrName + "' has to be unique!";
    }

    public abstract List<Entity> filterAll(String filter);

}
