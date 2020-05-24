package fr.axa.editique.users.service.impl;

import fr.axa.editique.users.repository.roles.RoleRepository;
import fr.axa.editique.users.service.api.IService;
import fr.axa.editique.users.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements IService,RoleService {


    private RoleRepository rolesRepository;


    @Autowired
    public RoleServiceImpl(RoleRepository rolesRepository) {
        setRolesRepository(rolesRepository);
    }


    public RoleRepository getRolesRepository() {
        return rolesRepository;
    }


    public void setRolesRepository(RoleRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

   /*
    public Map<String, List<MessageI18n>> validate(Roles roles) {
        Map<String, List<MessageI18n>> messages = new java.util.HashMap<String, List<MessageI18n>>();
        // TODO: IMPLEMENT HERE THE VALIDATION OF YOUR ENTITY
        return messages;
    }


    @Transactional
    public void delete(Roles roles) {
        getRolesRepository().delete(roles);
    }


    @Transactional
    public List<Roles> save(Iterable<Roles> entities) {
        return getRolesRepository().save(entities);
    }


    @Transactional
    public void delete(Iterable<Long> ids) {
        List<Roles> toDelete = getRolesRepository().findAll(ids);
        getRolesRepository().deleteInBatch(toDelete);
    }


    @Transactional
    public Roles save(Roles entity) {
        return getRolesRepository().save(entity);
    }


    public Roles findOne(Long id) {
        return getRolesRepository().findOne(id);
    }


    public Roles findOneForUpdate(Long id) {
        return getRolesRepository().findOneDetached(id);
    }


    public List<Roles> findAll(Iterable<Long> ids) {
        return getRolesRepository().findAll(ids);
    }


    public List<Roles> findAll() {
        return getRolesRepository().findAll();
    }


    public long count() {
        return getRolesRepository().count();
    }


    public Page<Roles> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getRolesRepository().findAll(globalSearch, pageable);
    }


    public Page<Roles> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getRolesRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }


    public Class<Roles> getEntityType() {
        return Roles.class;
    }


    public Class<Long> getIdType() {
        return Long.class;
    }
    */
}
