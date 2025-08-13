package br.com.damatomos.forum_hub.domain.users;

import br.com.damatomos.forum_hub.domain.topics.TopicModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="UserModel")
@EqualsAndHashCode(of = "id")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<TopicModel> topics;

}
