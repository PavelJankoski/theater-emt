package mk.ukim.finki.usermanagement.application.service.impl;

import mk.ukim.finki.usermanagement.application.service.RoleService;
import mk.ukim.finki.usermanagement.domain.enums.ERole;
import mk.ukim.finki.usermanagement.domain.exceptions.RoleNotFoundException;
import mk.ukim.finki.usermanagement.domain.model.Role;
import mk.ukim.finki.usermanagement.domain.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRolesByName(Set<String> roles) {
        Role ro = null;
        for(String r: roles) {
            if(r.toLowerCase().equals("user")) {
                ro = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(RoleNotFoundException::new);
                return ro;
            }
            else if(r.toLowerCase().equals("admin")){
                ro = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(RoleNotFoundException::new);
                return ro;
            }
            else{
                continue;
            }
        }
        if(ro==null) {
            throw new RoleNotFoundException();
        }
        else {
            return ro;
        }
    }
}
