package test.test.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.test.Model.User;
import test.test.Repository.UserRepository;

import java.util.List;

@Service // Singleton por defecto
@Transactional
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User no encontrado con id: " + id));
    }

    public User create(User user) {
        if (repo.existsByEmail(user.getEmail())) {
            throw new RuntimeException("El email ya existe: " + user.getEmail());
        }
        return repo.save(user);
    }

    public User update(Long id, User user) {
        User existing = getById(id);
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        return repo.save(existing);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("No existe user con id: " + id);
        }
        repo.deleteById(id);
    }
}