package hel.base;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;

public interface  PackageRepository extends CrudRepository<hel.base.Pack, Long> {
    List<Pack> getByUser(User user);
    Pack getByName(String name);
    Pack getById(Long id);
}
