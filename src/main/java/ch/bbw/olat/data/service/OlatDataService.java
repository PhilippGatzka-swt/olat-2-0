package ch.bbw.olat.data.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public OlatDataService(
            OlatUserService olatUserService,
            OlatPersonService olatPersonService,
            OlatSubjectService olatSubjectService,
            OlatGroupService olatGroupService,
            OlatAbsenceService olatAbsenceService,
            OlatTimetableService olatTimetableService) {
        this.olatUserService = olatUserService;
        this.olatPersonService = olatPersonService;
        this.olatSubjectService = olatSubjectService;
        this.olatGroupService = olatGroupService;
        this.olatAbsenceService = olatAbsenceService;
        this.olatTimetableService = olatTimetableService;
    }
}
