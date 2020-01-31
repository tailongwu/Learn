package SpringCloud02.demo.user.service.Impl;

import SpringCloud02.demo.user.dto.UserDto;
import SpringCloud02.demo.user.entity.User;
import SpringCloud02.demo.user.repository.UserRepository;
import SpringCloud02.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    protected UserRepository userRepository;

    @Override
    public Page<User> getPage(Pageable pageable) {
        return null;
    }

    @Override
    public User load(Long id) {
        return this.userRepository.getOne(id);
    }

    @Override
    public User save(UserDto userDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
