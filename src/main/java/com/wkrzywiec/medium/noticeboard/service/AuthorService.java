package com.wkrzywiec.medium.noticeboard.service;

import com.wkrzywiec.medium.noticeboard.controller.dto.AuthorDTO;
import com.wkrzywiec.medium.noticeboard.entity.Author;
import com.wkrzywiec.medium.noticeboard.mapper.AuthorMapper;
import com.wkrzywiec.medium.noticeboard.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.wkrzywiec.medium.noticeboard.mapper.AuthorMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class AuthorService implements CrudService<AuthorDTO> {

    private final AuthorRepository authorRepository;

    @Override
    public List<AuthorDTO> findAll() {
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        System.out.println("bikku's second project")
        System.out.println("bikku's third project")
        System.out.println("bikku's fourth project")
        authorRepository.findAll().forEach(author -> authorDTOList.add(INSTANCE.authorToDto(author)));
        return authorDTOList;
    }

    @Override
    public Optional<AuthorDTO> findById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        return authorOptional.map(AuthorMapper.INSTANCE::authorToDto);
    }

    @Override
    public AuthorDTO save(AuthorDTO authorDTO) {
        Author author = INSTANCE.dtoToAuthor(authorDTO);
        return INSTANCE.authorToDto(authorRepository.save(author));
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorDTO update(Long id, AuthorDTO authorDTO) {
        Author savedAuthor = authorRepository.findById(id).orElseThrow();
        Author authorToUpdate = INSTANCE.dtoToAuthor(authorDTO);

        savedAuthor.setFirstName(authorToUpdate.getFirstName());
        savedAuthor.setLastName(authorToUpdate.getLastName());

        return INSTANCE.authorToDto(authorRepository.save(savedAuthor));
    }
}
