package hel;

import hel.base.Note;
import hel.base.Pack;

import java.util.List;

public class SearchView {
    private List<Pack> packs;
    private List<Note> notes;


    public SearchView() {
    }

    public SearchView(List<Pack> packs, List<Note> notes) {
        this.packs = packs;
        this.notes = notes;
    }

    public List<Pack> getPacks() {
        return packs;
    }

    public void setPacks(List<Pack> packs) {
        this.packs = packs;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

}
