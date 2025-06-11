package ua.se.sample.easynotes.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.se.sample.easynotes.dto.enums.IpsRole;
import ua.se.sample.easynotes.entity.PromptEntity;

@Repository
public interface PromptRepository extends JpaRepository<PromptEntity, IpsRole> {

}
