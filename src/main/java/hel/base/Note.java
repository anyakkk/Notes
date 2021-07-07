package hel.base;

import javax.persistence.*;

@Entity
@Table(name = "table3")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    private String hold;

    @Column(name = "NAME")
    String name;



    @ManyToOne(fetch = FetchType.EAGER)
    Pack pack;

    public Note() {
    }

    public Note(String name, String hold, hel.base.Pack pack) {
        this.name = name;
        this.hold = hold;
        this.pack = pack;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHold() {
        return hold;
    }

    public void setHold(String hold) {
        this.hold = hold;
    }

    public hel.base.Pack getPack() {
        return pack;
    }

    public void setPack(hel.base.Pack pack) {
        this.pack = pack;
    }
}