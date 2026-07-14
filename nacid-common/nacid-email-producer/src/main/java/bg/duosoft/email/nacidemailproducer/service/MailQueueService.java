package bg.duosoft.email.nacidemailproducer.service;

public interface MailQueueService {
    /**
     * Send mail task to message queue
     *
     * @param message
     */
    void sendMailToQueue(String message);

    void sendPortalMailToQueue(Integer emailNotificationId);

    void sendSimpleMailToQueue(String recipients, String message, String subject);

}