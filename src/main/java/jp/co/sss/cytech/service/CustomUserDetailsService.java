package jp.co.sss.cytech.service; // 💡 作成した場所のパッケージ名に合わせてください

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.sss.cytech.entity.User;
import jp.co.sss.cytech.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	java.util.Optional<User> optionalUser = userRepository.findByEmail(email); // レポジトリの関数を使ってemailが一致するユーザを検索し、userに格納
        if (optionalUser.isEmpty()) {//ユーザが見つからなかった場合の処理
            throw new UsernameNotFoundException("ユーザーが見つかりません: " + email);
        }
        
        User user = optionalUser.get();
        System.out.println("★取得したユーザー名: " + user.getUserName());
        return new CustomUserDetails(user);
    }
}