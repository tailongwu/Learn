package SpringCloud02.demo.user.service;

import SpringCloud02.demo.user.dto.UserDto;
import SpringCloud02.demo.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> getPage(Pageable pageable);
    User load(Long id);
    User save(UserDto userDto);
    void delete(Long id);
}
