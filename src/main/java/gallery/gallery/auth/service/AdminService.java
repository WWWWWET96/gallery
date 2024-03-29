//package gallery.gallery.auth.service;
//
//
//import gallery.gallery.auth.repository.AdminRepository;
//import gallery.gallery.common.error.exception.RestApiException;
//import gallery.gallery.common.error.errorCode.CommonErrorCode;
//import gallery.gallery.general.domain.Admin;
//import gallery.gallery.general.dto.AdminDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AdminService implements UserDetailsService {
//    private final AdminRepository adminRepository;
//
//    public AdminDto login(String nickname){
//        Admin admin = loadUserByUsername(nickname);
//        return AdminDto.of(admin);
//    }
//
//    @Override
//    public Admin loadUserByUsername(String username) throws UsernameNotFoundException {
//      return adminRepository.findByNickname(username)
//              .orElseThrow(()-> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
//    }
//    public Long signup(AdminDto adminDto){
//        return adminRepository.save(Admin.of(adminDto)).getId();
//    }
//
//}
