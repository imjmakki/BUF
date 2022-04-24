package app.buf.buf.dao;

import app.buf.buf.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityDAO extends JpaRepository<Authority, Long> {
}
