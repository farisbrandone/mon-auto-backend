package com.example.monauto.service;

import com.example.monauto.entity.Role;

import java.util.Collection;

public interface IRoleService {
    Collection<Role> addRole(Collection<String> roles);
}
