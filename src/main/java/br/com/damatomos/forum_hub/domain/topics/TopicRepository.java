package br.com.damatomos.forum_hub.domain.topics;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<TopicModel, Long> {
}
