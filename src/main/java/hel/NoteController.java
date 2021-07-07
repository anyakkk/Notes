package hel;

import hel.base.*;
import hel.saving.Saver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NoteController {
    @Autowired
    public PackageRepository pack;
    @Autowired
    public NoteRepository note;
    @Autowired
    public UserRepository user;

    @GetMapping("/")
    public String Form(Model model){
        model.addAttribute("note", new hel.base.Note());
        return "res";
    }

    @GetMapping("/new")
    public String New(@RequestParam String name, Model model) {

        return "res";
    }

    @PostMapping("/")
    public String add(@ModelAttribute Saver saver, Model model) {


        return "res";
    }

}
