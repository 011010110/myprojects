package org.jiang.core.service;

import org.jiang.core.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserService extends JpaRepository<User, String> {
}
