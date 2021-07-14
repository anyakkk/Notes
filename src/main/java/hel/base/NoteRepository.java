package hel.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface  NoteRepository extends JpaRepository<Note, Long> {
    Note getNoteById(Long id);
    List<Note> getAllByPackOrderByName(Pack pack);

    List<Note> findByNameLikeAndPack_User(String name, User user);
}