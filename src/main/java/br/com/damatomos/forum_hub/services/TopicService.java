package br.com.damatomos.forum_hub.services;

import br.com.damatomos.forum_hub.domain.topics.TopicMapper;
import br.com.damatomos.forum_hub.domain.topics.TopicModel;
import br.com.damatomos.forum_hub.domain.topics.TopicRepository;
import br.com.damatomos.forum_hub.domain.users.UserModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public TopicModel create(TopicModel model) {
        if (model.getUser() == null)
        {
            throw new RuntimeException("Nenhum usuário foi identificado para essa operação");
        }

        topicRepository.save(model);

        return model;
    }

    public Page<TopicModel> findAll(Pageable pageable) {
        return this.topicRepository.findAll(pageable);
    }

    public TopicModel update(TopicModel model) {

        var topicOptional = topicRepository.findById(model.getId());

        if (topicOptional.isEmpty())
        {
            throw new EntityNotFoundException("Não existe tópico com esse id");
        }

        var topic = topicOptional.get();

        if (model.getUser() == null || !topic.getUser().equals(model.getUser()))
        {
            throw new RuntimeException("Nenhum usuário foi identificado para essa operação");
        }

        topicRepository.save(model);

        return model;
    }

    public void delete(Long id, UserModel user)
    {
        var topicOptional = topicRepository.findById(id);

        if (topicOptional.isEmpty())
        {
            throw new EntityNotFoundException("Não existe tópico com esse id");
        }

        var topic = topicOptional.get();

        if (!topic.getUser().equals(user))
        {
            throw new RuntimeException("Nenhum usuário foi identificado para essa operação");
        }

        topicRepository.delete(topic);
    }

    public TopicModel findById(Long id) {
        var topic = this.topicRepository.findById(id);
        if (topic.isEmpty())
        {
            throw new RuntimeException("O tópico não existe");
        }

        return topic.get();
    }
}
