package bg.duosoft.nacidservicesbe.controller.utils;

import bg.duosoft.nacidcoredata.util.security.SecurityRole;
import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;
import bg.duosoft.nacidservicesbe.service.CommonApplicationService;
import bg.duosoft.nacidshareddata.exception.ForbiddenException;
import bg.duosoft.nacidshareddata.util.security.SecurityUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 21.12.2022
 * Time: 11:44
 */
public class AccessUtils {

    public static void checkAccessAllowedForAppView(Integer applicationId, CommonApplicationService commonApplicationService){
        String currentUser = SecurityUtils.getUsername();
        boolean userHasAdminAccessRights = SecurityUtils.hasRole(SecurityRole.SERVICES_ACCESS);
        if(currentUser != null){
            if(userHasAdminAccessRights || commonApplicationService.userIsOwner(applicationId, currentUser)){
                return;
            }
        }
        throw new ForbiddenException();
    }

    public static void checkAccessAllowedForAppModification(Integer applicationId, CommonApplicationService commonApplicationService){
        String currentUser = SecurityUtils.getUsername();
        if(commonApplicationService.userIsOwner(applicationId, currentUser)){
           return;
        }
        throw new ForbiddenException();
    }

    public static void checkAppDeletionAllowedDependingOnStatuses(Integer applicationId, CommonApplicationService commonApplicationService){
        if(!commonApplicationService.applicationCanBeDeleted(applicationId)){
            throw new ForbiddenException();
        }
    }

    public static void checkAppModificationAllowedDependingOnStatus(Integer applicationId, CommonApplicationService commonApplicationService){
        if(FoApplicationStatus.DRAFT.equals(commonApplicationService.getFoStatus(applicationId))){
            return;
        }
        throw new ForbiddenException();
    }

    public static void checkAppFilingAllowedDependingOnStatus(Integer applicationId, CommonApplicationService commonApplicationService){
        FoApplicationStatus status = commonApplicationService.getFoStatus(applicationId);
        if(FoApplicationStatus.DRAFT.equals(status) || FoApplicationStatus.FINALIZED.equals(status)){
            return;
        }
        throw new ForbiddenException();
    }

    public static void checkAppFilingSignedAllowedDependingOnStatus(Integer applicationId, CommonApplicationService commonApplicationService){
        if(FoApplicationStatus.FINALIZED.equals(commonApplicationService.getFoStatus(applicationId))){
            return;
        }
        throw new ForbiddenException();
    }
}
