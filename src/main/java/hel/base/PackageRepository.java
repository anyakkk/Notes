package hel.base;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;
import java.util.Optional;

public interface  PackageRepository extends CrudRepository<Pack, Long> {
    List<Pack> getByUser(User user);
    Pack getByName(String name);
    Pack getById(Long id);

    List<Pack> findByUserAndNameLike(User user, String name);

}
