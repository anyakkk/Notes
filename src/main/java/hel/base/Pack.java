package hel.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "tablePack")
public class Pack {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;
    String name;

    @ManyToOne
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Note> notes =  new HashSet<>();;

    public Set<Note> getNotes() {
        return notes;
    }

    public Pack() {}

    public Pack(String name) {
        this.user = user;
        this.name = name;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}