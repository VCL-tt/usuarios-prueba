package test.test.Implement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.test.Model.User;
import test.test.Repository.UserRepository;
import test.test.Service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

    @Override
    public User getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User no encontrado"));
    }

    @Override
    public User create(User user) {
        return repo.save(user);
    }

    @Override
    public User update(Long id, User user) {
        User existing = getById(id);
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}