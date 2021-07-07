package hel.base;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface  NoteRepository extends CrudRepository<hel.base.Note, Long> {
    Note getNoteById(Long id);
    List<Note> getAllByPack(Pack pack);
}