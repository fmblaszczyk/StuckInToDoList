package stuckinjava.todolist.general.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import stuckinjava.todolist.general.model.User;
import stuckinjava.todolist.general.repository.UserRepository;
import stuckinjava.todolist.security.model.ERole;
import stuckinjava.todolist.security.model.Privilege;
import stuckinjava.todolist.security.model.Role;
import stuckinjava.todolist.security.repository.PrivilegeRepository;
import stuckinjava.todolist.security.repository.RoleRepository;

import java.util.*;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {



    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    private boolean alreadySetup = false;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
       // Example multi-role privilege
        final Privilege registeredUserPrivilege = createPrivilegeIfNotFound("REGISTERED_USER");

        // Example admin privileges
        final Privilege readUsersDataPrivilege = createPrivilegeIfNotFound("READ_OTHER_USERS_DATA");
        final Privilege editUsersDataPrivilege = createPrivilegeIfNotFound("EDIT_OTHER_USERS_DATA");
        final Privilege deleteUsersDataPrivilege = createPrivilegeIfNotFound("DELETE_OTHER_USERS_DATA");

        // Example simple user privileges
        final Privilege readOwnDataPrivilege = createPrivilegeIfNotFound("READ_REST_OWN_DATA");
        final Privilege editOwnDataPrivilege = createPrivilegeIfNotFound("EDIT_OWN_DATA");






        // == create initial roles
        final List<Privilege> adminPrivileges = new ArrayList<Privilege>(Arrays.asList(registeredUserPrivilege,readUsersDataPrivilege, editUsersDataPrivilege, deleteUsersDataPrivilege));
        final List<Privilege> userPrivileges = new ArrayList<Privilege>(Arrays.asList(registeredUserPrivilege,readOwnDataPrivilege, editOwnDataPrivilege));
        final Role adminRole = createRoleIfNotFound(ERole.ROLE_ADMIN, adminPrivileges);
        final Role userRole = createRoleIfNotFound(ERole.ROLE_USER, userPrivileges);


        // == create initial example users
        User adminUser = createUserIfNotFound("admin@test.com", "admin", "test", new HashSet<>(new ArrayList<Role>(Arrays.asList(adminRole))));
        User commonUser = createUserIfNotFound("common@test.com", "common","test", new HashSet<>(new ArrayList<Role>(Arrays.asList(userRole))));



        alreadySetup = true;

    }






    @Transactional
    public Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    public Role createRoleIfNotFound(final ERole name, final Collection<Privilege> privileges) {
        Optional<Role> roleInBase = roleRepository.findByName(name);
        if (!roleInBase.isPresent()) {
            Role role = new Role(name);
            role.setPrivileges(privileges);
            role = roleRepository.save(role);
            return role;
        }else{
            return roleInBase.get();
        }


    }






    @Transactional
    public User createUserIfNotFound(final String email,final String login, final String password, final Set<Role> roles) {

        Optional<User> userInBase = userRepository.findByUsername(email);

        if (!userInBase.isPresent()) {
            User user = new User();
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setUsername(login);
            user.setRoles(roles);
            user = userRepository.save(user);
            return user;
        }else{
            return userInBase.get();
        }
    }



}