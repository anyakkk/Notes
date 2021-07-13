package hel.base;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface  NoteRepository extends CrudRepository<hel.base.Note, Long> {
    Note getNoteById(Long id);
    List<Note> getAllByPack(Pack pack);

    List<Note> findByNameLikeAndPack_User(String name, User user);
}