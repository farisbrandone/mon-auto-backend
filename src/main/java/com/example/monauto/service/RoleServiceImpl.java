package com.example.monauto.service;

import com.example.monauto.dao.RoleRepository;
import com.example.monauto.entity.Role;
import com.example.monauto.enumFile.RoleUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public  Collection<Role>  addRole(Collection<String> roles) {
        Collection<Role> roleList = new ArrayList<>();
        roles.forEach(roleName -> {
            Role role = new Role();
            RoleUser roleUser= RoleUser.valueOf(roleName);
            role.setRoleName(roleUser);
       Role  myrole= roleRepository.save(role);
       roleList.add(myrole);

        });
        return roleList;
    }
}
