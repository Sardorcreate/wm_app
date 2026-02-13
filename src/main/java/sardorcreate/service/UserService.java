package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.user.UserCreateDto;
import sardorcreate.dto.user.UserDto;
import sardorcreate.entity.Department;
import sardorcreate.entity.User;
import sardorcreate.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DepartmentService departmentService;

    public ResponseEntity<?> createUser(UserCreateDto dto) {

        Optional<User> byFullName = userRepository.findByFullName(dto.getFullName());

        Department dep = departmentService.getDepartment(dto.getDepartment());

        if (byFullName.isPresent()) {
            ResponseEntity.badRequest().body("The user with this name already exists!!!");
        }

        User user = new User();

        user.setFullName(dto.getFullName());
        user.setDepartment(dep);

        User save = userRepository.save(user);

        UserDto newDto = new UserDto();

        newDto.setId(save.getId());
        newDto.setFullName(save.getFullName());
        newDto.setDepartment(save.getDepartment().getName());

        return ResponseEntity.ok(newDto);
    }

    public User getUser(long id) {

        Optional<User> byId = userRepository.findById(id);

        if (byId.isEmpty()) {
            throw new RuntimeException("This user does not exist!!!");
        }

        return byId.get();
    }

    public ResponseEntity<?> getUserByDep(long id) {

        List<User> users = userRepository.findUserByDepartment_Id(id);

        if (users.isEmpty()) {
            throw new RuntimeException("There is no any users with this dep_id");
        }

        List<UserDto> dtos = new ArrayList<>();

        for (User user : users) {
            UserDto newDto = new UserDto();

            newDto.setId(user.getId());
            newDto.setFullName(user.getFullName());
            newDto.setDepartment(user.getDepartment().getName());

            dtos.add(newDto);
        }

        return ResponseEntity.ok(dtos);
    }
}
