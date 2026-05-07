package bg.duosoft.nacidbackofficeshareddata.utils;

import bg.duosoft.email.nacidemailproducer.domain.core.email_data.CChangeResponsibleUserEmailData;
import bg.duosoft.email.nacidemailproducer.service.MailSenderService;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationDTO;
import bg.duosoft.nacid.backoffice.core.data.domain.rest.common.ApplicationResponsibleUsersDTO;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResponsibleUserChangeUtils {

    private static final String NO_RESPONSIBLE_USER = "-";

    public static void sendResponsibleUserChangeNotification(String newResponsibleUser, String entryNumber, String sourceUser, MailSenderService mailSenderService) {
        CChangeResponsibleUserEmailData emailData = CChangeResponsibleUserEmailData.builder().sourceUsername(sourceUser).targetUsername(newResponsibleUser).appEntryNumber(entryNumber).build();
        mailSenderService.sendResponsibleUserChangeMail(emailData);
    }

    public static boolean isResponsibleUserChange(String newResponsibleUser, List<ApplicationResponsibleUsersDTO> responsibleUsers) {
        boolean isChanged = true;

        if (!StringUtils.hasText(newResponsibleUser) || NO_RESPONSIBLE_USER.equals(newResponsibleUser)) {
            return false;
        }
        if (CollectionUtils.isEmpty(responsibleUsers) && StringUtils.hasText(newResponsibleUser)) {
            return isChanged;
        }

        ApplicationResponsibleUsersDTO lastUser = responsibleUsers.stream().filter(r -> Objects.isNull(r.getDateTo())).findFirst().orElse(null);
        if (Objects.nonNull(lastUser) && StringUtils.hasText(lastUser.getResponsibleUser()) && lastUser.getResponsibleUser().equals(newResponsibleUser)) {
            isChanged = false;
        }

        return isChanged;
    }

    public static String getActiveResponsibleUser(ApplicationDTO application) {
        List<ApplicationResponsibleUsersDTO> responsibleUsers = application.getResponsibleUsers();
        if (!CollectionUtils.isEmpty(responsibleUsers)) {
            ApplicationResponsibleUsersDTO activeResponsibleUser = responsibleUsers.stream().filter(r -> Objects.isNull(r.getDateTo())).findFirst().orElse(null);
            if (Objects.nonNull(activeResponsibleUser)) {
                return activeResponsibleUser.getResponsibleUser();
            }
        }
        return null;
    }

    public static void processResponsibleUserChange(ApplicationDTO application, String responsibleUser) {
        if (CollectionUtils.isEmpty(application.getResponsibleUsers())) {
            application.setResponsibleUsers(new ArrayList<>());
        }

        List<ApplicationResponsibleUsersDTO> responsibleUsers = application.getResponsibleUsers();
        ApplicationResponsibleUsersDTO lastUser = responsibleUsers.stream().filter(r -> Objects.isNull(r.getDateTo())).findFirst().orElse(null);

        if (StringUtils.hasText(responsibleUser) && Objects.nonNull(lastUser) && !responsibleUser.equals(lastUser.getResponsibleUser())){
            lastUser.setDateTo(LocalDateTime.now());
            responsibleUsers.add(new ApplicationResponsibleUsersDTO(null, responsibleUser, LocalDateTime.now(), null, null));
        }
        if (StringUtils.hasText(responsibleUser) && Objects.isNull(lastUser)){
            responsibleUsers.add(new ApplicationResponsibleUsersDTO(null, responsibleUser, LocalDateTime.now(), null, null));
        }
        if (!StringUtils.hasText(responsibleUser) && Objects.nonNull(lastUser)){
            lastUser.setDateTo(LocalDateTime.now());
        }

    }
}
