package lt.prava.kotlin.controller

import lt.prava.kotlin.model.User
import lt.prava.kotlin.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import javax.persistence.EntityNotFoundException

@Controller
class UserController {

    @Autowired
    private val userService: UserService? = null

    @GetMapping("/")
    fun getAllUserView(model: Model): String {
        val users = userService!!.allUsers
        model["users"] = users
        return "home"
    }

    @GetMapping("/create")
    fun createUserView(model: Model): String {
        val user = User()
        model["user"] = user
        model["isUpdate"] = false
        return "create-update"
    }

    @PostMapping("/update/{id}")
    fun createUser(
        @ModelAttribute("user") user: User,
        @PathVariable("id") id: Long?
    ): String {
        user.id = id
        userService!!.createOrUpdateUser(user)
        return "redirect:/"
    }

    @GetMapping("/update/{id}")
    @Throws(EntityNotFoundException::class)
    fun updateUser(model: Model, @PathVariable("id") id: Long?): String {
        val user = userService!!.getUserById(id!!)
        model.addAttribute("user", user)
        model.addAttribute("isUpdate", true)
        return "create-update"
    }

    @PostMapping("/create")
    fun createUser(@ModelAttribute("user") user: User?): String {
        userService!!.createOrUpdateUser(user!!)
        return "redirect:/"
    }

    @GetMapping("/delete/{id}")
    @Throws(EntityNotFoundException::class)
    fun deleteUser(@PathVariable("id") id: Long?): String {
        userService!!.deleteUserById(id!!)
        return "redirect:/"
    }
}