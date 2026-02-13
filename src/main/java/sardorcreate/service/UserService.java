package sardorcreate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sardorcreate.dto.user.UserCreateDto;
import sardorcreate.dto.user.UserDto;
import sardorcreate.entity.Department;
import sardorcreate.entity.User;
import sardorcreate.exception.AlreadyExistsException;
import sardorcreate.exception.NotExistsException;
import sardorcreate.mapper.UserMapper;
import sardorcreate.repository.UserRepository;
import sardorcreate.util.MessageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DepartmentService departmentService;
    private final UserMapper userMapper;

    public ResponseEntity<?> createUser(UserCreateDto dto) {

        if (userRepository.findByLogin(dto.getLogin()).isPresent()) {
            throw new AlreadyExistsException(MessageService.getMessage("The employee with this login already exists"));
        }

        Department dep = departmentService.getDepartment(dto.getDepartment());

        User user = userMapper.dtoToEntity(dto, dep);

        User save = userRepository.save(user);

        UserDto newDto = userMapper.entityToDto(save);

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
            throw new NotExistsException(MessageService.getMessage("There is no any users with this dep_id"));
        }

        List<UserDto> dtos = new ArrayList<>();

        for (User user : users) {
            UserDto dto = userMapper.entityToDto(user);

            dtos.add(dto);
        }

        return ResponseEntity.ok(dtos);
    }
}
