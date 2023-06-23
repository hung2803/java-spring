package com.vti.storyproject.Service.iml;

import com.vti.storyproject.Repository.AccountRepository;
import com.vti.storyproject.Service.IAccountService;
import com.vti.storyproject.modal.dto.AccountCreateDto;
import com.vti.storyproject.modal.dto.AccountUpdateDto;
import com.vti.storyproject.modal.entity.Account;
import com.vti.storyproject.modal.entity.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Service lấy dữ liệu từ database nên và kiểm tra trong này :
// Đánh dấu đây là một bean:
@Service
public class AccountService implements IAccountService, UserDetailsService {
    // gọi tới Repository
    @Autowired // đánh dấu đây cũng là một bean
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

// khởi tạo một constructor không tham số :
    public AccountService(AccountRepository accountRepository) {

        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAll() {

        return accountRepository.findAll();
    }

    @Override
    public Account create(AccountCreateDto createDto) {
//      tạo ra một đối tượng entity rỗng
//      để hứng giá trị từ dto mà bên ngoài người dùng nhập vào
        Account account = new Account();
        String pwEncoder = passwordEncoder.encode(createDto.getPassword());
        createDto.setPassword(pwEncoder);
//         BeanUtils sẽ lấy giá trị của dto và chuyền vào account
        BeanUtils.copyProperties(createDto, account);
        account.setRole(Role.USER);
        return accountRepository.save(account);
    }

    @Override
    public Account getById(int id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return account;
        }
        return null;
    }

    @Override
    public Account update(AccountUpdateDto accountDto) {
//        sử dụng Optional để kiểm tra xem account có tồn tại hay không.bằng cách get id
        Optional<Account> accountOptional = accountRepository.findById(accountDto.getId());
       if (accountOptional.isPresent() ){
           Account account = accountOptional.get();
           BeanUtils.copyProperties(accountDto, account);
            return accountRepository.save(account);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        Account account = getById(id);
        if (account != null) {
            accountRepository.delete(account);
            return true;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if(accountOptional.isEmpty()){
            throw new UsernameNotFoundException("username không tồn tại");

        }Account account = accountOptional.get();
        // nếu account có tồn tại  --> tạo ra đối tượng userDetails từ account
        /**
         * Account.getUsername :  username
         * account.getPassword(): password đã được mã hóa
         * authorities :  danh sách quyền của user
         */
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(account.getRole());
        return new User(account.getUsername(), account.getPassword(), authorities);
    }

}
