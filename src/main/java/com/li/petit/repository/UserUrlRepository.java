package com.li.petit.repository;

import com.li.petit.entity.UserUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserUrlRepository extends JpaRepository<UserUrl, Long> {
    UserUrl findByShortUrl(String shortUrl);
}

