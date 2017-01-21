package chatt.moment

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import lombok.Data
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull
import org.springframework.data.repository.CrudRepository




@SpringBootApplication
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

@Controller
class GreetingController
@Autowired
constructor(private val repo: UserRepository) {

    @RequestMapping("/greeting")
    fun greeting(@RequestParam(value = "name", required = false, defaultValue = "World") name: String, model: Model): String {
        val user = AppUser().apply {
            username = "Sebulba"
        }


        repo.save(user)
        model.addAttribute("name", System.getenv("MAGNUS"))
        model.addAttribute("repoCount", repo.count())
        return "greeting"
    }

}

@Entity
@Data
@RequiredArgsConstructor
class AppUser {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @NotNull
    var username: String? = null

}

interface UserRepository : CrudRepository<AppUser, Long>