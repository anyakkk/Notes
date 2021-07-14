package hel;

import hel.base.*;
import hel.saving.SaverForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;
import java.security.Principal;
import java.util.Optional;


@Controller
public class NoteController {
    @Autowired
    public PackageRepository packRepository;
    @Autowired
    public NoteRepository noteRepository;
    @Autowired
    public UserRepository userRepository;

    @GetMapping("/")
    public String Form(Model model, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        model.addAttribute("packList", packRepository.getByUser(user.get()));
        model.addAttribute("saverForm", new SaverForm());
        model.addAttribute("isPackSelected", false);
        return "res";
    }

    @GetMapping("/registration")
    public String FormReg(Model model) {

        model.addAttribute("userForm", new User());
        return "registration";
    }

    @GetMapping("/addPack")
    public String addPack(@RequestParam("name") String name, Model model, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        Pack newPack = new Pack(name);
        newPack.setUser(user.get());
        packRepository.save(newPack);
        return "redirect:/";
    }

    @GetMapping("/pack/{pack}")
    public String formNotes(@PathVariable("pack") Long id, Model model, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        Optional<Pack> nowPack = packRepository.findById(id);
        if (!nowPack.isPresent() || (nowPack.get().getUser() != user.get()))
            return "redirect:/";
        model.addAttribute("packList", packRepository.getByUser(user.get()));
        model.addAttribute("saverForm", new SaverForm());
        model.addAttribute("isPackSelected", true);
        model.addAttribute("packSelected", id);
        model.addAttribute("noteList", nowPack.get().getNotes());
        return "res";
    }

    @GetMapping("/note/{pack}/{note}")
    public String formNotes(@PathVariable("pack") Long packId, @PathVariable("note") Long noteId, Model model, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        Optional<Pack> nowPack = packRepository.findById(packId);
        if (!nowPack.isPresent() || (nowPack.get().getUser() != user.get()))
            return "redirect:/";
        Optional<Note> nowNote = noteRepository.findById(noteId);
        if (!nowNote.isPresent() || (nowNote.get().getPack() != nowPack.get()))
            return "redirect:/";
        model.addAttribute("packList", packRepository.getByUser(user.get()));
        model.addAttribute("saverForm", new SaverForm(nowNote.get().getName(), nowNote.get().getHold()));
        model.addAttribute("isPackSelected", true);
        model.addAttribute("packSelected", packId);
        model.addAttribute("noteList", nowPack.get().getNotes());
        model.addAttribute("isNoteSelected", true);
        model.addAttribute("noteSelected", noteId);

        return "res";
    }

    @GetMapping("/saveNote/{pack}/{note}")
    public String addNote(@ModelAttribute("saverForm") SaverForm saverForm, @PathVariable("pack") Long packId, @PathVariable("note") Long noteId, Model model, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        Optional<Pack> nowPack = packRepository.findById(packId);
        if (!nowPack.isPresent() || (nowPack.get().getUser() != user.get()))
            return "redirect:/";
        if (noteId == -1) {
            Note note = new Note(saverForm.getName(), saverForm.getKeep(), nowPack.get());
            noteRepository.save(note);
            return "redirect:/note/" + packId + "/" + note.getId();
        }
        Optional<Note> nowNote = noteRepository.findById(noteId);
        if (!nowNote.isPresent() || (nowNote.get().getPack() != nowPack.get()))
            return "redirect:/";
        nowNote.get().setName(saverForm.getName());
        nowNote.get().setHold(saverForm.getKeep());
        noteRepository.save(nowNote.get());
        return "redirect:/note/" + packId + "/" + nowNote.get().getId();
    }

    @GetMapping("/addNote/{pack}")
    public String addNote(@PathVariable("pack") Long id, Model model, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        Optional<Pack> nowPack = packRepository.findById(id);
        if (!nowPack.isPresent() || (nowPack.get().getUser() != user.get()))
            return "redirect:/";
        model.addAttribute("packList", packRepository.getByUser(user.get()));
        model.addAttribute("saverForm", new SaverForm());
        model.addAttribute("isPackSelected", true);
        model.addAttribute("packSelected", id);
        model.addAttribute("noteList", nowPack.get().getNotes());
        model.addAttribute("isNoteSelected", true);
        model.addAttribute("noteSelected", -1);
        return "res";
    }

    @GetMapping("/search")
    public ResponseEntity<SearchView> notesSearch(@RequestParam("search") String search, Model model, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        return ResponseEntity.ok(new SearchView(
                packRepository.findByUserAndNameLike(user.get(), "%" + search + "%"),
                noteRepository.findByNameLikeAndPack_User("%" + search + "%", user.get())));
    }

    @GetMapping("/renamePack")
    public String renamePack(@RequestParam("id") Long id, @RequestParam("name") String name, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if ((id != null) && (name != null)) {
            Optional<Pack> pack = packRepository.findById(id);

            if (pack.isPresent() && (pack.get().getUser() == userRepository)) {
                pack.get().setName(name);
                packRepository.save(pack.get());
            }

        }
        return "redirect:/";
    }


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/registration")
    public String regi(@ModelAttribute("userForm") User userForm, Model model) {
        Optional<User> user = userRepository.findByUsername(userForm.getUsername());
        if (!user.isPresent()) {
            userRepository.save(new User(userForm.getUsername(), bCryptPasswordEncoder.encode(userForm.getPassword())));
        } else {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) throws ServletException {
        httpServletRequest.logout();
        return "redirect:/login";
    }

    @GetMapping("/removeNote")
    public String notesRemove(@RequestParam("id") Long id, Model model, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if (id != null) {
            Optional<Note> note = noteRepository.findById(id);
            noteRepository.delete(note.get());
        }
        return "redirect:/";
    }

    @GetMapping("/removePack")
    public String packsRemove(@RequestParam("id") Long id, Model model, Principal principal) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        if (id != null) {
            Optional<Pack> pack = packRepository.findById(id);
            packRepository.delete(pack.get());
        }
        return "redirect:/";
    }

}