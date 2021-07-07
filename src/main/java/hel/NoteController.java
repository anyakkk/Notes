package hel;

import hel.base.NoteRepository;
import hel.base.PackageRepository;
import hel.base.User;
import hel.base.UserRepository;
import hel.saving.Saver;
import hel.security.UserService;
import hel.security.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


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

    @GetMapping("/registration")
    public String FormReg(Model model) {

        model.addAttribute("userForm",  new User());
        return "registration";
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/registration")
    public String regi(@ModelAttribute("userForm") User userForm, Model model) {
        Optional<User> user = userRepository.findByUsername(userForm.getUsername());
        if (!user.isPresent()) {
            userRepository.save(new User(userForm.getUsername(), bCryptPasswordEncoder.encode(userForm.getPassword())));
        }
        else{
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/login";
    }
}
