package ch.bbw.olat.data.service;

import ch.bbw.olat.util.OlatMailService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Getter
public class OlatDataService {

    private final OlatUserService olatUserService;
    private final OlatPersonService olatPersonService;
    private final OlatSubjectService olatSubjectService;
    private final OlatGroupService olatGroupService;
    private final OlatAbsenceService olatAbsenceService;
    private final OlatTimetableService olatTimetableService;
    private final PasswordEncoder passwordEncoder;
    private final OlatMailService olatMailService;

    @Autowired
    public OlatDataService(
            OlatUserService olatUserService,
            OlatPersonService olatPersonService,
            OlatSubjectService olatSubjectService,
            OlatGroupService olatGroupService,
            OlatAbsenceService olatAbsenceService,
            OlatTimetableService olatTimetableService,
            PasswordEncoder passwordEncoder,
            OlatMailService olatMailService) {
        this.olatUserService = olatUserService;
        this.olatPersonService = olatPersonService;
        this.olatSubjectService = olatSubjectService;
        this.olatGroupService = olatGroupService;
        this.olatAbsenceService = olatAbsenceService;
        this.olatTimetableService = olatTimetableService;
        this.passwordEncoder = passwordEncoder;
        this.olatMailService = olatMailService;
    }
}
