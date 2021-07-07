package hel.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "table1")
public class Pack {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long id;
    String name;

    @ManyToOne
    private User user;



    @JsonIgnore
    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<hel.base.Note> notes =  new HashSet<>();;

    public Set<hel.base.Note> getNotes() {
        return notes;
    }

    public Pack() {}

    public Pack(String name) {
        this.user = user;
        this.name = name;
    }

    public void setNotes(Set<hel.base.Note> notes) {
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


    public hel.base.User getUser() {
        return user;
    }

    public void setUser(hel.base.User user) {
        this.user = user;
    }
}