package br.com.damatomos.forum_hub.domain.topics;

import br.com.damatomos.forum_hub.domain.users.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="TopicModel")
@EqualsAndHashCode(of = "id")
public class TopicModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String message;

    private Boolean status;

    private String course;

    // TODO: Add author (UserModel)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;
    // TODO: Add course (CourseModel)
    // TODO: Add Responses (ResponseModel)

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
