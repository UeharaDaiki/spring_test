package jp.co.sss.cytech.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jp.co.sss.cytech.entity.User;

public class CustomUserDetails implements UserDetails {

    private final User user; // 💖 DBのUserエンティティを丸ごと保持

    public CustomUserDetails(User user) {
        this.user = user;
    }

    // 💖 これを作ることで HTMLから principal.user でUserエンティティに直接アクセス可能になる
    public User getUser() {
        return user;
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // ログインIDとして使っている項目
    }

    @Override
    public String getPassword() {
        return user.getPasswords();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // 権限設定（今回は空でOK）
    }

    // アカウントの期限切れ等の状態設定（すべて true にしておきます）
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}