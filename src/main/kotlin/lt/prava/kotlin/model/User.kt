package lt.prava.kotlin.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "first_name")
    var first_name: String? = null

    @Column(name = "last_name")
    var last_name: String? = null

    @Column(name = "email", nullable = false, length = 200)
    var email: String? = null
}