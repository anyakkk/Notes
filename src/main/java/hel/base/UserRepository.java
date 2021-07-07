package hel.base;

import org.springframework.data.repository.CrudRepository;

public interface  UserRepository extends CrudRepository<hel.base.User, Long> {
    User getByUsername(String username);
}